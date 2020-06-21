import edu.princeton.cs.algs4.*;

public class RandomSeq{
	public static void main(String[] args){
		int n = 5;
		double lo = 100.0;
		double hi = 200.0;
		for(int i = 0; i < n; i++){
			StdOut.printf("%.2f\n", StdRandom.uniform(lo, hi));
		}
	}
}
