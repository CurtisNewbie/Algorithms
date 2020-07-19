import java.io.FileInputStream;
import java.io.IOException;

import java.util.*;
import java.nio.file.*;

public class Digraph {
    // this is directional, only w is added to v, where v->w
    private List<Integer>[] adjacencyList;
    private int V;
    private int E = 0;

    public Digraph(int v) {
        V = v;
        initAdjList(vertices());
    }

    public Digraph(String filename) {
        try (Scanner sc = new Scanner(new FileInputStream(Paths.get(filename).toFile()))) {
            V = sc.nextInt();
            initAdjList(vertices());
            int edges = sc.nextInt();
            for (int i = 0; i < edges; i++) {
                addEdge(sc.nextInt(), sc.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create {@code adjacencyList}
     * 
     * @param v number of vertices
     */
    @SuppressWarnings("unchecked")
    private void initAdjList(int v) {
        adjacencyList = (ArrayList<Integer>[]) new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public List<Integer> adjacent(int v) {
        return adjacencyList[v];
    }

    /**
     * Add edge v->w, directional
     * 
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adjacencyList[v].add(w);
        E++;
    }

    public int vertices() {
        return V;
    }

    public int edges() {
        return E;
    }

    public Digraph reverse() {
        Digraph rd = new Digraph(vertices());
        for (int v = 0; v < vertices(); v++) {
            for (int w : adjacent(v)) {
                rd.addEdge(w, v);
            }
        }
        return rd;
    }

    @Override
    public String toString() {
        return String.format("%d Vertices, %d Edges:\n%s\n", vertices(), edges(),
                Arrays.toString(adjacencyList));
    }
}
