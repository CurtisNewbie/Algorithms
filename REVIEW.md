# Book Review

## Chapter 1

### 1.1 GCD (Greatest Common Divisor) Algorithm

(p1)

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

(p11)

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

(p13)

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

(p13)

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

For the precision part, we can also change it a bit as follows for better understanding. 1e-15 is much preciser than 1e-5, and will yield differet result. For example, for `varient_sqrt(24)`: when `err = 1`, it yields a result of 4.911996, but when `err = 1e-5`, it yields a result of 4.898979.

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
