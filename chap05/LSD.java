import java.util.*;

// LSD String Sort (AKA Radix Sort for strings)
public class LSD {

    public static void main(String[] args) {
        String[] data = new String[] { "4PGC938", "2IYE230", "3CI0720", "1ICK750", "10HV845", "4JZY524", "1ICK750",
                "3CI0720", "10HV845", "10HV835", "2RLA629", "2RLA629", "3ATW723" };
        System.out.println(Arrays.toString(data));
        sort(data, 7);
        System.out.println(Arrays.toString(data));
    }

    /**
     * Sort strings using LSD algorithm. The first {@code W} characters are used as
     * keys (only one char is used for each sorting) for sorting. This algorithm is
     * stable. O(W*N)
     * 
     * @param a string array
     * @param W the first W characters used for sorting, starting at W-1
     */
    public static void sort(String[] a, final int W) {
        // ASCII charset
        final int R = 256;
        String[] aux = new String[a.length];

        for (int k = W - 1; k >= 0; k--) {

            int[] count = new int[R + 1];
            // count frequency
            for (int i = 0; i < a.length; i++) {
                count[a[i].charAt(k) + 1]++;
            }
            // convert frequency to starting indices
            for (int i = 0; i < R; i++) {
                count[i + 1] += count[i];
            }
            // copy to temp
            for (int i = 0; i < a.length; i++) {
                aux[count[a[i].charAt(k)]++] = a[i];
            }
            // write back to the original array
            for (int i = 0; i < a.length; i++) {
                a[i] = aux[i];
            }
        }
    }
}