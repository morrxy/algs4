/**
 * exercise 1.2.3
 * Write an Interval2D client that takes command-line arguments N, min, and max
 * and generates N random 2D intervals whose width and height are uniformly distributed
 * between min and max in the unit square. Draw them on StdDraw and print the number
 * of pairs of intervals that intersect and the number of intervals that are contained
 * in one another.
 *
 * java Interval2D_client 30 .1 .9
 * 
 */

import java.util.Arrays;

public class Interval2D_client {
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		double min = Double.parseDouble(args[1]);
		double max = Double.parseDouble(args[2]);

		Interval2D[] inters = new Interval2D[N];
		Interval1D[][] inters_1d = new Interval1D[N][2];

		for (int i = 0; i < N; i++) {
			double[] d1 = make_doubles(min, max);
			Interval1D x = new Interval1D(d1[0], d1[1]);
			d1 = make_doubles(min, max);
			Interval1D y = new Interval1D(d1[0], d1[1]);

			inters_1d[i][0] = x;
			inters_1d[i][1] = y;

			inters[i] = new Interval2D(x, y);
			inters[i].draw();
		}

		int n = calculate_intersect_pairs(inters);
		StdOut.println("the number of pairs of intervals that intersects: " + n);

		int n1 = contained_each_other(inters_1d);
		StdOut.println("the number of intervals that are contained in one another: " + n1);
	}

	public static double[] make_doubles(double min, double max) {
		double[] a = new double[2];
		a[0] = StdRandom.uniform(min, max);
		a[1] = StdRandom.uniform(min, max);
		Arrays.sort(a);
		return a;
	}

	public static int calculate_intersect_pairs(Interval2D[] inters) {
		int count = 0;
		int len = inters.length;

		for (int i = 0; i <= len - 2; i++) {
			for (int j = i + 1; j <= len -1; j++) {
				if (inters[i].intersects(inters[j])) {
					count += 1;
				}
			}
		}

		return count;
	}

	public static int contained_each_other(Interval1D[][] inters) {
		int count = 0;
		int len = inters.length;

		for (int i = 0; i <= len - 2; i++) {
			for (int j = i + 1; j <= len - 1; j++) {
				if (all_overlap(inters[i], inters[j])) {
					count += 1;
				}
			}
		}

		return count;
	}

	public static boolean all_overlap(Interval1D[] int1, Interval1D[] int2) {
		// boolean result = false;
		
		Interval1D x1 = int1[0];
		Interval1D y1 = int1[1];
		Interval1D x2 = int2[0];
		Interval1D y2 = int2[1];

		if (x1.left() != x2.left()) return false;
		if (x1.right() != x2.right()) return false;
		if (y1.left() != y2.left()) return false;
		if (y1.right() != y2.right()) return false;

		return true;
	}

}