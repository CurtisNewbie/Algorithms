import java.util.Iterator;

/**
 * @author yongjie.zhuang
 */
public class QueueDemo {

    public static void main(String[] args) {
        Queue<Integer> linkedQueue = new LinkedQueue<>();
        System.out.printf("LinkedQueue is empty? %b\n", linkedQueue.isEmpty());

        linkedQueue.enqueue(1);
        linkedQueue.enqueue(2);
        linkedQueue.enqueue(3);

        System.out.printf("LinkedQueue is empty? %b\n", linkedQueue.isEmpty());
        System.out.printf("LinkedQueue's size: %d\n", linkedQueue.size());

        System.out.println("Iterate LinkedQueue: ");
        Iterator<Integer> iterator = linkedQueue.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
        System.out.println();

        System.out.printf("Dequeue: %d\n", linkedQueue.dequeue());
        System.out.printf("Dequeue: %d\n", linkedQueue.dequeue());
        System.out.println();

        System.out.println("Iterate LinkedQueue: ");
        iterator = linkedQueue.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
    }

}
