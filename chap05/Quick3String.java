import java.util.*;

/**
 * This is an improved algorithm built on top of LSD and MSD Radix Sort. It
 * solves major issues in MSD, such as:
 * <p>
 * 1. Duplicates issues (where MSD will compare all W characters in the group),
 * W refers to the length of the longest string in the group.
 * <p>
 * 2. long common prefix (similar to the previous one),
 * <p>
 * 3. huge space complexity (O(n) for aux[], and O(R) for each recursion, thus
 * O(n) + O(W*R)).
 * <p>
 * This algorithm is basically the same as the three-way quick sort. In general,
 * in each recursion, it picks a pivot, and divide the array into three parts,
 * 1. < pivot, 2. == pivot, 3. > pivot. For the parts that are not equal to
 * pivot, continues to next recursion without incrementing the {@code k} key
 * used for comparing. For the middle part that is equal to pivot, increments
 * the {@code k}, or i.e., compares next key.
 * <p>
 * Time complexity is still O(n*W) for the worst case, but in general, it's
 * pretty fast and it's in-place sorting.
 */
public class Quick3String {

    public static void main(String[] args) {
        String[] data = new String[] { "she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she",
                "sells", "are", "surely", "seashells" };
        System.out.println(Arrays.toString(data));
        sort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    public static void sort(String[] a, int l, int h, int k) {
        if (l > h)
            return;
        int pivot = charAt(a[l], k);
        int lp = l;
        int hp = h;
        int ep = l + 1; // pointer for values equal to pivot
        while (ep <= hp) {
            int epv = charAt(a[ep], k);
            if (epv < pivot) {
                swap(a, lp++, ep++); // move backward
            } else if (epv > pivot) {
                swap(a, ep, hp--); // ep not moved, because ep might not be sorted
            } else {
                ep++;
            }
        }
        // a[l...lp-1] are elements < pivot
        // a[lp...hp] are elements = pivot
        // a[hp+1...h] are elements > pivot
        sort(a, l, lp - 1, k);
        if (pivot >= 0) // end of strings, sorted
            sort(a, lp, hp, k + 1);
        sort(a, hp + 1, h, k);
    }

    private static int charAt(String s, int k) {
        return k < s.length() ? s.charAt(k) : -1;
    }

    private static void swap(String[] a, int i, int j) {
        String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}