/**
 * @author yongjie.zhuang
 */
public class CheckPrimeNumber {

    public static void main(String[] args) {
        int n = 13;
        System.out.printf("Is %d a prime number? %b\n", n, isPrime(n));
        n = 255;
        System.out.printf("Is %d a prime number? %b\n", n, isPrime(n));
        n = 288;
        System.out.printf("Is %d a prime number? %b\n", n, isPrime(n));
    }

    static boolean isPrime(int N) {
        if (N < 2)
            return false;
        for (int i = 2; i * i <= N; i++) {
            if (N % i == 0)
                return false;
        }
        return true;
    }
}
