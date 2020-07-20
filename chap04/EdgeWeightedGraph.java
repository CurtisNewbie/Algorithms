import java.util.*;
import java.io.*;
import java.nio.file.*;

public class EdgeWeightedGraph {

    private int V;
    private int E;
    private List<Edge>[] adjacencyList;

    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(String fname) {
        try (Scanner sc = new Scanner(new FileInputStream(Paths.get(fname).toFile()));) {
            this.V = sc.nextInt();
            this.E = 0;
            adjacencyList = (List<Edge>[]) new ArrayList[vertices()];
            initAdjList(vertices());
            int edges = sc.nextInt();
            for (int i = 0; i < edges; i++)
                addEdge(new Edge(sc.nextInt(), sc.nextInt(), sc.nextDouble()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initAdjList(int v) {
        for (int i = 0; i < vertices(); i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public Iterable<Edge> adjacent(int v) {
        return adjacencyList[v];
    }

    public void addEdge(Edge e) {
        adjacencyList[e.v()].add(e);
        adjacencyList[e.w()].add(e);
        E++;
    }

    public Iterable<Edge> edges(int v) {
        List<Edge> l = new ArrayList<>(E);
        for (List<Edge> el : adjacencyList)
            el.forEach(e -> l.add(e));
        return l;
    }

    public int vertices() {
        return V;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[\n");
        for (List<Edge> l : adjacencyList) {
            sb.append(" " + l.toString() + "\n");
        }
        sb.append("]");
        return String.format("Weghted Graph: %d V, %d E\n%s", V, E, sb.toString());
    }

    public static void main(String[] args) {
        String fname = "tinyEWG.txt";
        EdgeWeightedGraph weg = new EdgeWeightedGraph(fname);
        System.out.println(weg.toString());
    }
}
