import java.util.*;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 6, 8, 9, 10, 13};
        System.out.println("Looking for 9");
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySearch(9, arr));
    }

    public static int binarySearch(int key, int[] arr) {
        int l = 0;
        int h = arr.length - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (arr[mid] < key) {
                l = mid + 1;
            } else if (arr[mid] > key) {
                h = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
