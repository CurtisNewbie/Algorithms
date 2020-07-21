import java.util.*;

public class EdgeWeightedDigraph extends EdgeWeightedGraph {

    public EdgeWeightedDigraph(String filename) {
        super(filename);
    }

    @Override
    public void addEdge(Edge e) {
        adjacencyList[e.v()].add(e);
        E++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[\n");
        for (List<Edge> l : adjacencyList) {
            sb.append(" " + l.toString() + "\n");
        }
        sb.append("]");
        return String.format("Weghted Digraph: %d V, %d E\n%s", V, E, sb.toString());
    }

}