/**
 * an abstract data type for accumulating data values
 */
public class Accumulator {
	private double total;
	private int N;

	public void addDataValue(double val) {
		N += 1;
		total += val;
	}

	public double mean() {
		return total / N;
	}

	public String toString() {
		return "Mean (" + N + " values): " + String.format("%7.5f", mean());
	}
}