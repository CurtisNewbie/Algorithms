
/**
 * @author yongjie.zhuang
 */
public interface SymbolTable<K extends Comparable<K>, V> {

    V get(K k);

    void put(K k, V v);

    V delete(K k);

    int size();
}


