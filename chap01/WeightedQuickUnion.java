// This uses a weighted tree, simply put that, for two root nodes
// to be connected, the small tree is always connected to the 
// bigger tree, such that the tree's depth is dramatically decreased
// since it's weighted, all operations should be O(logN) instead of O(n^2)
// which are the height of the tree
public class WeightedQuickUnion implements UnionFind {

    private int[] root;
    private int[] weight;
    private int count;

    public WeightedQuickUnion(final int numOfElements) {
        root = new int[numOfElements];
        weight = new int[numOfElements];
        count = numOfElements;
    }

    @Override
    public int count() {
        return count;
    }

    // O(logN)
    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // O(logN)
    @Override
    public int find(int p) {
        while (root[p] != p)
            p = root[p];
        return p;
    }

    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;
        // small tree is connected to the larger tree
        if (weight[q] < weight[p]) {
            root[rootQ] = root[rootP];
            weight[rootP] += weight[rootQ];
        } else {
            root[rootP] = root[rootQ];
            weight[rootQ] += weight[rootP];
        }
        count--;
    }

}