import java.util.Deque;
import java.util.LinkedList;
import java.nio.file.Paths;

public class DFSPaths {
    private boolean[] marked;
    private int[] parentOf;
    private int source;

    public DFSPaths(Graph g, int source) {
        this.source = source;
        this.marked = new boolean[g.vertices()];
        this.parentOf = new int[g.vertices()];
        dfs(g, source);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adjacent(v)) {
            if (!marked[w]) {
                parentOf[w] = v; // v->w
                dfs(g, w);
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
        Graph g = new UndirectedGraph(Paths.get("/home/zhuangyongj/git/Algorithms/demodata/connectedG.txt"));
        DFSPaths paths = new DFSPaths(g, 0);
        System.out.printf("Has path from 0 to 4? %b\n", paths.hasPathTo(4));
        System.out.printf("Path from 0 to 4: %s\n", paths.pathTo(4).toString());
    }
}
