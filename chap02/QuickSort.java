import java.util.*;

public class QuickSort extends AbstractSort {

    public static void main(String[] args) {
        AbstractSort.test(new QuickSort());
    }

    @Override
    void sort(Comparable[] arr) {
        qs(arr, 0, arr.length - 1);
    }

    void qs(Comparable[] arr, int l, int h) {
        if (l < h) {
            int pivot = partition(arr, l, h);
            qs(arr, l, pivot - 1);
            qs(arr, pivot + 1, h);
        }
    }

    int partition(Comparable[] arr, int lower, int upper) {
        // pointers
        int lp = lower, hp = upper + 1;
        // the value to compare with
        Comparable pivot = arr[lower];

        while (true) {
            // find value greater than pivot
            while (lessThan(arr[++lp], pivot))
                if (lp == upper)
                    break;

            // find value less than pivot
            while (lessThan(pivot, arr[--hp]))
                if (hp == lower)
                    break;

            // pivot should be at hp
            if (lp >= hp)
                break;

            /*
             * swap them, such that the value less than pivot is on the left hand side
             * and the value greater than pivot is on the right hand side
             * */
            swap(arr, lp, hp);
        }
        swap(arr, lower, hp);
        return hp;
    }
}