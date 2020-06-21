import edu.princeton.cs.algs4.*;

public class VisualAccumulator{
	private double total;
	private int count;

	public VisualAccumulator(int trials, double max){
		StdDraw.setXscale(0, trials);
		StdDraw.setYscale(0, max);
		StdDraw.setPenRadius(0.005);
	}		

	public void addDataPoint(double val){
		count++;
		total += val;
		// draw data point
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.point(count, val);

		// draw diviation
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(count, total/count);
	}

	public double mean (){
		return total / count;
	}
}
