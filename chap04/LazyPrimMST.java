import java.util.LinkedList;
import java.util.*;

public class LazyPrimMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private PriorityQueue<Edge> minpq;

    public LazyPrimMST(EdgeWeightedGraph g) {
        marked = new boolean[g.vertices()];
        mst = new LinkedList<>();
        minpq = new PriorityQueue<>();
        visit(g, 0); // start from random vertex, but the graph should be connected
        int v, w;
        while (!minpq.isEmpty()) {
            Edge minEdge = minpq.poll(); // always use the edge with minimal weight
            v = minEdge.v();
            w = minEdge.w();

            if (marked[v] && marked[w]) // both vertices are in the MST already, skip
                continue;

            mst.offer(minEdge);
            // continue expanding MST
            if (!marked[v])
                visit(g, v);
            if (!marked[w])
                visit(g, w);
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adjacent(v)) {
            if (!marked[e.other(v)])
                minpq.add(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {
        String fname = "../demodata/tinyEWG.txt";
        EdgeWeightedGraph weg = new EdgeWeightedGraph(fname);
        System.out.println(weg.toString());
        LazyPrimMST mst = new LazyPrimMST(weg);
        System.out.println("MST: " + mst.edges().toString());
    }
}
