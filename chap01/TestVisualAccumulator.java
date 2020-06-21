import edu.princeton.cs.algs4.*;

public class TestVisualAccumulator{

	public static void main(String[] args){
		final int N = 2000;
		System.out.printf("Drawing %s random data points.\n", N);
		VisualAccumulator va = new VisualAccumulator(N, 1.0);
		for(int i = 0 ; i < N ; i++){
			va.addDataPoint(StdRandom.random());
		}
		System.out.println("Mean: " + va.mean());
	}	
}

