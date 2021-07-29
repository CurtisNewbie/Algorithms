import java.util.*;

public class RecurrBinarySearch {

    public static void main(String[] args) {
        int arr[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println(Arrays.toString(arr));
        System.out.println("Looking for 7");
        System.out.println(binarySearch(7, arr, 0, arr.length - 1));
    }

    // when using recurrsion, make sure the each recurrsion is solving
    // sub-problems that do not overlap with others, or else it will
    // overly-inefficient.
    public static int binarySearch(int key, int arr[], int l, int h) {
        if (l <= h) {
            int mid = l + (h - l) / 2;
            if (arr[mid] < key) {
                return binarySearch(key, arr, mid + 1, h);
            } else if (arr[mid] > key) {
                return binarySearch(key, arr, l, mid - 1);
            } else
                return mid;
        }
        return -1;
    }
}
