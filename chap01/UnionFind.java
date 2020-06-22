public interface UnionFind {

    int count();

    boolean connected(int p, int q);

    int find(int p);

    void union(int p, int q);

}