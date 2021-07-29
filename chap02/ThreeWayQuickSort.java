/**
 * This three-way quick sort still uses one pivot, but it maintains an extra pointer that records the elements which
 * equal to the pivot
 * <p>
 * This makes it different from traditional quicksort in that we only sort (next recursion) those that are less than or
 * greater than the pivot, and those between [lp : hp] are already in their correct positions.
 * <p>
 * I.e., traditional quicksort only considers the pivot that is in the correct position, and this implementation
 * optimises it by taking into consideration all elements that are equal to the pivot.
 * <p>
 * This implementation is quicker when there is a fair amount of duplicate items, but when there are not, it may cause
 * extra overhead.
 */
public class ThreeWayQuickSort extends AbstractSort {

    public static void main(String[] args) {
        AbstractSort.test(new QuickSort());
    }

    void qs(Comparable[] arr, int l, int h) {
        if (l >= h)
            return;

        // lp for v < pivot, mp for v == pivot, hp for v > pivot
        int lp = l, mp = l + 1, hp = h;

        Comparable pivot = arr[l];

        while (mp <= hp) {
            int cmp = arr[mp].compareTo(pivot);
            if (cmp < 0)
                swap(arr, lp++, mp++);
            else if (cmp > 0)
                swap(arr, mp, hp--);
            else
                mp++;
        }

        // 1. [l : lp-1], elements are less than pivot
        // 2. [lp : hp], elements are equal to pivot
        // 3. [hp+1 : h], elements are greater than pivot
        qs(arr, l, lp - 1);
        qs(arr, hp + 1, h);
    }

    @Override
    void sort(Comparable[] arr) {
        qs(arr, 0, arr.length - 1);
    }
}