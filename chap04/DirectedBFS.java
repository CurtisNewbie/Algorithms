import java.util.*;

public class DirectedBFS {
    private boolean[] marked;
    private int[] parentOf;
    private int source;

    public DirectedBFS(Digraph g, int source) {
        this.source = source;
        marked = new boolean[g.vertices()];
        parentOf = new int[g.vertices()];
        bfs(g, source);
    }

    private void bfs(Digraph g, int v) {
        Queue<Integer> queue = new LinkedList<>();
        marked[v] = true;
        queue.offer(v);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int w : g.adjacent(x)) {
                if (!marked[w]) {
                    queue.offer(w);
                    marked[w] = true;
                    parentOf[w] = x;
                }
            }
        }
    }

    public Iterable<Integer> reachable() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < marked.length; i++) {
            if (marked[i])
                list.add(i);
        }
        return list;
    }

    public boolean reachable(int w) {
        return marked[w];
    }

    public Iterable<Integer> pathTo(int w) {
        if (!reachable(w))
            return null;

        Deque<Integer> deque = new LinkedList<>();
        for (int x = w; x != source; x = parentOf[x]) {
            deque.offerFirst(x);
        }
        deque.offerFirst(source);
        return deque;
    }

    public static void main(String[] args) {
        String fname = "tinyDG.txt";
        Digraph g = new Digraph(fname);
        System.out.println("Graph: " + fname);
        System.out.println(g.toString());

        for (int i = 0; i < g.vertices(); i++) {
            System.out.printf("Vertex: %d can reach to: '%s'\n", i,
                    new DirectedBFS(g, i).reachable().toString());
        }
        int v = 0;
        int w = 2;
        DirectedBFS bfs = new DirectedBFS(g, v);
        System.out.printf("Vertex: %d 's shortest path to %d: '%s'\n", v, w,
                bfs.pathTo(w).toString());
    }
}

