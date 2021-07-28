import java.util.Arrays;

/**
 * @author yongjie.zhuang
 */
public class ReverseArray {

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.printf("Original: %s\n", Arrays.toString(a));
        reverse(a);
        System.out.printf("Reversed: %s\n", Arrays.toString(a));

    }

    /** In-place reversing */
    static void reverse(int[] a) {
        if (a == null || a.length < 2)
            return;

        for (int i = 0; i < a.length / 2; i++) {
            int j = a.length - 1 - i;
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}
