/**
 * Chapter 5.1.1
 */
public class KeyIndexCounting {

    public static void main(String[] args) {
        // [0] -> key, [1] -> string (as value)
        Object[][] data = new Object[][] { { 2, "Anderson" }, { 3, "Brown" }, { 3, "Davis" }, { 4, "Garcia" },
                { 1, "Harris" }, { 3, "Jackson" }, { 4, "Johnson" }, { 3, "Jones" }, { 1, "Martin" } };
        System.out.println("Demo data: ");
        System.out.println(show(data));
        System.out.println("\nAfter grouping: ");
        System.out.println(show(group(data, 4)));
    }

    /**
     * 
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