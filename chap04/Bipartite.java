import java.nio.file.Paths;

public class Bipartite {

    private int[] colours;
    private final int RED = 1;
    private final int BLACK = -1;
    private final int UNCOLOURED = 0;
    private boolean isBipartite;

    public Bipartite(Graph g) {
        colours = new int[g.vertices()];
        isBipartite = true;
        for (int i = 0; i < g.vertices() && isBipartite; i++) { // graph might not be connected
            if (colours[i] == UNCOLOURED)
                dfs(g, i, RED);
        }
    }

    private void dfs(Graph g, int v, int c) {
        colours[v] = c;
        for (int w : g.adjacent(v)) {
            if (colours[w] == UNCOLOURED) {
                dfs(g, w, -c);
            } else if (colours[w] == colours[v]) {
                isBipartite = false;
                return;
            }
        }
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {
        String test = "bipartite.txt";
        System.out.printf("Graph: %s\n", test);
        Graph g1 = new Graph(Paths.get(test));
        System.out.printf("Graph: %s", g1.toString());
        System.out.printf("Is Bipartite: %b\n", new Bipartite(g1).isBipartite());

        System.out.println("\nGraph: notBipartite.txt");
        Graph g2 = new Graph(Paths.get("notBipartite.txt"));
        System.out.printf("Graph: %s", g2.toString());
        System.out.printf("Is Bipartite: %b\n", new Bipartite(g2).isBipartite());
    }
}
