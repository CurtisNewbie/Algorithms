/**
 * @author yongjie.zhuang
 */
public class UnionFindDemo {

    public static void main(String[] args) {
        int[][] nodes = new int[][]{
                {4, 3},
                {3, 8},
                {6, 5},
                {9, 4},
                {2, 1},
                {8, 9},
                {5, 0},
                {7, 2},
                {6, 1},
                {1, 0},
                {6, 7}
        };
        UnionFind quickUnion = new UnionQuickUnion(10);
        UnionFind quickFind = new UnionQuickFind(10);
        UnionFind weightedQuickUnion = new WeightedQuickUnion(10);

        for (int i = 0; i < 10; i++) {
            quickFind.union(nodes[i][0], nodes[i][1]);
            quickUnion.union(nodes[i][0], nodes[i][1]);
            weightedQuickUnion.union(nodes[i][0], nodes[i][1]);
        }

        System.out.printf("Quick Union, components: %d\n", quickUnion.count());
        System.out.printf("Quick Find, components: %d\n", quickFind.count());
        System.out.printf("Weighted Quick Union, components: %d\n", weightedQuickUnion.count());
        System.out.println();

        int p = 2, q = 4;
        System.out.printf("Quick Union, %d and %d is connected: %b\n", p, q, quickUnion.connected(p, q));
        System.out.printf("Quick Find, %d and %d is connected: %b\n", p, q, quickFind.connected(p, q));
        System.out.printf("Weighted Quick Union, %d and %d is connected: %b\n", p, q, weightedQuickUnion.connected(p, q));
        System.out.println();

        p = 3;
        q = 4;
        System.out.printf("Quick Union, %d and %d is connected: %b\n", p, q, quickUnion.connected(p, q));
        System.out.printf("Quick Find, %d and %d is connected: %b\n", p, q, quickFind.connected(p, q));
        System.out.printf("Weighted Quick Union, %d and %d is connected: %b\n", p, q, weightedQuickUnion.connected(p, q));

    }
}
