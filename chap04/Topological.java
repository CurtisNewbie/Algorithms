import java.util.Queue;

public class Topological {
    private Iterable<Integer> topologicalOrder = null;

    public Topological(Digraph g) {
        // Topological Order is a Reverse Post-Order of a DAG
        // thus, there must be no cycle in the graph
        if (!new DirectedCycle(g).hasCycle()) {
            topologicalOrder = new DepthFirstOrder(g).reversePostOrder();
        }
    }

    public Topological(EdgeWeightedDigraph g) {
        if (!new DirectedCycle(g).hasCycle()) {
            topologicalOrder = new DepthFirstOrder(g).reversePostOrder();
        }
    }

    public boolean isTopological() {
        return topologicalOrder != null;
    }

    public Iterable<Integer> order() {
        return topologicalOrder;
    }
}
