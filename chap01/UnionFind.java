public interface UnionFind {

    /**
     * Number of components
     */
    int count();

    /**
     * Check if p and q are connected
     */
    boolean connected(int p, int q);

    /**
     * Find id of path that p is on
     */
    int find(int p);

    /**
     * Connect two nodes (p-q)
     */
    void union(int p, int q);
}