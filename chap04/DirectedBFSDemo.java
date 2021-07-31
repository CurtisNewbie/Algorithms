
public class DirectedBFSDemo {

    public static void main(String[] args) {
        String fname = "/home/zhuangyongj/git/Algorithms/demodata/tinyDG.txt";
        Graph g = new Digraph(fname);
        System.out.println("Graph: " + fname);
        System.out.println(g.toString());

        for (int i = 0; i < g.vertices(); i++) {
            System.out.printf("Vertex: %d can reach to: '%s'\n", i, new BFSPaths(g, i).reachable().toString());
        }
        int v = 0;
        int w = 2;
        BFSPaths bfs = new BFSPaths(g, v);
        System.out.printf("Vertex: %d 's shortest path to %d: '%s'\n", v, w, bfs.pathTo(w).toString());
    }
}
