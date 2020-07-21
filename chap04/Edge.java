/**
 * Undirected Edge or Directed Edge (implementation is basically the same)
 */
public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * Return v in v-w or v->w
     */
    public int v() {
        return v;
    }

    /**
     * Return w in v-w or v->w
     */
    public int w() {
        return w;
    }

    public double weight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight() > o.weight())
            return 1;
        else if (this.weight() < o.weight())
            return -1;
        else
            return 0;
    }

    /**
     * Return other vertex in this edge
     */
    public int other(int vertex) {
        if (v == vertex)
            return w;
        else if (w == vertex)
            return v;
        else
            throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
