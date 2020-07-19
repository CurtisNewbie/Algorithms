import java.util.*;
import java.io.*;
import java.nio.file.*;

/**
 * Graph for integers starting at 0.
 */
public class Graph {
    private int V;
    private int E = 0;
    private List<Integer>[] adjacencyList = null;

    @SuppressWarnings("unchecked")
    public Graph(int v) {
        this.V = v;
        adjacencyList = (ArrayList<Integer>[]) new ArrayList[vertices()];
        initAdjList(vertices());
    }

    @SuppressWarnings("unchecked")
    public Graph(Path path) {
        try (Scanner sc = new Scanner(new FileInputStream(path.toFile()));) {
            this.V = sc.nextInt();
            adjacencyList = (ArrayList<Integer>[]) new ArrayList[vertices()];
            initAdjList(vertices());
            int edges = sc.nextInt();
            for (int i = 0; i < edges; i++)
                addEdge(sc.nextInt(), sc.nextInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initAdjList(int v) {
        for (int i = 0; i < vertices(); i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    /**
     * Return number of vertices
     */
    public int vertices() {
        return V;
    }

    /**
     * Return number of edges
     */
    public int edges() {
        return E;
    }

    /**
     * Add Edge between v-w. O(1)
     */
    public void addEdge(int v, int w) {
        // mark w adjacent for v
        adjacencyList[v].add(w);
        // mark v adjacent for w
        adjacencyList[w].add(v);
        E++;
    }

    /**
     * Return neighbours (adjacent vertices)
     */
    public List<Integer> adjacent(int v) {
        return adjacencyList[v];
    }

    /**
     * Degree of a vertex
     */
    public int degree(int v) {
        return adjacent(v).size();
    }

    /**
     * Max degree in graph
     */
    public int maxDegree() {
        int max = 0;
        int d;
        for (int i = 0; i < vertices(); i++) {
            d = degree(i);
            max = d > max ? d : max;
        }
        return max;
    }

    /**
     * Average degree in graph
     */
    public double avgDegree() {
        return 2.0 * edges() / vertices();
    }

    /**
     * Return number of self loops (e.g., v->v)
     */
    public int numOfSelfLoops() {
        int count = 0;
        for (int v = 0; v < vertices(); v++) {
            for (int w : adjacent(v))
                if (v == w)
                    ++count;
        }
        return count / 2;
    }

    @Override
    public String toString() {
        return String.format("%d Vertices, %d Edges:\n%s\n", vertices(), edges(),
                Arrays.toString(adjacencyList));
    }
}

