public class InsertionSort extends AbstractSort {

    public static void main(String[] args) {
        AbstractSort.test(new InsertionSort());
    }

    @Override
    void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int j = i + 1;
            while (j > 0 && lessThan(arr[j], arr[j - 1])) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }
}