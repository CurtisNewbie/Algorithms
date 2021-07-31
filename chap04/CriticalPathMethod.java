import java.util.*;

public class CriticalPathMethod {

    private final int N;
    private final EdgeWeightedDigraph g;
    private final AcyclicLongestPath acyclicLP;
    private final int start;
    private final int end;

    /**
     * CPM
     * 
     * @param tasks each row is a task, each row should have two or more elements,
     *              first element is duration, and [1...n-1] are dependencies
     */
    public CriticalPathMethod(List<List<String>> tasks) {
        N = tasks.size();
        g = new EdgeWeightedDigraph(2 * N + 2); // two nodes for each task, and two extra for start and end
        start = 2 * N;
        end = 2 * N + 1;
        for (int v = 0; v < N; v++) {
            List<String> task = tasks.get(v);
            double duration = Double.valueOf(task.get(0));
            int w = v + N;
            g.addEdge(new Edge(v, w, duration)); // v -> w
            g.addEdge(new Edge(start, v, 0.0)); // start -> v
            g.addEdge(new Edge(w, end, 0.0)); // w -> end

            // add dependency, or tasks after v->w
            for (int i = 1; i < task.size(); i++) {
                int nextTask = Integer.valueOf(task.get(i));
                g.addEdge(new Edge(w, nextTask, 0.0));
            }
        }
        acyclicLP = new AcyclicLongestPath(g, start);
    }

    public List<Double> startingTimes() {
        List<Double> times = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            times.add(acyclicLP.distTo(i));
        }
        return times;
    }

    public double criticalPathDuration() {
        return acyclicLP.distTo[end];
    }

    public static void main(String[] args) {
        List<List<String>> tasks = new ArrayList<>();
        tasks.add(Arrays.asList("41", "1", "7", "9"));
        tasks.add(Arrays.asList("51", "2"));
        tasks.add(Arrays.asList("50"));
        tasks.add(Arrays.asList("36"));
        tasks.add(Arrays.asList("38"));
        tasks.add(Arrays.asList("45"));
        tasks.add(Arrays.asList("21", "3", "8"));
        tasks.add(Arrays.asList("32", "3", "8"));
        tasks.add(Arrays.asList("32", "2"));
        tasks.add(Arrays.asList("29", "4", "6"));

        CriticalPathMethod cpm = new CriticalPathMethod(tasks);
        List<Double> startTimes = cpm.startingTimes();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("Task: %d, starts at: %.2f\n", i, startTimes.get(i));
        }
        System.out.printf("Critical Path Duration: %.2f in total\n", cpm.criticalPathDuration());
    }
}