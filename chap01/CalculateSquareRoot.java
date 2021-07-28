/**
 * @author yongjie.zhuang
 */
public class CalculateSquareRoot {

    public static void main(String[] args) {
        double c = 24;
        System.out.printf("Sqaure root of %f = %f\n", c, sqrt(c));
        System.out.printf("Sqaure root of %f = %f\n", c, vsqrt(c));
    }

    static double sqrt(double c) {
        if (c < 0)
            throw new IllegalArgumentException("Should be greater than zero");

        double err = 1e-15; // this is for precision
        double root = c;

        // we are estimating until we reach a specific precision level
        while (Math.abs(root - c / root) > err * root) {
            // moving closer towards the root value
            root = (c / root + root) / 2.0;
        }
        return root;
    }

    static double vsqrt(double c) {
        if (c < 0)
            throw new IllegalArgumentException("Should be greater than zero");

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
}