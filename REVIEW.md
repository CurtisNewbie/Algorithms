# Book Review

## Chapter 1

### 1.1 GCD (Greatest Common Divisor) Algorithm

(p.1)

Algorithm to calculate the greatest common divisor:

Recursive implementation:

```
int gcd(int a, int b) {
    if (b == 0)
        return a;
    return gcd(b, a % b);
}
```

Iterative implementation:

```
int gcd(int a, int b) {
    while (b != 0) {
        int t = b;
        b = a % b;
        a = t;
    }
    return a;
}
```

### 1.2 Reverse Array

(p.11)

```
void reverse(int[] a) {
    if (a == null || a.length < 2)
        return;

    for (int i = 0; i < a.length / 2; i++) {
        int j = a.length - 1 - i;
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
```

### 1.3 Check Prime Number

(p.13)

Prime numbers are whole numbers greater than 1, that have only two factors : 1 and the number itself. `N` within {2, 3} are prime numbers, so this loop starts from 4 `(i = 2 and i * i <= N)`, when the number can be divided by a number other than 1 or itself, return false.

```
boolean isPrime(int N) {
    if (N < 2)
        return false;
    for (int i = 2; i * i <= N; i++) {
        if (N % i == 0)
            return false;
    }
    return true;
}
```

### 1.4 Calculate Sqaure Root

(p.13)

Newton's method, move towards the sqaure root, until the estimate is close enough to be considered as a square root.

```
double sqrt(double c) {
    if (c < 0)
       return Double.NaN;

    double err = 1e-15; // this is for precision
    double root = c;

    // we are estimating until we consider it to be close enough
    while (Math.abs(root - c / root) > err * root) {

        // moving closer towards the root value
        root = (c / root + root) / 2.0;
    }
    return root;
}
```

For the precision part, we can also change it a bit as follows for better understanding. 1e-15 is much more precise than 1e-5, and will yield differet result. For example, for `varient_sqrt(24)`: when `err = 1`, it yields a result of 4.911996, but when `err = 1e-5`, it yields a result of 4.898979.

```
double varient_sqrt(double c) {
    if (c < 0)
        return Double.NaN;

    double err = 1e-5;
    double prevGuess = c;
    double root = c;

    while (true) {
        root = (c / root + root) / 2.0;
        if (Math.abs(root - prevGuess) < err)
            break;
        prevGuess = root;
    }
    return root;
}
```

### 1.5 Binary Searching

(p.28)

**Iterative Binary Searching**

```
int binarySearch(int key, int[] arr) {
    int l = 0;
    int h = arr.length - 1;
    while (l <= h) {
        int mid = l + (h - l) / 2;
        if (arr[mid] < key) {
            l = mid + 1;
        } else if (arr[mid] > key) {
            h = mid - 1;
        } else {
            return mid;
        }
    }
    return -1;
}
```

**Recursive Binary Searching**

```
int binarySearch(int key, int arr[], int l, int h) {
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
```

### 1.6 Bag

(p.74)

Bag is a collection in which items may occur more than once, bag doesn't support deleting items, it just collects items. List is an index Bag. Bag can be implemented using linked nodes or array.

**Linked style Bag**

```
public class LinkedBag<T> implements Bag<T> {

    private int size = 0;
    private Node<T> head = null;

    @Override
    public void add(T t) {
        Node<T> n = new Node<>(t);
        n.next = head;
        head = n;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedBagIterator(head);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    private static class LinkedBagIterator<T> implements Iterator<T> {

        private Node<T> head;

        private LinkedBagIterator(Node<T> head) {
            this.head = head;
        }

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public T next() {
            T t = head.val;
            head = head.next;
            return t;
        }
    }
}
```

**Array style Bag**

```
public class ArrayBag<T> implements Bag<T> {

    private Object[] array = new Object[1 << 4];
    private int ptr = 0;

    @Override
    public void add(T t) {
        if (isFull())
            resize();
        array[ptr++] = t;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return ptr;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayBagIterator(Arrays.copyOfRange(array, 0, ptr));
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    private void resize() {
        array = Arrays.copyOf(array, array.length << 2);
    }

    private boolean isFull() {
        return ptr == array.length - 1;
    }

    private static class ArrayBagIterator<T> implements Iterator<T> {

        private T[] objects;
        private int ptr = 0;

        private ArrayBagIterator(T[] objects) {
            this.objects = objects;
        }

        @Override
        public boolean hasNext() {
            return ptr < objects.length;
        }

        @Override
        public T next() {
            return objects[ptr++];
        }
    }
}
```

### 1.7 Queue

