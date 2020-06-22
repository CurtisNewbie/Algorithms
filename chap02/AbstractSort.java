import java.util.*;

public abstract class AbstractSort {

    abstract void sort(Comparable[] arr);

    protected static void main(AbstractSort as) {
        String[] arr = new String[] { "cat", "bar", "ad", "address", "uber", "diss", "eww", "pda", "flower", "gucci",
                "iostream", "over" };
        show(arr);
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
}