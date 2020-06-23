public class InsertionSort extends AbstractSort {

    public static void main(String[] args) {
        AbstractSort.test(new InsertionSort());
    }

    @Override
    void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    void sort(Comparable[] arr, int l, int h) {
        for (int i = l; i < h; i++) {
            int j = i + 1;
            while (j > 0 && lessThan(arr[j], arr[j - 1])) {
                swap(arr, j, j - 1);
                j--;
            }
        }

    }
}