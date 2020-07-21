import java.util.*;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph g) {
        mst = new LinkedList<>();
        PriorityQueue<Edge> minpq = new PriorityQueue<>(g.vertices());
        // put all edges to min priority queue to be sorted by weight
        for (Edge e : g.edges())
            minpq.add(e);

        // UnionFind to record vertices connected using an array root[] with weight[],
        // where root[i] is the parent of i, and weight[i] is the weight of subtree
        // rooted at i (for flattening the tree)
        WeightedQuickUnion uf = new WeightedQuickUnion(g.vertices());
        while (!minpq.isEmpty() && mst.size() < g.vertices() - 1) {
            Edge minEdge = minpq.poll();
            if (uf.connected(minEdge.v(), minEdge.w()))
                continue; // v-w already connected

            uf.union(minEdge.v(), minEdge.w()); // connect v-w
            mst.offer(minEdge); // add min edge to MST
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {
        String fname = "tinyEWG.txt";
        EdgeWeightedGraph weg = new EdgeWeightedGraph(fname);
        System.out.println(weg.toString());
        KruskalMST mst = new KruskalMST(weg);
        System.out.println("MST: " + mst.edges().toString());
    }
}