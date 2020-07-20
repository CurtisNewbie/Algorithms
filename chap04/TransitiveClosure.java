import java.util.*;

// for any pair of vertices (v and w), is there a path between them
public class TransitiveClosure {
    // accessible vertices for each vertex
    private DirectedDFS[] vertices;

    public TransitiveClosure(Digraph g) {
        vertices = new DirectedDFS[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            vertices[v] = new DirectedDFS(g, v);
        }
    }

    public Iterable<Integer> reachableOf(int v) {
        return vertices[v].reachable();
    }

    boolean reachable(int v, int w) {
        return vertices[v].canReach(w);
    }

    public static void main(String[] args) {
        String fname = "tinyDG.txt";
        Digraph g = new Digraph(fname);
        System.out.println("Graph: " + fname);
        System.out.println(g.toString());
        TransitiveClosure tc = new TransitiveClosure(g);
        for (int i = 0; i < g.vertices(); i++) {
            System.out.printf("Vertex: %d can reach to: '%s'\n", i, tc.reachableOf(i));
        }
        System.out.printf("Vertex %d can reach to %d: %b\n", 0, 11, tc.reachable(0, 11));
        System.out.printf("Vertex %d can reach to %d: %b\n", 0, 4, tc.reachable(0, 4));
    }
}
