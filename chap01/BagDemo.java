import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.Random;

/**
 * @author yongjie.zhuang
 */
public class BagDemo {

    public static void main(String[] args) {
        Bag<Integer> arrayBag = new ArrayBag<>();
        Bag<Integer> linkedBag = new LinkedBag<>();


        final int N = 30;
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            int r = rand.nextInt(10);
            arrayBag.add(r);
            linkedBag.add(r);
        }
        System.out.printf("ArrayBag Size == %d ? %b\n", N, arrayBag.size() == N);
        System.out.printf("LinkedBag Size == %d ? %b\n", N, linkedBag.size() == N);

        System.out.println("ArrayBag iterating:");
        Iterator<Integer> arrayBagIterator = arrayBag.iterator();
        while (arrayBagIterator.hasNext())
            System.out.print(arrayBagIterator.next() + " ");
        System.out.println("\n");

        System.out.println("LinkedBag iterating:");
        Iterator<Integer> linkedBagIterator = linkedBag.iterator();
        while (linkedBagIterator.hasNext())
            System.out.print(linkedBagIterator.next() + " ");

    }
}
