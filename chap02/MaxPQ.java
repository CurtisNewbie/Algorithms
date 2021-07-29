import java.util.*;

/**
 * Heapify - the process to re-structure the heap such that the fundamental rules for a heap is fulfilled
 * <p>
 * sink() -> top-down approach to heapify a heap/ or PriorityQueue (internally implemented using heap), it takes O(lgN)
 * time
 * <p>
 * swim() -> bottom-up approach to heapify a heap/ or PriorityQueue, it takes O(lgN) time
 * <p>
 * Both sink() and swim() are used to heapify, but sink() is suitable for inserting new element, and swim() is suitable
 * for removing an element (root).
 * <p>
 * For removing a Max item (based on priority), it takes O(1) time.
 *
 * @param <K>
 */
public class MaxPQ<K extends Comparable<K>> {
    /** Array that represents the Heap, pq[0] is always null, pq[1] is the root */
    private K[] pq;
    private int size = 0;

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1, 7, 3, 5, 2, 1, 8, 9, 12, 11, 1, 3, 4, 2, 3, 2, 1};
        System.out.println(Arrays.toString(nums));
        MaxPQ<Integer> pq = new MaxPQ<>(nums.length - 1); // nums.length - 1 is intended for testing resizeIfNecessary()
        for (int n : nums) {
            pq.insert(n);
            pq.print();
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.printf("Max: %d\n", pq.max());
        }
        pq.print();
    }

    public MaxPQ(int capacity) {
        pq = (K[]) new Comparable[capacity + 1]; // pq[0] is not used
    }

    /**
     * Double the size when: size == pq.length - 1
     */
    private void expandIfNecessary() {
        if (size == pq.length - 1) {
            pq = Arrays.copyOf(pq, size << 1);
        }
    }

    /**
     * Sink: top-down approach to heapify the tree
     * <p>
     * better approach for root removal
     * </p>
     *
     * @param parent (index) where to sink
     */
    private void sink(int parent) {
        int childMax;
        // do not exceed the boundary
        while ((childMax = leftChild(parent)) <= size) {
            int rightChild = childMax + 1;

            // if there is right child && the right child is greater
            if (childMax < size && lessThan(pq[childMax], pq[rightChild])) {
                childMax = rightChild;
            }

            /*
             * compare parent with the child nodes (max among lef and right)
             * if the parent is greater than or equal to child nodes
             * the heap is already heapified
             * */
            if (!lessThan(pq[parent], pq[childMax])) {
                break;
            }
            swap(parent, childMax);
            parent = childMax;
        }
    }

    /**
     * Insert new element using swim-based approach to heapify the structure
     *
     * @param k element
     */
    public void insert(K k) {
        expandIfNecessary();
        pq[++size] = k;
        swim(size);
    }

    /**
     * Swim: bottom-up approach to heapify the tree
     * <p>
     * better than sink-based approach for insertion
     * </p>
     *
     * @param node (index) where to swim (from child to parent, bottom -> up, usually, i is the current size)
     */
    private void swim(int node) {
        while (isNotRoot(node)) {
            int parent = parent(node);
            // child should be less than parent, if not, swap them
            if (lessThan(pq[parent], pq[node])) {
                swap(parent, node);
                node = parent;
            } else {
                break;
            }
        }
    }

    /**
     * Get the max as well as the first element in the PriorityQueue
     */
    public K max() {
        if (size < 1)
            throw new NoSuchElementException("Underflow");
        int root = 1;
        K max = pq[root];
        // swap the root with the last item (which is of min value)
        swap(root, size);
        // make sure the last item (previous root/max value) is removed
        pq[size--] = null;
        /*
         * sink the new root (previously the last item, or
         * the min item, move it downwards until we find
         * its correct position)
         *  */
        sink(root);
        return max;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private boolean lessThan(K ka, K kb) {
        return ka.compareTo(kb) < 0;
    }

    private void swap(int a, int b) {
        K t = pq[a];
        pq[a] = pq[b];
        pq[b] = t;
    }

    public void print() {
        System.out.println(Arrays.toString(pq));
    }

    private static int parent(int child) {
        return child / 2;
    }

    private static int leftChild(int parent) {
        return parent * 2;
    }

    private static boolean isNotRoot(int child) {
        return child > 1;
    }
}