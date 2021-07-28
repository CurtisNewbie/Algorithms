
public class GCD {

    public static void main(String[] args) {
        int a = 10;
        int b = 23;
        System.out.printf("recursive gcd: %d\n", recursiveGCD(a, b));
        System.out.printf("iterative gcd: %d\n", iterativeGCD(a, b));
    }

    public static int recursiveGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        int r = a % b;
        return recursiveGCD(b, r);
    }

    public static int iterativeGCD(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}

