import java.util.*;

public class SequentialSearchST<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public static void main(String[] args) {
        SequentialSearchST<Character, Integer> ssst = new SequentialSearchST<>();
        Object[][] data = new Object[][] { { 'S', 0 }, { 'E', 1 }, { 'A', 2 }, { 'R', 3 }, { 'C', 4 } };
        for (Object[] r : data) {
            ssst.put((Character) r[0], (Integer) r[1]);
            System.out.println(ssst.show());
        }
        System.out.println("Delete 'S'");
        ssst.delete('S');
        System.out.println(ssst.show());
    }

    public V get(K k) {
        Node<K, V> curr = root;
        while (curr != null) {
            if (curr.getKey().compareTo(k) == 0)
                return curr.getValue();
            curr = curr.getNext();
        }
        return null;
    }

    public void put(K k, V v) {
        Node<K, V> curr = root;
        while (curr != null) {
            if (curr.getKey().compareTo(k) == 0) {
                curr.setValue(v);
                return;
            }
            curr = curr.getNext();
        }
        curr = new Node<K, V>(k, v);
        curr.setNext(root);
        root = curr;
    }

    public String show() {
        List<String> list = new ArrayList<>();
        Node<K, V> curr = root;
        while (curr != null) {
            list.add(curr.getKey() + ":" + curr.getValue());
            curr = curr.getNext();
        }
        return list.toString();
    }

    public V delete(K k) {
        Node<K, V> prev = null;
        Node<K, V> curr = root;
        while (curr != null) {
            if (curr.getKey().compareTo(k) == 0) {
                if (prev == null) {
                    root = curr.getNext();
                } else {
                    prev.setNext(curr.getNext());
                }
                return curr.getValue();
            }
            prev = curr;
            curr = curr.getNext();
        }
        return null;
    }
}