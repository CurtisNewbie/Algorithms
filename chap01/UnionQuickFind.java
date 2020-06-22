// first implementation that creates connected paths/nodes by setting the same value
// if the value of id[p] == id[q] that means they are connected
// union() is O(n) time, and find() and connected() are O(1)
public class UnionQuickFind implements UnionFind {

    private int[] id;
    private int count;

    public UnionQuickFind(final int numOfElements) {
        this.id = new int[numOfElements];
        count = numOfElements;
        for (int i = 0; i < numOfElements; i++) {
            id[i] = i;
        }
    }

    // o(1)
    @Override
    public int count() {
        return count;
    }

    // o(1)
    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // o(1)
    @Override
    public int find(int p) {
        return id[p];
    }

    // o(n)
    @Override
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId) // already connected
            return;

        for (int i = 0; i < id.length; i++) {
            // replace any id[i] that has a value of p, with q
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
        count--;
    }
}