Queue is First-In-First-Out ADT. If the implementation is singly-linked queue (as below); nodes will be enqueued at tail, the old tail points to the new node, and the new node becomes the tail; and nodes will be dequeued at head, and head.next becomes the new head, so the old head is simply unlinked.

When the queue is emtpy, and a node is added, both the head and the tail will point to the new node, in this case, the queue is simply a 1 length linked list. When the queue has only one single node, both the head and tail points to the same node just like how we have added the first node.

If one tries to dequeue a value, then the head will be null, because the old head's next is null, in such case, we need to set the tail as null as well (remember, now both the head and tail points to the same node, if the head is now dequeued and becomes null, the tail will still be pointing to the node that we just dequeued), because in an empty queue, both the head and tail should be null.

**Head and Tail:**

- Head -> node -> ... -> node -> Tail

(p.95)

```
public class LinkedQueue<T> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    @Override
    public void enqueue(T t) {
        Node<T> n = new Node<>(t);
        if (isEmpty()) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T t = head.val;
        head = head.next;
        if (head == null)
            tail = null;
        size--;
        return t;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedQueueIterator<>(head);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext())
            action.accept(iterator.next());
    }

    private static class LinkedQueueIterator<T> implements Iterator<T> {

        private Node<T> head;

        private LinkedQueueIterator(Node<T> head) {
            this.head = head;
        }

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public T next() {
            T t = head.val;
            head = head.next;
            return t;
        }
    }
}
```

### 1.8 Stack

Stack is LIFO Last-In-First-Out ADT.

**Linked style Stack**:

```
public class LinkedStack<T> implements Stack<T> {

    private Node<T> head = null;
    private int size = 0;

    public LinkedStack() {
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(T t) {
        size++;
        Node<T> nextHead = new Node<>(t);
        nextHead.next = head;
        head = nextHead;
    }

    @Override
    public T pop() {
        if (head == null) {
            return null;
        } else {
            size--;
            T t = head.val;
            head = head.next;
            return t;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<T> {

        private Node<T> root = head;

        private LinkedIterator() {
        }

        @Override
        public boolean hasNext() {
            return root != null;
        }

        @Override
        public T next() {
            T t = root.val;
            root = root.next;
            return t;
        }
    }
}

```

**Array style Stack:**

```
public class ArrayStack<T> implements Stack<T> {

    private static final int MIN_CAPACITY = 1 << 4;

    private T[] arr;
    private int size;

    public ArrayStack() {
        arr = (T[]) new Object[MIN_CAPACITY];
    }

    public ArrayStack(int capacity) {
        arr = (T[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int newCapacity) {
        T[] ra = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            ra[i] = arr[i];
        }
        this.arr = ra;
    }

    private boolean isFull() {
        return this.size == arr.length;
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return arr.length;
    }

    @Override
    public void push(T s) {
        if (isFull()) {
            resize(capacity() << 1); // expand
        }
        arr[size++] = s;
    }

    @Override
    public T pop() {
        T t = arr[--size];
        arr[size] = null; // for GC
        if (capacity() > MIN_CAPACITY && size == capacity() >> 2)
            resize(capacity() >> 1); // shrink
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<T> {

        private int i = size;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return arr[--i];
        }
    }
}
```

### 1.9 Dijkstra Double Stack

In this example each number and operators are delimited by a single space, and each `(...)` can only contain 1 operator and two operands. For example `"( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"`.

One stack is used for operator, and another is used for values. We simply ignores `(` because it represents the start of a prioritised expression, only when we meet `)`, we know that the expression is now finished, and we need to calculate the result. We first pop operator out, as well as the two operands (values), and then we do the calculation, and then, we again push the result back to the stack.

E.g., for situation like `"(1 + 2) * 3"`, after we handled the `(1 + 2)`, we push the result 3 back to the value stack, then we imagine it being `"3 * 3"`, where the first 3 is already pushed into the stack.

(p.79)

```
public static double interpret(String expr) {
    String[] tokens = expr.split(" ");
    Stack<String> operator = new Stack<>();
    Stack<Double> values = new Stack<>();

    for (String token : tokens) {
        if (token.trim().isEmpty() || token.equals("("))
            continue;

        if (token.equals("+")
                || token.equals("-")
                || token.equals("*")
                || token.equals("/")){
            operator.push(token);
        } else if (token.equals(")")) {
            String oper = operator.pop();
            double operandLeft = values.pop();
            if (oper.equals("+"))
                operandLeft += values.pop();
            else if (oper.equals("-"))
                operandLeft -= values.pop();
            else if (oper.equals("*"))
                operandLeft *= values.pop();
            else if (oper.equals("/"))
                operandLeft /= values.pop();
            else
                throw new IllegalStateException("Unknown operator: " + oper);
            values.push(operandLeft);
        } else {
            values.push(Double.parseDouble(token));
        }
    }
    return values.pop();
}
```

