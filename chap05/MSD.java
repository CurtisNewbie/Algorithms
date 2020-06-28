import java.util.*;

/**
 * Most Significant Digit (Radix Sort)
 * <p>
 * Differs from the LSD algorithm, MSD starts from the first character.
 * <p>
 * In LSD, to sort a list of strings, it starts from W-1 towards 0, putting the
 * strings in correct position in each iteration. Regardless of what the W is,
 * it should always at least sort the first character for these strings as the
 * last iteration, such that they are always sorted in terms of their first
 * character.
 * <p>
 * In MSD, instead of starting at W-1, it starts from 0 towards W-1, where the
 * strings are sorted using recursion rather than iteration. Once the strings
 * are sorted based on i(th) character, these strings are splited into
 * sub-groups based on their i(th) character.
 * <p>
 * In essence it adopts the divide-and-conquer way to do Radix Sort for strings.
 * All strings in sub-groups must have same previous characters, such that no
 * matter how they move within the group, their positions are still stable and
 * won't violate previous ordering.
 * <p>
 * MSD has disadvantages similar to quick sort, since it's a divide-and-conquer
 * algorithm, when the data are splited into very small groups (e.g., N=1), it
 * becomes very inefficient. Switching to insertion sort at some point is a
 * solution.
 */
public class MSD {
    private static final int R = 256;
    // switch to Insertion sort
    private static final int M = 15;
    // allocate a cache to minimise space complexity
    private static String[] aux;

    private MSD() {
    }

    public static void main(String[] args) {
        // String[] data = new String[] { "4PGC938", "2IYE230", "3CI0720", "1ICK750",
        // "10HV845", "4JZY524", "1ICK750",
        // "3CI0720", "10HV845", "10HV835", "2RLA629", "2RLA629", "3ATW723" };
        String[] data = new String[] { "she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "she",
                "sells", "are", "surely", "seashells" };
        System.out.println(Arrays.toString(data));
        sort(data);
        System.out.println(Arrays.toString(data));
    }

    private static int charAt(String s, int k) {
        return k < s.length() ? s.charAt(k) : -1;
    }

    public static void sort(String[] a) {
        aux = new String[a.length];
        sort(a, 0, a.length - 1, 0);
        aux = null;
    }

    /**
     * 
     * @param a array
     * @param l lower bound (inclusive)
     * @param h upper bound (inclusive)
     * @param k index of the char that is used as key for sorting
     */
    private static void sort(String[] a, int l, int h, int k) {
        if (h <= l + M) {
            // switch to insertion sort
            insertionSort(a, l, h, k);
            return;
        }
        int[] count = new int[R + 2];
        // occurance, +2 is to handle when k is out-of-bounary, when charAt() returns
        // -1, count[1]++, imagine 'a' == 0, then for 'a' group, j in count[j] will be
        // 2. Thus, count[0] is never used, and count[1] can be considered as the
        // reserved position for the strings that are out-of-boundary for k
        for (int i = l; i <= h; i++)
            count[charAt(a[i], k) + 2]++;
        // indices
        for (int r = 0; r < R + 1; r++)
            count[r + 1] += count[r];
        // copy and order, notice that the out-of-boundary groups are at aux[0]
        for (int i = l; i <= h; i++)
            aux[count[charAt(a[i], k) + 1]++] = a[i];
        // write back to original arr
        for (int i = l; i <= h; i++)
            a[i] = aux[i - l];

        // continue sorting, divide-and-conquer
        // notice that count[r] refers to the
        // amount of strings that has same charAt(k) + 2 character
        // say, for 'a', count['a' + 2] = 3, than,
        // the group for 'a': {l + 3, l + count['b' + 2] - 1}
        // if for 'b' there are 2 occurances: count['b' + 2] = count['a' + 2] + 2 = 5,
        // than 'a' group will be {l + 3, l + 5 - 1}, [l+3, l+4] inclusive.
        for (int r = 0; r < R; r++)
            sort(a, l + count[r], l + count[r + 1] - 1, k + 1);
    }

    /**
     * 
     * @param a array
     * @param l lower bound (inclusive)
     * @param h upper bound (inclusive)
     * @param k characters starting at k -> a[i].length() that are used for string
     *          comparison
     */
    private static void insertionSort(String[] a, int l, int h, int k) {
        for (int i = l; i <= h; i++) {
            for (int j = i; j > l && less(a[j], a[j - 1], k); j--)
                swap(a, j, j - 1);
        }
    }

    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    private static void swap(String[] a, int i, int j) {
        String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

}