public class MergeSort extends AbstractSort {

    static Comparable[] cache;

    public static void main(String[] args) {
        AbstractSort.test(new MergeSort());
    }

    @Override
    void sort(Comparable[] arr) {
        // pre-allocate cache, such that the space complexity is always: O(n/2) => O(n)
        cache = new Comparable[arr.length / 2 + 1];
        ms(arr, 0, arr.length - 1);
    }

    void ms(Comparable[] arr, int l, int h) {
        if (l < h) {
            int m = l + (h - l) / 2;
            ms(arr, l, m);
            ms(arr, m + 1, h);
            merge(arr, l, m, h);
        }
    }

    void merge(Comparable[] arr, int l, int m, int h) {
        if (lessThan(arr[m], arr[m + 1])) // optimisation, when arr[m] < arr[m+1], its' already sorted
            return;

        int lowLen = m - l + 1;
        // we only need to make a copy of arr[l : m]
        for (int i = 0; i < lowLen; i++) {
            // optimisation using cache[], reduces space complexity
            cache[i] = arr[i + l];
        }

        // pointers
        int lp = 0;
        int hp = m + 1;
        int ap = l;
        while (lp < lowLen && hp <= h) {
            Comparable lc = cache[lp];
            Comparable hc = arr[hp];
            if (lessThan(lc, hc)) {
                arr[ap++] = lc;
                lp++;
            } else {
                arr[ap++] = hc;
                hp++;
            }
        }
        while (lp < lowLen) {
            arr[ap++] = cache[lp++];
        }
        while (hp <= h) {
            arr[ap++] = arr[hp++];
        }
    }
}