public class SeparateChainingHashST<K extends Comparable<K>, V> {
    private int n;
    private final int M;
    private SequentialSearchST<K, V>[] st;

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
        st[hash(k)].put(k, v);
    }

    public V delete(K k) {
        var ss = st[hash(k)];
        return ss.delete(k);
    }
}