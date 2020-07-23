import java.nio.file.Paths;
import java.util.*;

public class ConnectedComponents {
    private boolean[] marked;
    private List<List<Integer>> components;

    public ConnectedComponents(Graph g) {
        marked = new boolean[g.vertices()];
        components = new ArrayList<>();
        for (int x = 0; x < g.vertices(); x++) {
            if (!marked[x]) {
                List<Integer> l = new ArrayList<>();
                dfs(g, x, l);
                components.add(l);
            }
        }
    }

    private void dfs(Graph g, int v, List<Integer> l) {
        marked[v] = true;
        l.add(v);
        for (int w : g.adjacent(v)) {
            if (!marked[w])
                dfs(g, w, l);
        }
    }

    public int count() {
        return components.size();
    }

    public List<List<Integer>> components() {
        return components;
    }

    public static void main(String[] args) {
        String name = "../demodata/notConnectedG.txt";
        System.out.println("Graph: %s\n");
        Graph g1 = new Graph(Paths.get(name));
        System.out.printf("Graph: %s", g1.toString());
        System.out.printf("Graph is connected: %b\n\n", new DFS(g1, 0).count() == g1.vertices());

        ConnectedComponents cc = new ConnectedComponents(g1);
        System.out.printf("Connected Components Count: %d\n", cc.count());
        System.out.println("Connected Components: ");
        for (List<Integer> l : cc.components()) {
            System.out.println(l.toString());
        }
    }

}
