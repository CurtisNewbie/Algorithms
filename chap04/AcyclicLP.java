import java.util.*;

public class AcyclicLP {

    /**
     * Should be Double.NEGATIVE_INFINITY for all vertices except {@code source} by
     * default
     */
    protected double[] distTo;
    /** Should be null for all vertices by default */
    protected Edge[] edgeTo;

    public AcyclicLP(EdgeWeightedDigraph g, int source) {
        distTo = new double[g.vertices()];
        edgeTo = new Edge[g.vertices()];
        Arrays.fill(distTo, Double.NEGATIVE_INFINITY);
        Arrays.fill(edgeTo, null);
        distTo[source] = 0.0;

        // 1. Get topological order/ most-dependent
        Topological topo = new Topological(g);

        // 2. relax vertices based on topological order, since it's an DAG
        for (int v : topo.order())
            relax(g, v);
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
        // path->v->w is longer than path->w
        if (distTo[w] < vTow) {
            distTo[w] = vTow;
            edgeTo[w] = e;
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
     * Longest distance from source to v
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
     * Return longest path from source to all vertices
     */
    public List<Iterable<Edge>> longestPath() {
        List<Iterable<Edge>> paths = new ArrayList<>();
        for (int v = 0; v < edgeTo.length; v++) {
            paths.add(pathTo(v));
        }
        return paths;
    }

    public static void main(String[] args) {
        String fname = "../demodata/tinyEWDAG.txt";
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(fname);
        System.out.println(fname + ", " + g.toString());

        AcyclicLP acyclicLP = new AcyclicLP(g, 5);

        System.out.println("Longest Paths to vertices: ");
        acyclicLP.longestPath().forEach(ite -> System.out.println(ite));

        System.out.println("\nMaximum Distance to vertices: ");
        for (int v = 0; v < g.vertices(); v++) {
            System.out.printf("To %d: %.2f\n", v, acyclicLP.distTo(v));
        }
    }

}