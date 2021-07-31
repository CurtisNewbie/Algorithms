/** Copy of Chap01/WeightedQuickUnion for compilation */
public class WeightedQuickUnionC {

    private int[] root;
    private int[] weight;
    private int count;

    public WeightedQuickUnionC(final int numOfElements) {
        root = new int[numOfElements];
        weight = new int[numOfElements];
        for (int i = 0; i < numOfElements; i++) {
            root[i] = i;
            weight[i] = 1;
        }
        count = numOfElements;
    }

    public int count() {
        return count;
    }

    // O(logN)
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // O(logN)
    public int find(int p) {
        while (root[p] != p)
            p = root[p];
        return p;
    }

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