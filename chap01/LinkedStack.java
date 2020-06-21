import java.util.Iterator;

public class LinkedStack<T> implements Stack<T> {

    private Node<T> head = null;
    private int size = 0;

    public LinkedStack() {
    }

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
        Node<T> nextHead = new Node<>(t);
        nextHead.next = head;
        head = nextHead;
    }

    @Override
    public T pop() {
        if (head == null) {
            return null;
        } else {
            size--;
            T t = head.val;
            head = head.next;
            return t;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<T> {

        private Node<T> root = head;

        private LinkedIterator() {
        }

        @Override
        public boolean hasNext() {
            return root != null;
        }

        @Override
        public T next() {
            if (root == null) {
                return null;
            } else {
                T t = root.val;
                root = root.next;
                return t;
            }
        }
    }
}
