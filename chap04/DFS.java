import java.nio.file.Paths;

public class DFS {

    private boolean[] marked;
    private int count;

    /**
     * @param g Graph
     * @param s Source (the node that DFS starts)
     */
    public DFS(Graph g, int s) {
        marked = new boolean[g.vertices()];
        count = 0;
        dfs(g, s);
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

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Graph: notConnectedG.txt");
        Graph g1 = new Graph(Paths.get("notConnectedG.txt"));
        System.out.printf("Graph: %s", g1.toString());
        System.out.printf("Graph is connected: %b\n", new DFS(g1, 0).count() == g1.vertices());

        System.out.println("\nGraph: connectedG.txt");
        Graph g2 = new Graph(Paths.get("connectedG.txt"));
        System.out.printf("Graph: %s", g2.toString());
        System.out.printf("Graph is connected: %b\n", new DFS(g2, 0).count() == g2.vertices());
    }
}
