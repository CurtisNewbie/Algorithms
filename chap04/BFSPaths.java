import java.util.*;
import java.nio.file.*;

public class BFSPaths {
    private boolean[] marked;
    private int[] parentOf;
    private int source;

    public BFSPaths(Graph g, int source) {
        marked = new boolean[g.vertices()];
        parentOf = new int[g.vertices()];
        this.source = source;
        bfs(g, source);
    }

    private void bfs(Graph g, int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        marked[v] = true;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int w : g.adjacent(x)) {
                if (!marked[w]) {
                    marked[w] = true;
                    parentOf[w] = x; // x->w
                    queue.offer(w);
                }
            }
        }
    }

    public boolean hasPathTo(int w) {
        return marked[w];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))
            return null;

        Deque<Integer> deque = new LinkedList<>();
        for (int x = v; x != source; x = parentOf[x]) {
            deque.add(x);
        }
        deque.add(source);
        return deque;
    }

    public static void main(String[] args) {
        Graph g = new Graph(Paths.get("connectedG.txt"));
        BFSPaths paths = new BFSPaths(g, 0);
        System.out.printf("Has path from 0 to 4? %b\n", paths.hasPathTo(4));
        System.out.printf("Path from 0 to 4: %s\n", paths.pathTo(4).toString());
    }

}
