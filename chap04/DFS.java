import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DFS {

    private boolean[] marked;
    private int count = 0;

    /**
     * @param g Graph
     * @param s Source (the node that DFS starts)
     */
    public DFS(Graph g, int s) {
        marked = new boolean[g.vertices()];
        dfs(g, s);
    }

    public DFS(Graph g, Iterable<Integer> sources) {
        marked = new boolean[g.vertices()];

        for (int v : sources) {
            if (!marked[v])
                dfs(g, v);
        }
    }

    private void dfs(Graph g, int v) {
        mark(v);
        count++;
        for (int w : g.adjacent(v)) {
            if (!isMarked(w))
                dfs(g, w);
        }
    }

    private void mark(int v) {
        marked[v] = true;
    }

    private boolean isMarked(int v) {
        return marked[v];
    }

    public boolean canReach(int v) {
        return isMarked(v);
    }

    public int count() {
        return count;
    }

    public Iterable<Integer> reachable() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < marked.length; i++) {
            if (marked[i])
                list.add(i);
        }
        return list;
    }

    public static void main(String[] args) {
        String name = "../demodata/notConnectedG.txt";
        System.out.printf("Graph: %s\n", name);
        Graph g1 = new UndirectedGraph(Paths.get(name));
        System.out.printf("Graph: %s", g1.toString());
        System.out.printf("Graph is connected: %b\n", new DFS(g1, 0).count() == g1.vertices());

        name = "../demodata/connectedG.txt";
        System.out.printf("Graph: %s\n", name);
        Graph g2 = new UndirectedGraph(Paths.get(name));
        System.out.printf("Graph: %s", g2.toString());
        System.out.printf("Graph is connected: %b\n", new DFS(g2, 0).count() == g2.vertices());
    }
}
