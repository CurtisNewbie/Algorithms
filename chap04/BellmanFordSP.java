import java.util.*;

public class BellmanFordSP {
    /**
     * Should be Double.POSITIVE_INFINITY for all vertices except {@code source} by default
     */
    private double[] distTo;
    /** Should be null for all vertices by default */
    private Edge[] edgeTo;
    /** Starting point of SPT */
    private int source;
    /** Record whether v (onQueue[v]) is in the queue waiting to be relaxed */
    private boolean[] onQueue;
    /** Vertices in queue to be relaxed */
    private Queue<Integer> queue;
    /** Whether the graph has negative cycle (which should not exist in SPT) */
    private boolean hasNegativeCycle = false;
    /** Counter of relax(int) calls */
    private int relaxationCount = 0;

    public BellmanFordSP(EdgeWeightedDigraph g, int source) {
        this.source = source;
        distTo = new double[g.vertices()];
        edgeTo = new Edge[g.vertices()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        Arrays.fill(edgeTo, null);
        distTo[source] = 0.0;
        edgeTo[source] = null;

        onQueue = new boolean[g.vertices()];
        onQueue[source] = true;
        queue = new LinkedList<>();
        queue.offer(source); // relax source first
        while (!queue.isEmpty() && !hasNegativeCycle) { // negative cycle causes infinite recursion
            // remove it on queue, vertecises can be relaxed more than once, to make sure that there
            // is only valid path (shortest path in SPT)
            int v = queue.poll();
            onQueue[v] = false;
            relax(g, v); // relax vertices in queue in FIFO order
        }
    }

    /**
     * Relax Edge e
     * 
     * @param e edge
     */
    protected void relax(EdgeWeightedDigraph g, Edge e) {
        int v = e.v();
        int w = e.w();
        double vTow = distTo[v] + e.weight();
        // path->v->w is shorter than path->w
        if (distTo[w] > vTow) {
            distTo[w] = vTow;
            edgeTo[w] = e;

            if (!onQueue[w]) { // w hasn't be relaxed yet
                queue.offer(w);
                onQueue[w] = true;
            }
        }
        if (++relaxationCount % g.vertices() == 0) { // SPT might have been created
            // detects cycles in SPT, and SPT shouldn't have cycle
            if (findNegativeCycle()) {
                this.hasNegativeCycle = true;
            }
        }
    }

    /**
     * Relax all adjacent Edge on v (where v->w)
     *
     * @param g graph
     * @param v v in v->w
     */
    protected void relax(EdgeWeightedDigraph g, int v) {
        for (Edge e : g.adjacent(v)) {
            relax(g, e);
            if (hasNegativeCycle)
                return;
        }
    }

    /**
     * Shortest distance from source to v
     * 
     * @param v
     * @return shortest distance from source to v
     */
    public double distTo(int v) {
        if (hasNegativeCycle)
            throw new UnsupportedOperationException("Negative cycle exists");
        return distTo[v];
    }

    /**
     * Has Path from source to v
     * 
     * @param v
     * @return has path from source to v
     */
    public boolean hasPathTo(int v) {
        if (hasNegativeCycle)
            throw new UnsupportedOperationException("Negative cycle exists");
        return distTo[v] != Double.POSITIVE_INFINITY;
    }

    /**
     * Return path from source to v, or empty list if there is no such path.
     * 
     * @param v v
     * @return path from source to v
     */
    public Iterable<Edge> pathTo(int v) {
        if (hasNegativeCycle)
            throw new UnsupportedOperationException("Negative cycle exists");

        if (!hasPathTo(v))
            return new LinkedList<>();
        Deque<Edge> path = new LinkedList<Edge>();
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.v()]) { // from v move backward to source
            path.offerFirst(e);
        }
        return path;
    }

    /**
     * Should only be called when there is N times relaxation of vertices where N % V == 0. If there
     * is no negative cycle in the graph, the SPT created (after N times relaxation) should not have
     * cycle at all. This call is necessary to avoid the infinite recursion.
     */
    private boolean findNegativeCycle() {
        if (hasNegativeCycle)
            return hasNegativeCycle;

        int V = this.edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++)
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);
        return new DirectedCycle(spt).hasCycle();
    }

    public boolean hasNegativeCycle() {
        return this.hasNegativeCycle;
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

        // -------------------- Normal Edge Weighted Graph ----------------------
        String fname = "../demodata/tinyEWD.txt";
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(fname);
        System.out.println(fname + ", " + g.toString());

        BellmanFordSP mBellmanFordSP = new BellmanFordSP(g, 1);

        System.out.println("Shortest Paths to vertices: ");
        mBellmanFordSP.shortestPaths().forEach(ite -> System.out.println(ite));

        System.out.println("\nMinimum Distance to vertices: ");
        for (int v = 0; v < g.vertices(); v++) {
            System.out.printf("To %d: %.2f\n", v, mBellmanFordSP.distTo(v));
        }

        // --------------------- Negative Cycles won't have SPT -------------------
        System.out.println("\nDemo with negative cycles:");
        fname = "../demodata/tinyEWDnc.txt";
        EdgeWeightedDigraph gwnc = new EdgeWeightedDigraph(fname);
        System.out.println(fname + ", " + g.toString());
        BellmanFordSP bellmanFordSPnc = new BellmanFordSP(gwnc, 1);
        System.out.println("Has Negative Cycle: " + bellmanFordSPnc.hasNegativeCycle());
    }
}
