/**
 * Different from SeparateChainingHashST, wherein, each [i] in M is a Linked-List alike structure. LinearProbingHashST
 * doesn't create a separate structure for each [i].
 * <p>
 * When collision occurs, it compares k with the key in [i], if key differs, it moves down to [i+1], repeats until it
 * finds one that matches its key or that is empty.
 */
public class LinearProbingHashST<K extends Comparable<K>, V> implements SymbolTable<K, V> {
    private int size = 0;
    private int M;
    private K[] keys;
    private V[] values;

    public static void main(String[] args) {
        LinearProbingHashST<Integer, String> lpst = new LinearProbingHashST<>();
        lpst.put(1, "Apple");
        lpst.put(2, "Banana");
        System.out.println("Has 1? " + lpst.contains(1));
        System.out.println("Get 1: " + lpst.get(1));
        lpst.put(1, "Lemon");
        System.out.println(lpst.toString());
        System.out.println("Replace 1 with Lemon: " + lpst.contains(1));
        lpst.delete(1);
        System.out.println("Delete 1: " + !lpst.contains(1));
        System.out.println(lpst.toString());
        System.out.println("Get 1: " + lpst.get(1));
    }

    public LinearProbingHashST(int capacity) {
        M = capacity;
        keys = (K[]) new Comparable[M];
        values = (V[]) new Comparable[M];
    }

    public LinearProbingHashST() {
        this(16);
    }

    private int hash(K k) {
        // 0x7fffffff is the largest value for int, which is essentially
        // 0111 1111 1111 1111 1111 1111 1111 1111
        // only the first leadng signed bit is 0, which guarantees that after the
        // bitwise AND operation, k.hashCode() is positive
        return (k.hashCode() & 0x7fffffff) % M;
    }

    public void resize() {
        final int capacity = M * 2;
        final LinearProbingHashST<K, V> t = new LinearProbingHashST<K, V>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], values[i]);
            }
        }
        this.keys = t.keys();
        this.values = t.values();
        this.M = capacity;
    }

    public boolean contains(K k) {
        int hashVal = hash(k);
        for (int i = hashVal; keys[i] != null; i = (i + 1) % M) {
            if (k.compareTo(keys[i]) == 0)
                return true;
        }
        return false;
    }

    public void put(K k, V v) {
        if (size >= M / 2) // a = 0.5 usage, i.e., 50%
            resize();

        int hashVal = hash(k);
        int i;
        for (i = hashVal; keys[i] != null; i = (i + 1) % M) {
            // replace value
            if (keys[i].compareTo(k) == 0) {
                values[i] = v;
                return;
            }
        }
        // found keys[i] == NULL
        keys[i] = k;
        values[i] = v;
        size++;
    }

    public V delete(K k) {
        int hashVal = hash(k);
        V v = null;
        int i = hashVal;
        // find item
        while (k.compareTo(keys[i]) != 0) {
            i = (i + 1) % M; // next
        }
        // delete item
        keys[i] = null;
        v = values[i];
        values[i] = null;

        // handle items behind it
        i = (i + 1) % M; // next
        while (keys[i] != null) {
            // re-insert
            K fk = keys[i];
            V fv = values[i];
            keys[i] = null;
            values[i] = null;
            --size;
            put(fk, fv);
            i = (i + 1) % M;
        }
        size--;
        return v;
    }

    public V get(K k) {
        int hashVal = hash(k);
        for (int i = hashVal; keys[i] != null; i = (i + 1) % M) {
            if (keys[i] == k) {
                return values[i];
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public V[] values() {
        return values;
    }

    public K[] keys() {
        return keys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < M ; i++) {
            sb.append("{ ").append(keys[i]).append(" : ").append(values[i]).append(" }");
            if (i < M - 1)
                sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}