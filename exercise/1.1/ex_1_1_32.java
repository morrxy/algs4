/* 
 * Histogram. Suppose that the standard input stream is a sequence 
 * of doublevalues. Write a program that takes an integer N and two 
 * double values l and r from the command line and uses StdDraw to 
 * plot a histogram of the count of the numbers in the standard input 
 * stream that fall in each of the N intervals defined by dividing (l,r) 
 * into N equal-sized intervals.
 *
 * 
 */

public class ex_1_1_32 {

	public static void main(String[] args) {

		int N = Integer.parseInt(args[0]);
		double left = Double.parseDouble(args[1]);
		double righg = Double.parseDouble(args[2]);

		// make a array of double throuth standard input
		// and promise every item in array betwtten left and right
		double[] samples = make_samples();

		// make a arrary intervals[] inlucde N entry
		// intervals[i] is the ith interval between (l, r)
		// every itme value indacate number of intervals
		// that fall in this interval
		int[] intervals = make_intervals;

		// draw histogram
		histogram(samples, intervals);
	}



}