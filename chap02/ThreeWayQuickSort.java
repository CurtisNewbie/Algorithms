/**
 * This three-way quick sort still uses one pivot, but it maintains an extra
 * pointer that records the elements which equal to the pivot
 * 
 * This makes it different from traditional quicksort in that we only sort (next
 * recursion) those that are less than or greater than the pivot, and those
 * between [lp : hp] are already in their correct positions.
 * 
 * I.e., traditional quicksort only considers the pivot that is in the correct
 * position, and this implementation optimises it by taking into consideration
 * all elements that are equal to the pivot.
 * 
 * This implementation is quicker when there is a fair amount of duplicate
 * items, but when there are not, it may cause extra overhead.
 */
public class ThreeWayQuickSort extends QuickSort {

    @Override
    void qs(Comparable[] arr, int l, int h) {
        if (l >= h)
            return;

        // lp for v < pivot, i for v == pivot, hp for v > pivot
        int lp = l, i = l + 1, hp = h;
        Comparable v = arr[l];
        while (i <= hp) {
            int cmp = arr[i].compareTo(v);
            if (cmp < 0)
                swap(arr, lp++, i++);
            else if (cmp > 0)
                swap(arr, i, hp--);
            else
                i++;
        }
        // the result arr consists of three parts:
        // 1. [l : lp-1], wherein elements are less than pivot
        // 2. [lp : hp], wherein elements are equal to pivot
        // 3. [hp+1 : h], wherein elements are greater than pivot
        qs(arr, l, lp - 1);
        qs(arr, hp + 1, h);
    }
}