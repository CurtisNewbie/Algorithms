import edu.princeton.cs.algs4.*;
		
public class StdDrawExampleTwo{
	public static void main(String[] args){
		final int N = 50;
		double[] a = new double[N];
		
		// init a[] with random double values
		for(int i =0; i< N; i++){
			a[i] = StdRandom.random();
		}

		// visualise random numbers
		for(int i =0; i< N; i++){
			double x = 1.0 * i /N;
			double y = a[i]/2.0;
			double rw = 0.5/N;
			double rh = a[i]/2.0;
			StdDraw.filledRectangle(x, y, rw, rh);
		}	
	}
}
