/**
 * @author yongjie.zhuang
 */
public interface Queue<T> extends Iterable<T> {

    void enqueue(T t);

    T dequeue();

    boolean isEmpty();

    int size();

}
