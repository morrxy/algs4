/* 
 * Histogram. Suppose that the standard input stream is a sequence 
 * of doublevalues. Write a program that takes an integer N and two 
 * double values l and r from the command line and uses StdDraw to 
 * plot a histogram of the count of the numbers in the standard input 
 * stream that fall in each of the N intervals defined by dividing (l,r) 
 * into N equal-sized intervals.
 *
 * java ex_1_1_32 10 1 100 < tinyT.txt
 */

import java.util.Arrays;

public class ex_1_1_32 {

	public static void main(String[] args) {

		int N = Integer.parseInt(args[0]);
		double left = Double.parseDouble(args[1]);
		double right = Double.parseDouble(args[2]);

		// make a array of double throuth standard input
		// and promise every item in array betwtten left and right
		double[] samples = make_samples(left, right);

		// test samples
		// StdOut.println(samples.length);
		// Arrays.sort(samples);
		// for (double x : samples) {
		// 	StdOut.print(x + " ");
		// }


		// make a arrary intervals[] inlucde N entry
		// intervals[i] is the ith interval between (l, r)
		// every itme value indacate number of intervals
		// that fall in this interval
		int[] intervals = make_intervals(samples, N, left, right);

		// test intervals
		// StdOut.println(intervals.length);
		// for (int x : intervals) {
		// 	StdOut.print(x + " ");
		// }

		// draw histogram
		draw_histogram(intervals, samples);
	}

	public static double[] make_samples(double left, double right) {
		String allstr = "";
		while(!StdIn.isEmpty()) {
			String str = StdIn.readString();
			double n = Double.parseDouble(str);
			if (n >= left && n <= right) {
				allstr = allstr + str + " ";
			}
		}

		String[] str_array = allstr.split(" ");
		int len = str_array.length;

		double[] double_array = new double[len];

		for (int i = 0; i < len; i++) {
			double_array[i] = Double.parseDouble(str_array[i]);
		}

		return double_array;
	}

	public static int[] make_intervals(double[] samples, int N, double left, double right) {
		int[] result = new int[N];
		int p;

		for (int i = 0; i < samples.length; i++) {
			p = which_interval(samples[i], N, left, right);
			result[p] += 1;
		}

		return result;
	}

	public static int which_interval(double sample, int N, double left, double right) {
		
		int k = 0;
		double avg = (right - left) / N;

		for (int i = 0; i < N; i++) {
			if (sample >= avg * i && sample < avg * (i + 1)) {
				k = i;
				break;
			}
		}

		if (sample == right) {
			k = N - 1;
		}

		return k;
	}

	public static void draw_histogram(int[] intervals, double[] samples) {

		StdDraw.setPenRadius(.006);
		StdDraw.line(0, 0, 1, 0);
		StdDraw.line(0, 0, 0, 1);

		int n = intervals.length;
		int total = samples.length;
		double interval_width = 1.0 / n;
		double x, y;
		double halfWidth = interval_width / 2;

		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.setPenRadius();
		for (int i = 0; i < n; i++) {
			x = i * interval_width + halfWidth;
			y = (double)intervals[i] / total / 2;
			StdDraw.rectangle(x, y, halfWidth, y);
		}

	}

}