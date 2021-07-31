import java.nio.file.Paths;

public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;
    private boolean[] onStack;

    public Cycle(Graph g) {
        marked = new boolean[g.vertices()];
        onStack = new boolean[g.vertices()];
        hasCycle = false;
        for (int x = 0; x < g.vertices(); x++) {
            if (!marked[x]) {
                dfs(g, x);
            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : g.adjacent(v)) {
            if (!marked[w]) {
                dfs(g, w);
            } else if (onStack[w]) {
                hasCycle = true;
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        String name = "/home/zhuangyongj/git/Algorithms/demodata/notConnectedG.txt";
        System.out.printf("Graph: %s\n", name);
        Graph g1 = new UndirectedGraph(Paths.get(name));
        System.out.printf("Graph: %s", g1.toString());
        System.out.printf("Graph is connected: %b\n", new DFS(g1, 0).count() == g1.vertices());
        Cycle c = new Cycle(g1);
        System.out.printf("Graph has cycle: %b\n", c.hasCycle());
    }
}
