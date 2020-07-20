import java.util.*;
import edu.princeton.cs.algs4.*;

public class PrimMST {

    private boolean[] marked;
    private double[] minWeightTo; // min weight from MST to vertex
    private Edge[] edgeTo;
    private IndexMinPQ<Double> minpq; // indexed Min PriorityQueue, where the index is the vertex

    public PrimMST(EdgeWeightedGraph g) {
        marked = new boolean[g.vertices()];
        minWeightTo = new double[g.vertices()];
        edgeTo = new Edge[g.vertices()];
        for (int i = 0; i < g.vertices(); i++) {
            minWeightTo[i] = Double.POSITIVE_INFINITY;
        }
        minpq = new IndexMinPQ<>(g.vertices());

        // Starts at 0
        minWeightTo[0] = 0.0;
        minpq.insert(0, minWeightTo[0]); // vertex 0 with 0.0 weight
        while (!minpq.isEmpty())
            visit(g, minpq.delMin());
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adjacent(v)) {
            int w = e.other(v);
            if (marked[w])
                continue;
            if (e.weight() < minWeightTo[w]) { // record the min weight to w
                edgeTo[w] = e; // v-w is the optimal edge for v and w in MST
                minWeightTo[w] = e.weight();
                if (minpq.contains(w))
                    minpq.changeKey(w, minWeightTo[w]);
                else
                    minpq.insert(w, minWeightTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        List<Edge> l = new ArrayList<>();
        for (int i = 1; i < edgeTo.length; i++) {
            l.add(edgeTo[i]);
        }
        return l;
    }

    public static void main(String[] args) {
        String fname = "tinyEWG.txt";
        EdgeWeightedGraph weg = new EdgeWeightedGraph(fname);
        System.out.println(weg.toString());
        PrimMST mst = new PrimMST(weg);
        System.out.println("MST: " + mst.edges().toString());
    }
}
