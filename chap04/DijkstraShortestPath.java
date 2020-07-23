import java.util.*;

import edu.princeton.cs.algs4.IndexMinPQ;

public class DijkstraShortestPath {
    /**
     * Should be Double.POSITIVE_INFINITY for all vertices except {@code source} by
     * default
     */
    private double[] distTo;
    /** Should be null for all vertices by default */
    private Edge[] edgeTo;
    /** Starting point of SPT */
    private int source;
    /**
     * Vertices to be relaxed in ascending order based on their weights, index is
     * the id of vertices w, and key is the minimum distance to w
     */
    private IndexMinPQ<Double> minpq;

    public DijkstraShortestPath(EdgeWeightedDigraph g, int source) {
        this.source = source;
        distTo = new double[g.vertices()];
        edgeTo = new Edge[g.vertices()];
        minpq = new IndexMinPQ<>(g.vertices());
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        Arrays.fill(edgeTo, null);
        distTo[source] = 0.0;
        edgeTo[source] = null;

        minpq.insert(source, distTo[source]);
        while (!minpq.isEmpty()) { // vertices added are only relaxed for once
            // relax adjacent vertices,
            relax(g, minpq.delMin());
        }
    }

    /**
     * Relax Edge e
     * 
     * @param e edge
     */
    protected void relax(Edge e) {
        int v = e.v();
        int w = e.w();
        double vTow = distTo[v] + e.weight();
        // path->v->w is shorter than path->w
        if (distTo[w] > vTow) {
            distTo[w] = vTow;
            edgeTo[w] = e;

            // update minimum distance to w
            if (minpq.contains(w))
                minpq.changeKey(w, distTo[w]);
            else
                minpq.insert(w, distTo(w));
        }
    }

    /**
     * Relax all adjacent Edge on v (where v->w)
     *
     * @param g graph
     * @param v v in v->w
     */
    protected void relax(EdgeWeightedGraph g, int v) {
        for (Edge e : g.adjacent(v)) {
            relax(e);
        }
    }

    /**
     * Shortest distance from source to v
     * 
     * @param v
     * @return shortest distance from source to v
     */
    public double distTo(int v) {
        return distTo[v];
    }

    /**
     * Has Path from source to v
     * 
     * @param v
     * @return has path from source to v
     */
    public boolean hasPathTo(int v) {
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    /**
     * Return path from source to v, or empty list if there is no such path.
     * 
     * @param v v
     * @return path from source to v
     */
    public Iterable<Edge> pathTo(int v) {
        if (!hasPathTo(v))
            return new LinkedList<>();
        Deque<Edge> path = new LinkedList<Edge>();
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.v()]) { // from v move backward to source
            path.offerFirst(e);
        }
        return path;
    }

    /**
     * Return shortest path from source to all vertices
     */
    public List<Iterable<Edge>> shortestPaths() {
        List<Iterable<Edge>> paths = new ArrayList<>();
        for (int v = 0; v < edgeTo.length; v++) {
            paths.add(pathTo(v));
        }
        return paths;
    }

    public static void main(String[] args) {
        String fname = "tinyEWD.txt";
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(fname);
        System.out.println(fname + ", " + g.toString());

        DijkstraShortestPath dijikstra = new DijkstraShortestPath(g, 0);

        System.out.println("Shortest Paths to vertices: ");
        dijikstra.shortestPaths().forEach(ite -> System.out.println(ite));

        System.out.println("\nMinimum Distance to vertices: ");
        for (int v = 0; v < g.vertices(); v++) {
            System.out.printf("To %d: %.2f\n", v, dijikstra.distTo(v));
        }
    }
}