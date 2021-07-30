import java.util.*;

public class SequentialSearchST<K extends Comparable<K>, V> implements SymbolTable<K, V> {

    private Node<K, V> root;
    private int size = 0;

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
        size++;
    }

    @Override
    public String toString() {
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
                size--;
                return curr.getValue();
            }
            prev = curr;
            curr = curr.getNext();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}