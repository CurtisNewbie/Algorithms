public class SelectionSort extends AbstractSort {

    public static void main(String[] args) {
        AbstractSort.main(new SelectionSort());
    }

    @Override
    void sort(Comparable[] arr) {
        int min;
        for (int j = 0; j < arr.length; j++) {
            min = j;
            for (int i = j + 1; i < arr.length; i++) {
                if (lessThan(arr[i], arr[min]))
                    min = i;
            }
            swap(arr, min, j);
        }
    }
}