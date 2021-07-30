import java.util.Arrays;

/**
 * @author yongjie.zhuang
 */
public class BinarySearchST<K extends Comparable<K>, V> implements SymbolTable<K, V> {

    private K[] keys;
    private V[] values;
    private int size = 0;

    public BinarySearchST(int capacity) {
        this.keys = (K[]) new Comparable[capacity];
        this.values = (V[]) new Object[capacity];
    }

    public BinarySearchST() {
        this(1 << 4);
    }


    @Override
    public V get(K k) {
        if (k == null)
            return null;
        if (size == 0)
            return null;

        int pos = binarySearch(k, 0, size - 1);
        if (pos < size && keys[pos].compareTo(k) == 0)
            return values[pos];
        else
            return null;
    }

    /** Find the correct position */
    private int binarySearch(K k, int l, int h) {
        while (l <= h) {
            int m = l + (h - l) / 2;
            int cmp = keys[m].compareTo(k);
            if (cmp == 0)
                return m;
            else if (cmp > 0)
                h = m - 1;
            else
                l = m + 1;

        }
        return l;
    }

    @Override
    public void put(K k, V v) {
        if (size == 0) {
            keys[0] = k;
            values[0] = v;
            size++;
            return;
        }

        int pos = binarySearch(k, 0, size - 1);
        if (pos < size && keys[pos].compareTo(k) == 0) {
            values[pos] = v;
            return;
        }
        moveToRight(pos);
        keys[pos] = k;
        values[pos] = v;
        size++;
    }

    /** Move all elements after pos (including pos) to right 1 step */
    private void moveToRight(int pos) {
        if (size + 1 < keys.length)
            expand();
        for (int i = size; i > pos; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
    }

    @Override
    public V delete(K k) {
        if (size == 0)
            return null;
        int pos = binarySearch(k, 0, size - 1);

        V v = null;
        if (pos < size && keys[pos].compareTo(k) == 0) {
            v = values[pos];
            moveToLeft(pos);
            values[size] = null;
            keys[size] = null;
            size--;
        }
        return v;
    }

    /** Move all elements after pos to left 1 step, the pos element is overwritten */
    private void moveToLeft(int pos) {
        for (int i = pos; i < size; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void expand() {
        int capacity = keys.length * 2;
        this.keys = Arrays.copyOf(keys, capacity);
        this.values = Arrays.copyOf(values, capacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append("{ ").append(keys[i]).append(" : ").append(values[i]).append(" }");
            if (i < size - 1)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
