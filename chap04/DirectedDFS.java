import java.util.*;

public class DirectedDFS {

    private boolean[] marked;

    public DirectedDFS(Digraph g, int source) {
        marked = new boolean[g.vertices()];
        dfs(g, source);
    }

    public DirectedDFS(Digraph g, Iterable<Integer> sources) {
        marked = new boolean[g.vertices()];
        for (int v : sources) {
            if (!marked[v])
                dfs(g, v);
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        for (int w : g.adjacent(v)) {
            if (!marked[w]) {
                marked[w] = true;
                dfs(g, w);
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

    public static void main(String[] args) {
        String fname = "tinyDG.txt";
        Digraph g = new Digraph(fname);
        System.out.println("Graph: " + fname);
        System.out.println(g.toString());
        for (int i = 0; i < g.vertices(); i++) {
            System.out.printf("Vertex: %d can reach to: '%s'\n", i,
                    new DirectedDFS(g, i).reachable().toString());
        }
        List<Integer> sources = Arrays.asList(1, 2, 6);
        System.out.printf("Vertices: %s can reach to: '%s'\n", sources.toString(),
                new DirectedDFS(g, sources).reachable().toString());
    }
}
