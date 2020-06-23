import java.util.*;

public abstract class AbstractSort {

    abstract void sort(Comparable[] arr);

    protected static void main(AbstractSort as) {
        final int N = 100;
        // generated random integers, using AbstractSort#randomIntegers
        Integer[] arr = new Integer[] { 62, 57, 94, 97, 18, 77, 24, 99, 69, 53, 56, 24, 7, 50, 12, 77, 8, 8, 72, 94, 82,
                41, 65, 22, 4, 35, 97, 31, 18, 76, 11, 24, 90, 48, 19, 64, 92, 61, 31, 51, 55, 16, 59, 79, 25, 11, 31,
                23, 17, 79, 11, 12, 92, 59, 70, 85, 61, 12, 88, 6, 22, 80, 19, 27, 77, 42, 54, 62, 19, 79, 37, 34, 12,
                19, 4, 71, 60, 7, 54, 23, 35, 48, 53, 55, 8, 30, 17, 67, 80, 46, 59, 8, 13, 10, 54, 64, 46, 6, 68, 20 };
        show(arr);
        System.out.println();
        as.sort(arr);
        if (!isSorted(arr))
            System.out.println("Not Sorted!!!");
        show(arr);
    }

    /**
     * Return whether v is less than w
     */
    protected static boolean lessThan(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected static void swap(Comparable[] a, int i, int j) {
        if (i == j)
            return;
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    protected static void show(Comparable[] a) {
        System.out.println(Arrays.toString(a));
    }

    /**
     * Return whether the arr is in descending order
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (lessThan(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    private static int[] randomIntegers(final int N) {
        int arr[] = new int[N];
        Random r = new Random();
        for (int i = 0; i < N; i++) {
            arr[i] = r.nextInt(N);
        }
        return arr;
    }
}