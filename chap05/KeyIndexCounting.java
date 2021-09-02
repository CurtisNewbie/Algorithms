import java.util.Arrays;

/**
 * Chapter 5.1.1
 */
public class KeyIndexCounting {

    public static void main(String[] args) {
        // [0] -> key, [1] -> string (as value)
        Object[][] data = new Object[][]{{2, "Harris"}, {3, "Brown"}, {3, "Davis"}, {4, "Garcia"},
                {1, "Anderson"}, {3, "Jackson"}, {4, "Johnson"}, {3, "Jones"}, {1, "Martin"}};
        System.out.println("Demo data: ");
        System.out.println(show(data));
        System.out.println("\nAfter grouping: ");
        System.out.println(show(group(data, 4)));

        int[] arr = new int[]{0, 3, 4, 2, 3, 1, 4, 6, 5, 7, 8, 9, 3, 4, 5, 2, 1};
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(sort(arr, 10)));
    }

    /**
     * @param R number of groups
     * @return
     */
    public static Object[][] group(Object[][] data, int R) {
        // count frequency
        int[] count = new int[R + 2];
        for (int i = 0; i < data.length; i++) {
            // System.out.printf("i: %d, %d\n", i, (int) data[i][0] + 1);
            count[(int) data[i][0] + 1] += 1;
        }
        // convert frequency to the starting index of each group
        for (int i = 0; i < R; i++) {
            count[i + 1] += count[i];
        }
        // copy to temp array
        Object[][] aux = new Object[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            int index = count[(int) data[i][0]];
            aux[index][0] = data[i][0];
            aux[index][1] = data[i][1];
            count[(int) data[i][0]]++;
        }
        return aux;
    }

    public static int[] sort(int[] key, final int R) {
        int count[] = new int[R + 1];
        for (int i = 0; i < key.length; i++)
            count[key[i] + 1] += 1;

        for (int i = 0; i < R; i++)
            count[i + 1] += count[i];

        int[] sorted = new int[key.length];
        for (int i = 0; i < key.length; i++) {
            int pos = count[key[i]]++;
            sorted[pos] = key[i];
        }
        return sorted;
    }

    public static String show(Object[][] data) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < data.length; i++) {
            sb.append("[ ");
            sb.append((int) data[i][0] + ", ");
            sb.append((String) data[i][1]);
            sb.append(" ]");
        }
        sb.append("]");
        return sb.toString();
    }
}