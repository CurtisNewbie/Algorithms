import java.util.*;

public class BST<K extends Comparable<K>, V> {

    private Node<K, V> root = null;
    private int size = 0;

    public static void main(String[] args) {
        BST<String, String> bst = new BST<>();
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
        return min(root);
    }

    public Node<K, V> min(Node<K, V> node) {
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
     * Delete the minimum node (left-most) in the sub-tree rooted at {@code node}
     * and return a currect sub-node for the parent of {@code node} after
     * modification.
     * <p>
     * For example:
     * <p>
     * {@code Node parent = ...;}
     * <p>
     * {@code parent.setRight(delMin(parent.getRight()));}
     * <p>
     * Deleting the minimum node in the sub-tree rooted at parent.getRight(). The
     * method returns the correct sub-node of the parent of {@code node}, Since
     * parent.getRight() is the right child node of parent, we just act as if
     * nothing happens, and connect it back to its parent as the original right
     * child node.
     * <p>
     * If parent.getRight() is actually the minimum node, the right childe node of
     * parent.getRight() is returned instead, such that the tree is still correct
     * and the min node is removed.
     * 
     * @param node
     * @return
     */
    private Node<K, V> delMin(Node<K, V> node) {
        if (node.getLeft() == null) { // current node is the minimum node
            size--;
            return node.getRight(); // node.getRight() will be connected to the left node of its parent
        } else {
            node.setLeft(delMin(node.getLeft()));
            return node;
        }
    }

    public void delMin() {
        root = delMin(root);
    }

    public V delete(K k) {
        return deleteNode(root, k).getValue();
    }

    /**
     * Delete a node
     * <p>
     * E.g.,
     * <p>
     * D- to be deleted
     * <p>
     * R- right of D
     * <p>
     * RL- left of R
     * <p>
     * L- left of D
     * <p>
     * M- Min node in tree rooted at R
     * <p>
     * MR- Right node of Min
     * <p>
     * ____D____-____M____
     * <p>
     * __/___\__-__/___\__
     * <p>
     * _L_____R_-_L_____R_
     * <p>
     * ______/__-______/__
     * <p>
     * _____RL__-_____RL__
     * <p>
     * ____/____-____/____
     * <p>
     * ___M_____-___MR____
     * <p>
     * _____\___
     * <p>
     * ______MR_
     * <p>
     * 
     * @param node
     * @param k
     * @return
     */
    private Node<K, V> deleteNode(Node<K, V> node, K k) {
        int res = 0;
        if (node == null)
            return node;
        if ((res = node.getKey().compareTo(k)) < 0) {
            node.setRight(deleteNode(node.getRight(), k));
        } else if (res > 0) {
            node.setLeft(deleteNode(node.getLeft(), k));
        } else {
            size--;
            if (node.getRight() == null)
                return node.getLeft();
            else if (node.getLeft() == null)
                return node.getRight();

            Node<K, V> rightMin = node;
            rightMin = min(node.getRight()); // min node in right sub-tree
            // delMin() returns itself if node.getRight() isn't the min node
            // and it reconnects parent of min to the right of min
            rightMin.setRight(delMin(node.getRight()));
            rightMin.setLeft(node.getLeft());
            return rightMin; // min node in the right subtree has now replaced the previous node
        }
        return node;
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
            int res;
            while (true) {
                res = curr.getKey().compareTo(k);
                if (res == 0) { // replace
                    curr.setValue(v);
                    return;
                } else if (res < 0) {
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

}