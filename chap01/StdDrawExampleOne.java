import edu.princeton.cs.algs4.*;

public class StdDrawExampleOne{
	public static void main(String[] args){
		final int N = 100;
		StdDraw.setXscale(0, N);
		StdDraw.setYscale(0, N*N);
		StdDraw.setPenRadius(0.01);
		for(int i = 1; i <= N; i++){
			StdDraw.point(i, i); // O(1)
			StdDraw.point(i, i*i); // O(2^N)
			StdDraw.point(i, i*Math.log(i)); // O(logN)
		}
	}
}
