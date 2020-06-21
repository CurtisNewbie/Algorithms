import java.util.Iterator;

/**
 * tail IN, head OUT
 * <p>
 * HEAD -> node -> .... -> node -> TAIL
 */
public class Queue<T> implements Iterable<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void enqueue(T t) {
        size++;
        Node<T> n = new Node<>(t);
        if (head == null && tail == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
    }

    public T dequeue() {
        if (size == 0)
            return null;
        size--;
        T t = head.val;
        head = head.next;
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {

        private Node<T> n = head;

        @Override
        public boolean hasNext() {
            return n != null;
        }

        @Override
        public T next() {
            T t = n.val;
            n = n.next;
            return t;
        }
    }

}
