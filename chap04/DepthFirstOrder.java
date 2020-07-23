import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DepthFirstOrder {
    private Queue<Integer> preOrder; // parent first
    private Queue<Integer> postOrder; // child first
    private Deque<Integer> reversePostOrder; // reverse of Post-Order
    private boolean[] marked;

    private DepthFirstOrder() {
        preOrder = new LinkedList<>();
        postOrder = new LinkedList<>();
        reversePostOrder = new LinkedList<>();
    }

    public DepthFirstOrder(Digraph g) {
        this();
        marked = new boolean[g.vertices()];
        for (int i = 0; i < g.vertices(); i++) {
            if (!marked[i])
                dfs(g, i);
        }
    }

    public DepthFirstOrder(EdgeWeightedDigraph g) {
        this();
        marked = new boolean[g.vertices()];
        for (int i = 0; i < g.vertices(); i++) {
            if (!marked[i])
                dfs(g, i);
        }
    }

    private void dfs(Digraph g, int v) {
        preOrder.offer(v);
        marked[v] = true;
        for (int w : g.adjacent(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        postOrder.offer(v);
        reversePostOrder.offerFirst(v);
    }

    private void dfs(EdgeWeightedDigraph g, int v) {
        preOrder.offer(v);
        marked[v] = true;
        for (int w : g.adjVertices(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        postOrder.offer(v);
        reversePostOrder.offerFirst(v);
    }

    public Iterable<Integer> preOrder() {
        return preOrder;
    }

    public Iterable<Integer> postOrder() {
        return postOrder;
    }

    public Iterable<Integer> reversePostOrder() {
        return reversePostOrder;
    }

}
