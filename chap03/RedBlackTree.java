import java.util.*;

/**
 * <p>
 * Red-black tree (2-3 tree)
 * </p>
 *
 * @author yongjie.zhuang
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root = null;

    public static void main(String[] args) {
        RedBlackTree<Character, Character> rbt = new RedBlackTree<>();
        char[] arr = new char[]{'S', 'E', 'A', 'R', 'C', 'H', 'X', 'M', 'P', 'L'};
        for (char c : arr)
            rbt.put(c, c);
        System.out.println(rbt.show());
        System.out.println(rbt.get('A'));
        System.out.println(rbt.get('G'));
        System.out.println(rbt.get('H'));
    }

    public V get(K k) {
        Node<K, V> n = find(k, root);
        return n != null ? n.getValue() : null;
    }

    private Node<K, V> find(K k, Node<K, V> root) {
        if (root == null)
            return null;
        int cmp = root.getKey().compareTo(k);
        if (cmp == 0) {
            return root;
        }
        if (cmp > 0)
            return find(k, root.getLeft());
        else
            return find(k, root.getRight());
    }

    /**
     * Rotate left, swap node {@code n} with its right child, and return its right child.
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
     * Rotate right, swap node {@code n} with its left child, and return its left child.
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

    private Node<K, V> put(Node<K, V> root, K k, V v) {
        if (root == null) {
            Node<K, V> node = new Node<>(k, v);
            node.isRed = true;
            return node;
        }

        int cmp = k.compareTo(root.getKey());
        if (cmp < 0)
            root.setLeft(put(root.getLeft(), k, v));
        else if (cmp > 0)
            root.setRight(put(root.getRight(), k, v));
        else // replace value
            root.setValue(v);

        return balance(root);
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