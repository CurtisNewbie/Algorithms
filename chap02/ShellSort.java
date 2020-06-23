public class ShellSort extends AbstractSort {

    public static void main(String[] args) {
        AbstractSort.test(new ShellSort());
    }

    @Override
    void sort(Comparable[] arr) {
        for (int gap = arr.length / 3; gap >= 1; gap /= 3) {
            for (int j = gap; j < arr.length; j++) {
                int curr = j;
                while (curr >= gap && lessThan(arr[curr], arr[curr - gap])) {
                    swap(arr, curr, curr - gap);
                    curr -= gap;
                }
            }
        }

    }

}