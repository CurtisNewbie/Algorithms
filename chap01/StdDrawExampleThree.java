import java.util.*;
import edu.princeton.cs.algs4.*;

public class StdDrawExampleThree{
	public static void main(String[] args){
		final int N = 50;
		double[] a = new double[N];
		
		// generate random values
		for(int i = 0; i < N; i++){
			a[i] = StdRandom.random();
		}
		
		// sort them
		Arrays.sort(a);

		// visualise
		for(int i = 0; i < N; i++){
			double x = 1.0 * i /N;
			double y = a[i]/2.0;
			double rw = 0.5/N;
			double rh = a[i]/2.0;
			StdDraw.filledRectangle(x, y, rw, rh);
		}
	}
}
