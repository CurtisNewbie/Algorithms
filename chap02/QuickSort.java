import java.util.*;

public class QuickSort extends AbstractSort {

    public static void main(String[] args) {
        AbstractSort.test(new QuickSort());
    }

    @Override
    void sort(Comparable[] arr) {
        qs(arr, 0, arr.length - 1);
    }

    void qs(Comparable[] arr, int l, int h) {
        if (l < h) {
            int pivot = partition(arr, l, h);
            qs(arr, l, pivot - 1);
            qs(arr, pivot + 1, h);
        }
    }

    int partition(Comparable[] arr, int l, int r) {
        int pivot = l;
        int lp = pivot, hp = r + 1;
        while (true) {
            while (lessThan(arr[++lp], arr[pivot]) && lp < r)
                ;
            while (lessThan(arr[pivot], arr[--hp])) // we don't need to check hp > l, because arr[pivot] is arr[l]
                ;

            if (lp < hp)
                swap(arr, lp, hp);
            else
                break;
        }
        swap(arr, hp, pivot);
        return hp;
    }
}