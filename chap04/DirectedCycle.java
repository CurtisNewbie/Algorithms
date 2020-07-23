import java.util.*;

public class DirectedCycle {
    private int[] parentOf;
    private boolean[] marked;
    private boolean[] onStack;
    private Stack<Integer> cycle = null;

    private DirectedCycle(int vertices) {
        parentOf = new int[vertices];
        marked = new boolean[vertices];
        onStack = new boolean[vertices];
    }

    public DirectedCycle(Digraph g) {
        this(g.vertices());
        for (int v = 0; v < g.vertices(); v++)
            if (!marked[v])
                dfs(g, v);
    }

    public DirectedCycle(EdgeWeightedDigraph g) {
        this(g.vertices());
        for (int v = 0; v < g.vertices(); v++)
            if (!marked[v])
                dfs(g, v);
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        onStack[v] = true; // in current path
        for (int w : g.adjacent(v)) {
            if (hasCycle())
                return;
            else if (!marked[w]) {
                parentOf[w] = v;
                dfs(g, w);
            } else if (onStack[w]) { // cycle detected
                cycle = new Stack<>();
                for (int x = v; x != w; x = parentOf[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    private void dfs(EdgeWeightedDigraph g, int v) {
        marked[v] = true;
        onStack[v] = true; // in current path
        for (int w : g.adjVertices(v)) {
            if (hasCycle())
                return;
            else if (!marked[w]) {
                parentOf[w] = v;
                dfs(g, w);
            } else if (onStack[w]) { // cycle detected
                cycle = new Stack<>();
                for (int x = v; x != w; x = parentOf[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        String fname = "../demodata/dgCycle.txt";
        Digraph g = new Digraph(fname);
        System.out.println("Graph: " + fname);
        System.out.println(g.toString());
        DirectedCycle cycle = new DirectedCycle(g);
        System.out.printf("Has Cycle: %b\n", cycle.hasCycle());
        System.out.printf("Cycle: %s\n", cycle.cycle().toString());
    }
}
