import java.util.Iterator;

public class Bag<T> implements Iterable<T> {

    private Node<T> head = null;
    private int size = 0;

    public void add(T t) {
        Node<T> n = new Node<>(t);
        if (head == null) {
            head = n;
        } else {
            n.next = head;
            head = n;
        }
        size++;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<T> {

        private Node<T> root = head;

        @Override
        public boolean hasNext() {
            return root != null;
        }

        @Override
        public T next() {
            T t = root.val;
            root = root.next;
            return t;
        }
    }
}
