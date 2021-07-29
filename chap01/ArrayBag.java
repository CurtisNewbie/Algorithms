import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * A Bag (Collections of items that may occur more than once (List is indexed Bag)
 *
 * @param <T>
 * @author yongjie.zhuang
 */
public class ArrayBag<T> implements Bag<T> {

    private Object[] array = new Object[1 << 4];
    private int ptr = 0;

    @Override
    public void add(T t) {
        if (isFull())
            resize();
        array[ptr++] = t;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return ptr;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayBagIterator(Arrays.copyOfRange(array, 0, ptr));
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    private void resize() {
        array = Arrays.copyOf(array, array.length << 2);
    }

    private boolean isFull() {
        return ptr == array.length - 1;
    }

    private static class ArrayBagIterator<T> implements Iterator<T> {

        private T[] objects;
        private int ptr = 0;

        private ArrayBagIterator(T[] objects) {
            this.objects = objects;
        }

        @Override
        public boolean hasNext() {
            return ptr < objects.length;
        }

        @Override
        public T next() {
            return objects[ptr++];
        }
    }
}
