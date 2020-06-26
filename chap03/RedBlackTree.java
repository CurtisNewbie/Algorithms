import java.util.*;

/**
 * [Only Insert operation implemented]
 * <p>
 * Red Black Tree is an implementation of a 2-3 tree. A 2-3 tree can have 2-node
 * or 3-node, the self-balancing mechanism relies on the use of 2/3 node, and
 * the use of red/black implements this concept.
 * <p>
 * In general, when one is inserted into a correct position, if it's a 1-node,
 * inserting the node in it will make it a 2-node, which is balanced already.
 * <p>
 * However, if it's a 2-node, which means inserting the node in it will make it
 * a 3-node, and we need to balance it by moving the middle node up a level
 * until the tree is balanced. Thus, this is a bottom-up balancing approach.
 * <p>
 * This concept is hard to implement directly since there are various cases to
 * take care of. A red-black tree adopts the similar concept using a red/black
 * colour. Red path links two node making them a three node, and black path is
 * just a normal path.
 * <p>
 * - Red-paths are always paths on the left (paths to the left node).
 * <p>
 * - No one node can connect to two Red-paths simultaneously.
 * <p>
 * - Balck-paths are perfectly balanced.
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root = null;

    public static void main(String[] args) {
        RedBlackTree<Character, Character> rbt = new RedBlackTree<>();
        char[] arr = new char[] { 'S', 'E', 'A', 'R', 'C', 'H', 'X', 'M', 'P', 'L' };
        for (char c : arr)
            rbt.put(c, c);
        System.out.println(rbt.show());
    }

    /**
     * Rotate left, swap node {@code n} with its right child, and return its right
     * child.
     * 
     * @param n
     * @return correct child node of {@code n}'s parent
     */
    private Node<K, V> rotateLeft(Node<K, V> n) {
        Node<K, V> r = n.getRight();
        r.isRed = n.isRed;
        n.isRed = true;
        n.setRight(r.getLeft());
        r.setLeft(n);
        return r;
    }

    /**
     * Rotate right, swap node {@code n} with its left child, and return its left
     * child.
     * 
     * @param n
     * @return correct child node of {@code n}'s parent
     */
    private Node<K, V> rotateRight(Node<K, V> n) {
        Node<K, V> l = n.getLeft();
        l.isRed = n.isRed;
        n.isRed = true;
        n.setLeft(l.getRight());
        l.setRight(n);
        return l;
    }

    /**
     * Flip the colours of node {@code h} and its child nodes.
     * 
     * @param h
     */
    void flipColors(Node<K, V> h) {
        h.isRed = !h.isRed;
        h.getLeft().isRed = !h.getLeft().isRed;
        h.getRight().isRed = !h.getRight().isRed;
    }

    public void put(K k, V v) {
        root = put(root, k, v);
        // root is always black
        root.isRed = false;
    }

    private Node<K, V> put(Node<K, V> n, K k, V v) {
        if (n == null) {
            Node<K, V> nnode = new Node<>(k, v);
            nnode.isRed = true;
            return nnode;
        }

        int res = k.compareTo(n.getKey());
        if (res < 0)
            n.setLeft(put(n.getLeft(), k, v));
        else if (res > 0)
            n.setRight(put(n.getRight(), k, v));
        else // replace value
            n.setValue(v);

        return balance(n);
    }

    private Node<K, V> balance(Node<K, V> n) {
        // red nodes are always the left-most nodes, if the n.right is red, swap it to
        // left
        if (isRed(n.getRight()) && !isRed(n.getLeft()))
            n = rotateLeft(n);
        // same logic applies, red nodes are always the left-most nodes and there can be
        // at most 1 red node connected, exchange pos between n.left and n.left.left
        if (isRed(n.getLeft()) && isRed(n.getLeft().getLeft()))
            n = rotateRight(n);
        // if n.left and n.right are both red, than flip their colours, making them
        // black but the n becomes red
        if (isRed(n.getLeft()) && isRed(n.getRight()))
            flipColors(n);
        return n;
    }

    private boolean isRed(Node<K, V> n) {
        return n != null && n.isRed;
    }

    public String show() {
        List<Node<K, V>> list = new ArrayList<>();
        dfs(list, root);
        List<String> varr = new ArrayList<>();
        for (Node<K, V> n : list) {
            varr.add(n.getValue().toString());
        }
        return varr.toString();
    }

    private void dfs(List<Node<K, V>> list, Node<K, V> node) {
        if (node != null) {
            dfs(list, node.getLeft());
            list.add(node);
            dfs(list, node.getRight());
        }
    }
}