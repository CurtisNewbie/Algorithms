import java.util.Iterator;

/**
 * @author yongjie.zhuang
 */
public class StackDemo {

    public static void main(String[] args) {
        Stack<Integer> arrayStack = new ArrayStack<>();
        Stack<Integer> linkedStack = new LinkedStack<>();

        System.out.printf("ArrayStack is empty? %b\n", arrayStack.isEmpty());
        System.out.printf("LinkedStack is empty? %b\n", linkedStack.isEmpty());

        int N = 50;
        for (int i = 0; i < N; i++) {
            arrayStack.push(i);
            linkedStack.push(i);
        }
        System.out.println();

        System.out.printf("ArrayStack is empty? %b\n", arrayStack.isEmpty());
        System.out.printf("ArrayStack size: %d\n", arrayStack.size());
        System.out.printf("LinkedStack is empty? %b\n", linkedStack.isEmpty());
        System.out.printf("LinkedStack size: %d\n", arrayStack.size());
        System.out.println();

        System.out.println("Iterate ArrayStack:");
        Iterator<Integer> iterator = arrayStack.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
        System.out.println();

        System.out.println("Iterate LinkedStack:");
        iterator = linkedStack.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
        System.out.println();

        for (int i = 0; i < N; i++) {
            System.out.printf("ArrayStack pop: %d\n", arrayStack.pop());
            System.out.printf("LinkedStack pop: %d\n", linkedStack.pop());
        }
        System.out.println();

        System.out.printf("ArrayStack is empty? %b\n", arrayStack.isEmpty());
        System.out.printf("ArrayStack size: %d\n", arrayStack.size());
        System.out.printf("LinkedStack is empty? %b\n", linkedStack.isEmpty());
        System.out.printf("LinkedStack size: %d\n", arrayStack.size());
    }
}
