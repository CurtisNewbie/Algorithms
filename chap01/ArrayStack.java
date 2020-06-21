import java.util.Iterator;

public class ArrayStack<T> implements Stack<T> {
    private T[] a;
    private int size;

    public ArrayStack(int fixedCapacity) {
        this.a = (T[]) new Object[fixedCapacity];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newCapacity) {
        T[] ra = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            ra[i] = a[i];
        }
        this.a = ra;
    }

    private boolean isFull() {
        return this.size == a.length;
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return a.length;
    }

    @Override
    public void push(T s) {
        if (isFull()) {
            resize(a.length * 2);
        }
        a[size++] = s;
    }

    @Override
    public T pop() {
        T t = a[--size];
        a[size] = null; // for GC
        if (size > 0 && size == a.length / 4)
            resize(a.length / 2);
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<T> {

        private int i = size;

        private ArrayStackIterator() {
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return a[--i];
        }
    }
}
