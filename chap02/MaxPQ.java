import java.util.*;

/**
 * Heapify - the process to re-structure the heap such that the fundamental
 * rules for a heap is fulfilled
 * 
 * sink() -> top-down approach to heapify a heap/ or PriorityQueue (internally
 * implemented using heap), it takes O(lgN) time
 * 
 * swim() -> bottom-up approach to heapify a heap/ or PriorityQueue, it takes
 * O(lgN) time
 * 
 * Both sink() and swim() are used to heapify, but sink() is suitable for
 * inserting new element, and swim() is suitable for removing an element (root).
 * 
 * For removing a Max item (based on priority), it takes O(1) time.
 * 
 * @param <K>
 */
public class MaxPQ<K extends Comparable<K>> {
    private K[] pq;
    private int n = 0;

    public static void main(String[] args) {
        Integer[] nums = new Integer[] { 1, 7, 3, 5, 2, 1 };
        System.out.println(Arrays.toString(nums));
        MaxPQ<Integer> pq = new MaxPQ<>(nums.length - 1); // nums.length - 1 is intended for testing resizeIfNecessary()
        for (int n : nums) {
            pq.insert(n);
            pq.show();
        }
        System.out.printf("Max: %d\n", pq.delMax());
        pq.show();
    }

    public MaxPQ(int capacity) {
        pq = (K[]) new Comparable[capacity + 1]; // pq[0] is not used
    }

    /**
     * Double the size when: size == pq.length - 1
     */
    private void resizeIfNecessary() {
        if (n == pq.length - 1) {
            int nsize = n * 2;
            K[] npq = (K[]) new Comparable[nsize];
            for (int i = 0; i <= n; i++)
                npq[i] = pq[i];
            pq = npq;
        }
    }

    // top-down approach to heapify the tree, better approach for root removal
    private void sink(int i) {
        int j; // left child
        while ((j = i * 2) <= n) {
            // let left child compares with right child, find the greatest among them
            if (j < n && lessThan(pq[j], pq[j + 1])) {
                j = j + 1; // right child
            }
            if (!lessThan(pq[i], pq[j])) { // compare with the grestest child (among left and right child nodes)
                break;
            }
            swap(i, j);
            i = j;
        }
    }

    // insert new element using swim-based approach to heapify the structure
    public void insert(K k) {
        resizeIfNecessary();
        pq[++n] = k;
        swim(n);
    }

    // bottom-up approach to heapify the tree, better than sink-based approach for
    // insertion
    private void swim(int i) {
        while (i > 1) { // while it's not root
            if (lessThan(pq[i / 2], pq[i])) { // swap with parent if necessary
                swap(i / 2, i);
                i = i / 2;
            } else {
                break;
            }
        }
    }

    public K max() {
        if (n >= 1) {
            --n;
            return pq[1];
        } else {
            throw new NoSuchElementException("Max Priority Queue Underflow");
        }
    }

    public K delMax() {
        if (n < 1)
            throw new NoSuchElementException("Max Priority Queue Underflow");
        K max = pq[1];
        swap(1, n); // swap the root with the last item
        pq[n--] = null; // make sure the last item is removed
        sink(1); // sink the new root (previously the last item)
        return max;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private boolean lessThan(K ka, K kb) {
        return ka.compareTo(kb) < 0;
    }

    private void swap(int a, int b) {
        K t = pq[a];
        pq[a] = pq[b];
        pq[b] = t;
    }

    public void show() {
        System.out.println(Arrays.toString(pq));
    }
}