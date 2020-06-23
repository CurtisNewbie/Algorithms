
import edu.princeton.cs.algs4.*;

public class Plot {
    public static void plots(final Integer[] arr, boolean sorted) {
        if (sorted)
            StdDraw.setPenColor(StdDraw.RED);
        int len = arr.length;
        StdDraw.setXscale(0, len);
        StdDraw.setYscale(0, len);
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < len; i++) {
            StdDraw.point(i, arr[i]);
        }
    }
}