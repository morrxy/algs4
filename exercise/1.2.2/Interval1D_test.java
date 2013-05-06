/**
 * exercise 1.2.2
 * Write an Interval1D client that takes an int value N as command-line argument,
 * reads N intervals (each defined by a pair of double values) from standard input,
 * and prints all pairs that intersect.
 *
 * java Interval1D_test 3
 * input a double as left end point of a interval
 * .4
 * input a double as right end point of a interval
 * .9
 * input a double as left end point of a interval
 * .3
 * input a double as right end point of a interval
 * .34
 * input a double as left end point of a interval
 * .5
 * input a double as right end point of a interval
 * .8
 * [0.4, 0.9] intersects with [0.5, 0.8]
 * 
 */

public class Interval1D_test {
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		Interval1D[] intervals = new Interval1D[N];

		for (int i = 0; i < N; i++) {
			StdOut.println("input a double as left end point of a interval");
			double x = StdIn.readDouble();
			StdOut.println("input a double as right end point of a interval");
			double y = StdIn.readDouble();

			intervals[i] = new Interval1D(x, y);
		}

		for (int i = 0; i <= N - 2; i++) {
			for (int j = i + 1; j <= N - 1; j++) {
				Interval1D int1 = intervals[i];
				Interval1D int2 = intervals[j];
				if (int1.intersects(int2)) {
					StdOut.println(int1 + " intersects with " + int2);
				}
			}
		}
	}
}