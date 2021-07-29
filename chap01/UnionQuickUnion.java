/**
 * UnionQuick implementation that treats the int[] array as a way to traverse back to each element's root. I.e., a tree
 * is created for each element
 */
public class UnionQuickUnion implements UnionFind {

    private int[] root;
    private int count;

    public UnionQuickUnion(final int numOfElements) {
        this.root = new int[numOfElements];
        for (int i = 0; i < numOfElements; i++) {
            root[i] = i; // points to itself
        }
        this.count = numOfElements;
    }

    // O(1)
    @Override
    public int count() {
        return count;
    }

    // worst case: O(n)
    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // worst case: O(n)
    @Override
    public int find(int p) {
        // find root, the root of p will be the one that points to itself,
        // i.e., root[p] -> p, or, root[3] == 3
        while (root[p] != p)
            p = root[p];
        return root[p];
    }

    // worst case: O(n)
    @Override
    public void union(int p, int q) {
        if (p == q) {
            // we can't connect same nodes
            return;
        }
        int rootP = find(p);
        int rootQ = find(q);

        // connect root of p to the tree rooted at q's root
        // i.e., connect these two sub-trees
        if (rootP != rootQ) {
            root[rootP] = rootQ;
            count--;
        }
    }
}