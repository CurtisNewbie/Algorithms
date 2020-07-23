
public class DijkstraAllPairsSP {

    private DijkstraShortestPath[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph g) {
        all = new DijkstraShortestPath[g.vertices()];
        for (int i = 0; i < g.vertices(); i++) {
            all[i] = new DijkstraShortestPath(g, i); // create SPTs rooted at each vertex
        }
    }

    public Iterable<Edge> shortestPath(int from, int to) {
        return all[from].pathTo(to); // from is the source of SPT
    }

    public double shortestDist(int from, int to) {
        return all[from].distTo(to);
    }
}