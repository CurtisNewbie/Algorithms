import java.util.*;

public class DirectedCycle {
    private int[] parentOf;
    private boolean[] marked;
    private boolean[] onStack;
    private Stack<Integer> cycle = null;

    public DirectedCycle(Digraph g) {
        parentOf = new int[g.vertices()];
        marked = new boolean[g.vertices()];
        onStack = new boolean[g.vertices()];
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

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }


    public static void main(String[] args) {
        String fname = "dgCycle.txt";
        Digraph g = new Digraph(fname);
        System.out.println("Graph: " + fname);
        System.out.println(g.toString());
        DirectedCycle cycle = new DirectedCycle(g);
        System.out.printf("Has Cycle: %b\n", cycle.hasCycle());
        System.out.printf("Cycle: %s\n", cycle.cycle().toString());
    }
}