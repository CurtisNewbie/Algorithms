public class ImprovedQuickSort extends QuickSort {

    private static final InsertionSort insertionSort = new InsertionSort();
    // 5 ~ 15
    private static final int INSERTION_SIZE = 5;

    public static void main(String[] args) {
        AbstractSort.test(new ImprovedQuickSort());
    }

    @Override
    void qs(Comparable[] arr, int l, int h) {
        if (h <= l + INSERTION_SIZE) {
            // for small partition, use insertion sort is faster
            insertionSort.sort(arr, l, h);
            return;
        }
        if (l < h) {
            int pivot = partition(arr, l, h);
            qs(arr, l, pivot - 1);
            qs(arr, pivot + 1, h);
        }
    }
}