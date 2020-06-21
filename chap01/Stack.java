public interface Stack<T> extends Iterable<T> {

    boolean isEmpty();

    int size();

    void push(T t);

    T pop();
}
