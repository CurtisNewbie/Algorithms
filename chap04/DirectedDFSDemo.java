import java.util.*;

/** Same as DFS, no difference at all */
public class DirectedDFSDemo {

    public static void main(String[] args) {
        String fname = "/home/zhuangyongj/git/Algorithms/demodata/tinyDG.txt";
        Graph g = new Digraph(fname);
        System.out.println("Graph: " + fname);
        System.out.println(g.toString());
        for (int i = 0; i < g.vertices(); i++) {
            System.out.printf("Vertex: %d can reach to: '%s'\n", i, new DFS(g, i).reachable().toString());
        }
        List<Integer> sources = Arrays.asList(1, 2, 6);
        System.out.printf("Vertices: %s can reach to: '%s'\n", sources.toString(),
                new DFS(g, sources).reachable().toString());
    }
}
