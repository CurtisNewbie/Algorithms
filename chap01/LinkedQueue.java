import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Enqueue at tail and dequeue at head
 * <p>
 * head -> node -> ... -> node -> tail
 * </p>
 * <p>
 * Dequeue: tail moves forward
 * </p>
 * <p>
 * Enqueue: head moves forward
 * </p>
 *
 * @author yongjie.zhuang
 */
public class LinkedQueue<T> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    @Override
    public void enqueue(T t) {
        Node<T> n = new Node<>(t);
        if (isEmpty()) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T t = head.val;
        head = head.next;
        if (head == null)
            tail = null;
        size--;
        return t;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedQueueIterator<>(head);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext())
            action.accept(iterator.next());
    }

    private static class LinkedQueueIterator<T> implements Iterator<T> {

        private Node<T> head;

        private LinkedQueueIterator(Node<T> head) {
            this.head = head;
        }

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public T next() {
            T t = head.val;
            head = head.next;
            return t;
        }
    }
}