### 1.10 Union Find Algorithm

"A disjoint-set data structure, also called a union–find data structure or merge–find set, is a data structure that stores a collection of disjoint (non-overlapping) sets."

A Union find algorithm may be used to find whether there is connection between the two sets S1 and S2, such that it connects the node A in S1 and the node B in S2.

A graph can be seperated as two sets (two groups of nodes), so a Union-Find algorithm might also be used for graphs.

#### 1.10.1 API of an Union-Find Algorithm

If A connects to B and B connects to C, A connects to C, and A, B, C all share the same path (as well as the id of the path). In order words, if `find(A) == find(B)`, then we know A is connected to B.

```
public interface UnionFind {

    /**
     * Number of components (connected nodes)
     */
    int count();

    /**
     * Check if p and q are connected
     */
    boolean connected(int p, int q);

    /**
     * Find id of path that p is on
     */
    int find(int p);

    /**
     * Connect two nodes (p-q)
     */
    void union(int p, int q);
}
```

#### 1.10.2 Quick-Find Algorithm

The algorithm is as follows:

1. assign an unique id to each node
2. when we connect two nodes (calling `union(p,q)`), we set the id of `q` to the id of `p`, such that the connected nodes all share the same id.
3. the `find(p)` method is basically returning the id of `p`.
4. the `connected(p,q)` is basically checking if `find(p) == find(q)`.

```
public class UnionQuickFind implements UnionFind {

    private int[] id;
    private int count;

    public UnionQuickFind(final int numOfElements) {
        this.id = new int[numOfElements];
        count = numOfElements;
        for (int i = 0; i < numOfElements; i++) {
            id[i] = i;
        }
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int find(int p) {
        return id[p];
    }

    @Override
    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId) // already connected
            return;

        for (int i = 0; i < id.length; i++) {
            // replace any id[i] that has a value of p, with q
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
        count--;
    }
}
```

#### 1.10.3 Quick-Union Algorithm

The difference between Quick-Find and Quick-Union algorithms are that, Quick-Find algorithm maintains an id of path of each nodes, such that the connected nodes share the same id; however, the Quick-Union maintains a root of each node, so the `find(p)` returns the root node of `p`, if `find(p) == find(q)`, they are connected, it also means they are on the same tree. If a node is not connected to any other nodes, then as we might guess `find(p) = p`.

The algorithm is as follows:

1. maintain an array of root node for each node, each node's root node is the node itself
2. method `find(p)` returns the root node of `p`, by recursively doing `root_p = root[root_p]`, until the node's root is the node itself.
3. when `union(p,q)` is called attempting to connect `p` and `q`, we find the root of `p` by `find(p)`, and the root of `q` by `find(q)`, since the root node is always pointing to itself (e.g., `root[a] = a`), we simply connects the sub-tree rooted at `find(p)` to the root of `q`, (e.g., `root[p] = find(q)`).

```
public class UnionQuickUnion implements UnionFind {

    private int[] root;
    private int count;

    public UnionQuickUnion(final int numOfElements) {
        this.root = new int[numOfElements];
        for (int i = 0; i < numOfElements; i++) {
            root[i] = i; // points to itself
        }
        this.count = numOfElements;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int find(int p) {
        while (root[p] != p)
            p = root[p];
        return root[p];
    }

    @Override
    public void union(int p, int q) {
        if (p == q) {
            return;
        }
        int rootP = find(p);
        int rootQ = find(q);

        // connect root of p to the tree rooted at q's root
        if (rootP != rootQ) {
            root[rootP] = rootQ;
            count--;
        }
    }
}
```

#### 1.10.4 Weighted Quick Union

Weighted Quick-Union is just like Quick-Union algorithm, except that the root node's weight is recorded, and the sub-trees are connected to the root node that has less weight.

```
public class WeightedQuickUnion implements UnionFind {

    private int[] root;
    private int[] weight;
    private int count;

    public WeightedQuickUnion(final int numOfElements) {
        root = new int[numOfElements];
        weight = new int[numOfElements];
        for (int i = 0; i < numOfElements; i++) {
            root[i] = i;
            weight[i] = 1;
        }
        count = numOfElements;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int find(int p) {
        while (root[p] != p)
            p = root[p];
        return p;
    }

    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ)
            return;

        // small tree is connected to the larger tree
        if (weight[q] < weight[p]) {
            root[rootQ] = root[rootP];
            weight[rootP] += weight[rootQ];
        } else {
            root[rootP] = root[rootQ];
            weight[rootQ] += weight[rootP];
        }
        count--;
    }
}
```

### 1.11
