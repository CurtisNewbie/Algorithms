import java.util.*;

public class KosarajuSCC {
    private boolean[] marked;
    private List<List<Integer>> scc;
    private int[] sccIds;
    private int count = 0;

    /**
     * 1. Reverse Graph
     * <p>
     * 2. Get topological order (reverse post-order)
     * <p>
     * 3. Traverse the RPO order using standard DFS.
     * <p>
     * SCC are elements found in a single dfs() call (including its own recursion)
     */
    public KosarajuSCC(Digraph g) {
        marked = new boolean[g.vertices()];
        scc = new ArrayList<>();
        sccIds = new int[g.vertices()];
        Iterable<Integer> reversePostOrder = new DepthFirstOrder(g.reverse()).reversePostOrder();
        for (int v : reversePostOrder) {
            if (!marked[v]) {
                List<Integer> l = new ArrayList<Integer>();
                dfs(g, v, l, ++count);
                scc.add(l);
            }
        }

    }

    private void dfs(Digraph g, int v, List<Integer> scc, int sccId) {
        marked[v] = true;
        scc.add(v);
        sccIds[v] = count;
        for (int w : g.adjacent(v)) {
            if (!marked[w])
                dfs(g, w, scc, sccId);
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return sccIds[v] == sccIds[w];
    }

    public int sccIdOf(int v) {
        return sccIds[v];
    }

    public List<List<Integer>> sccs() {
        return scc;
    }

    public List<Integer> sccOfId(int id) {
        return scc.get(id);
    }

    public int count() {
        return count;
    }

    public int[] ids() {
        return sccIds;
    }

    public static void main(String[] args) {
        String fname = "../demodata/tinyDG.txt";
        Digraph g = new Digraph(fname);
        System.out.printf("Graph: %s\n%s", fname, g.toString());
        KosarajuSCC kosaraju = new KosarajuSCC(g);
        System.out.println("Ids: " + Arrays.toString(kosaraju.ids()));
        System.out.println("SCC: ");
        for (List<Integer> scc : kosaraju.sccs()) {
            System.out.println(scc.toString());
        }
    }
}
