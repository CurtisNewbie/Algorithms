import java.util.Iterator;

public class ArrayStack<T> implements Stack<T> {

    private static final int MIN_CAPACITY = 1 << 4;

    private T[] arr;
    private int size;

    public ArrayStack() {
        arr = (T[]) new Object[MIN_CAPACITY];
    }

    public ArrayStack(int capacity) {
        arr = (T[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newCapacity) {
        T[] ra = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            ra[i] = arr[i];
        }
        this.arr = ra;
    }

    private boolean isFull() {
        return this.size == arr.length;
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return arr.length;
    }

    @Override
    public void push(T s) {
        if (isFull()) {
            resize(capacity() << 1); // expand
        }
        arr[size++] = s;
    }

    @Override
    public T pop() {
        T t = arr[--size];
        arr[size] = null; // for GC
        if (capacity() > MIN_CAPACITY && size == capacity() >> 2)
            resize(capacity() >> 1); // shrink
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<T> {

        private int i = size;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return arr[--i];
        }
    }
}
