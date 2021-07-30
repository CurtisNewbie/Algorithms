import java.util.Arrays;

public class SeparateChainingHashST<K extends Comparable<K>, V> implements SymbolTable<K, V> {
    private final int M;
    private SequentialSearchST<K, V>[] st;
    private int size = 0;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int size) {
        this.M = size;
        st = (SequentialSearchST<K, V>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(K k) {
        return (k.hashCode() & 0x7fffffff) % M;
    }

    public V get(K k) {
        return (V) st[hash(k)].get(k);
    }

    public void put(K k, V v) {
        SequentialSearchST<K, V> seqt = st[hash(k)];
        int beforeSize = seqt.size();
        seqt.put(k, v);
        if (seqt.size() > beforeSize)
            size++;
    }

    public V delete(K k) {
        SequentialSearchST<K, V> seqt = st[hash(k)];
        int beforeSize = seqt.size();
        V v = seqt.delete(k);
        if (seqt.size() < beforeSize)
            size--;
        return v;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "SeparateChainingHashST{" +
                "M=" + M +
                ", st=" + Arrays.toString(st) +
                ", size=" + size +
                '}';
    }
}