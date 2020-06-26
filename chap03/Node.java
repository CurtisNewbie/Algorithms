public class Node<K extends Comparable<K>, V> {

    private K k;
    private V v;
    private Node<K, V> left = null;
    private Node<K, V> right = null;
    /** Whether the colour of the path to its parent is red */
    public boolean isRed = false;

    public Node(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setLeft(Node<K, V> n) {
        left = n;
    }

    public void setRight(Node<K, V> n) {
        right = n;
    }

    public V getValue() {
        return v;
    }

    public void setValue(V v) {
        this.v = v;
    }

    public K getKey() {
        return k;
    }

    public void setKey(K k) {
        this.k = k;
    }

    @Override
    public String toString() {
        return String.format("{K: %s, V: %s}", k, v);
    }
}