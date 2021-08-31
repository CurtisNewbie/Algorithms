
/**
 * B-Tree
 *
 * @param <K>
 */
public class Btree<K extends Comparable<K>, V> {

    /** max children per b-tree node = m-1 */
    private static final int MAX_CHILD_COUNT_PER_NODE = 4;

    private Node<K, V> root;
    private int height;
    private int size;

    public Btree() {
        root = new Node(0);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int height() {
        return height;
    }

    public V get(K k) {
        if (k == null)
            throw new IllegalArgumentException();
        return search(root, k, height);
    }

    private V search(Node parent, K k, int height) {
        Entry<K, V>[] children = parent.children;

        // external node (nodes that don't have children, e.g., outer node, leaf node or terminal node)
        final boolean isExternalNode = height == 0;
        if (isExternalNode) {
            // loop over the children
            for (int j = 0; j < parent.childCount; j++) {
                if (k.compareTo(children[j].k) == 0) {
                    return children[j].v;
                }
            }
        } else {
            // internal node (have children)
            for (int j = 0; j < parent.childCount; j++) {
                if (j == parent.childCount - 1 || k.compareTo(children[j + 1].k) < 0) {
                    return search(children[j].next, k, height - 1);
                }
            }
        }
        return null;
    }

    public void put(K k, V v) {
        if (k == null)
            throw new IllegalArgumentException();
        Node<K, V> up = insert(root, k, v, height);
        size++;

        // no need to split
        if (up == null)
            return;

        final int hf = MAX_CHILD_COUNT_PER_NODE / 2;

        // new Root has two children (because max children limit is 4)
        // both of them don't have children, one points to previous root
        // another points to the node that is pushed upward after splitting
        Node newRoot = new Node(hf);
        newRoot.children[0] = new Entry(root.children[0].k, null, root);
        newRoot.children[1] = new Entry(up.children[0].k, null, up);
        root = newRoot;
        height++;
    }

    private Node<K, V> insert(Node<K, V> parent, K k, V v, int height) {
        int j;
        Entry<K, V> t = new Entry(k, v, null);

        boolean isExternalNode = height == 0;
        if (isExternalNode) {
            for (j = 0; j < parent.childCount; j++) {
                // from left to right, keys in ascending order
                boolean isKeyLtChild = k.compareTo(parent.children[j].k) < 0;
                if (isKeyLtChild) {
                    break;
                }
            }
        } else {
            for (j = 0; j < parent.childCount; j++) {
                boolean isLastChild = j == parent.childCount - 1;
                // is last child node, or we found the correct position
                if (isLastChild || k.compareTo(parent.children[j + 1].k) < 0) {
                    Node<K, V> newSubRoot = insert(parent.children[j++].next, k, v, height - 1);

                    // no need to split the tree
                    if (newSubRoot == null)
                        return null;

                    // the tree is split, because the max child count per node is exceeded
                    t.k = newSubRoot.children[0].k;
                    t.v = null;
                    t.next = newSubRoot;
                    break;
                }
            }
        }

        // move every node after i towards right, because i is the correct position of inserted node
        for (int i = parent.childCount; i > j; i--) {
            parent.children[i] = parent.children[i - 1];
        }
        // current level added a new node, so we increment childCount
        parent.children[j] = t;
        parent.childCount++;

        // no need to split, everything in tact
        if (parent.childCount < MAX_CHILD_COUNT_PER_NODE)
            return null;
        else
            return split(parent);
    }

    private Node<K, V> split(Node<K, V> parent) {
        final int hf = MAX_CHILD_COUNT_PER_NODE / 2;

        // t is node pushed upward after splitting
        Node<K, V> newSubRoot = new Node<>(hf);

        // set the parent node as only having half of the node, and copy the rest of it to t
        parent.childCount = hf;

        // copy half of the node
        for (int j = 0; j < hf; j++)
            newSubRoot.children[j] = parent.children[hf + j];

        // avoid memory leak
        for (int j = hf + 1; j < MAX_CHILD_COUNT_PER_NODE; j++) {
            parent.children[j] = null;
        }
        return newSubRoot;
    }

    public static class Node<K, V> {
        private int childCount;
        private Entry<K, V>[] children = new Entry[MAX_CHILD_COUNT_PER_NODE];

        public Node(int childCount) {
            this.childCount = childCount;
        }

    }

    public static class Entry<K, V> {
        private K k;
        private V v;
        private Node next;

        public Entry(K k, V v, Node next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }


}

