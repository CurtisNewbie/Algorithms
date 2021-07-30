import java.util.*;

public class BinarySearchTree<K extends Comparable<K>, V> implements SymbolTable<K, V> {

    private Node<K, V> root = null;
    private int size = 0;

    public static void main(String[] args) {
        BinarySearchTree<String, String> bst = new BinarySearchTree<>();
        bst.put("Curtis", "newbie");
        System.out.println(bst.show());
        bst.put("Banana", "mie");
        System.out.println(bst.show());
        bst.put("Doggy", "dog");
        System.out.println(bst.show());
        bst.put("Apple", "juice");
        System.out.println(bst.show());
        bst.put("France", "Ecnarf");
        System.out.println(bst.show());
        bst.put("Good", "Dog");
        System.out.println(bst.show());
        System.out.println("Size: " + bst.size());

        System.out.println("\nLook for Banana: " + bst.get("Banana"));
        System.out.println("Look for Apple: " + bst.get("Apple"));
        System.out.println("Min: " + bst.min());
        System.out.println("Max: " + bst.max());

        System.out.println("\nSize: " + bst.size());
        System.out.println("Delete France");
        bst.delete("France");
        System.out.println(bst.show());
        System.out.println("Size: " + bst.size());

        System.out.println("\nSize: " + bst.size());
        System.out.println("Delete Min: " + bst.min());
        bst.delMin();
        System.out.println(bst.show());
        System.out.println("Size: " + bst.size());

        System.out.println("Keys: " + bst.keys().toString());
        System.out.println("Keys From Apple to Doggy: " + bst.keys("Aapple", "Doggy").toString());
    }

    public Iterable<K> keys() {
        Queue<K> queue = new LinkedList<>();
        List<List<Object>> entries = new ArrayList<>();
        dfs(entries, root);
        for (List<Object> e : entries)
            queue.add((K) e.get(0));
        return queue;
    }

    public Iterable<K> keys(K l, K r) {
        Queue<K> queue = new LinkedList<>();
        if (l.compareTo(r) > 0) {
            return queue;
        }

        Node<K, V> curr = root;
        while (curr != null && curr.getKey().compareTo(r) <= 0) {
            int lres = curr.getKey().compareTo(l);
            if (lres >= 0)
                queue.offer(curr.getKey());

            curr = curr.getRight();
        }
        return queue;
    }

    public Node<K, V> min() {
        return leftMostNode(root);
    }

    public Node<K, V> leftMostNode(Node<K, V> node) {
        Node<K, V> curr = node;
        while (curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        return curr;
    }

    public Node<K, V> max() {
        return max(root);
    }

    public Node<K, V> max(Node<K, V> node) {
        Node<K, V> curr = node;
        while (curr.getRight() != null) {
            curr = curr.getRight();
        }
        return curr;
    }

    /**
     * Delete the minimum node (left-most) in the sub-tree rooted at {@code node} and return the current root of this
     * sub-tree after removal
     * <p>
     * For example:
     * <p>
     * {@code Node parent = ...;}
     * <p>
     * {@code parent.setRight(delMin(parent.getRight()));}
     * <p>
     * Deleting the minimum node in the sub-tree rooted at parent.getRight(). The method returns new root of the
     * sub-tree.
     * </p>
     *
     * @return new root of sub-tree after deletion
     */
    private Node<K, V> deleteLeftMost(Node<K, V> node) {
        // current node is the min node, delete current node
        if (node.getLeft() == null) {
            size--;
            // node.getRight() will be connected to the left node of its parent
            return node.getRight();
        } else {
            node.setLeft(deleteLeftMost(node.getLeft()));
            return node;
        }
    }

    public void delMin() {
        root = deleteLeftMost(root);
    }

    public V delete(K k) {
        return deleteNode(root, k).getValue();
    }

    /**
     * Delete a node
     *
     * @param startedAt
     * @param k
     * @return root of sub-tree after deletion
     */
    private Node<K, V> deleteNode(Node<K, V> startedAt, K k) {
        if (startedAt == null)
            return null;
        Node<K, V> curr = startedAt;
        int cmp = curr.getKey().compareTo(k);
        if (cmp < 0) {
            curr.setRight(deleteNode(curr.getRight(), k));
        } else if (cmp > 0) {
            curr.setLeft(deleteNode(curr.getLeft(), k));
        } else {
            // found, delete it by returning another node as the new root of sub-tree
            size--;
            if (curr.getRight() == null)
                return curr.getLeft();
            else if (curr.getLeft() == null)
                return curr.getRight();

            // min node under right sub-tree
            // replace current node with this rightMin
            Node<K, V> rightMin = leftMostNode(curr.getRight());
            rightMin.setRight(deleteLeftMost(curr.getRight()));
            rightMin.setLeft(curr.getLeft());
            return rightMin;
        }
        return curr;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(K k) {
        Node<K, V> node = getNode(k);
        return node != null ? node.getValue() : null;
    }

    public Node<K, V> getNode(K k) {
        Node<K, V> curr = root;
        int res;
        while (curr != null && (res = curr.getKey().compareTo(k)) != 0) {
            if (res < 0) {
                curr = curr.getRight();
            } else {
                curr = curr.getLeft();
            }
        }
        return curr;
    }

    public void put(K k, V v) {
        if (root == null) {
            root = new Node<>(k, v);
        } else {
            Node<K, V> curr = root;
            while (true) {
                int cmp = curr.getKey().compareTo(k);
                if (cmp == 0) { // replace
                    curr.setValue(v);
                    return;
                } else if (cmp < 0) {
                    if (curr.getRight() != null) {
                        curr = curr.getRight();
                    } else {
                        curr.setRight(new Node<K, V>(k, v));
                        break;
                    }
                } else {
                    if (curr.getLeft() != null) {
                        curr = curr.getLeft();
                    } else {
                        curr.setLeft(new Node<K, V>(k, v));
                        break;
                    }
                }
            }
        }
        size++;
    }

    public String show() {
        List<List<Object>> list = new ArrayList<>();
        dfs(list, root);
        return "DFS: " + list.toString();
    }

    private void dfs(List<List<Object>> list, Node<K, V> node) {
        if (node != null) {
            dfs(list, node.getLeft());
            list.add(Arrays.asList(node.getKey(), node.getValue()));
            dfs(list, node.getRight());
        }
    }

    @Override
    public String toString() {
        return show();
    }

}