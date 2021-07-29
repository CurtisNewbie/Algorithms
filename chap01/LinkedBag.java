import java.util.Iterator;
import java.util.function.Consumer;

/**
 * A Bag (Collections of items that may occur more than once (List is indexed Bag)
 *
 * @param <T>
 * @author yongjie.zhuang
 */
public class LinkedBag<T> implements Bag<T> {

    private int size = 0;
    private Node<T> head = null;

    @Override
    public void add(T t) {
        Node<T> n = new Node<>(t);
        n.next = head;
        head = n;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedBagIterator(head);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    private static class LinkedBagIterator<T> implements Iterator<T> {

        private Node<T> head;

        private LinkedBagIterator(Node<T> head) {
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
