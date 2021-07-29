import java.util.Iterator;

public class LinkedStack<T> implements Stack<T> {

    private Node<T> head = null;
    private int size = 0;

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(T t) {
        size++;
        Node<T> newHead = new Node<>(t);
        newHead.next = head;
        head = newHead;
    }

    @Override
    public T pop() {
        if (head == null) {
            return null;
        }

        T t = head.val;
        head = head.next;
        size--;
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<T> {

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
