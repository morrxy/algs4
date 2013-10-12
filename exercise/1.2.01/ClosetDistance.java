/** 
 * exercise 1.2.1
 * Write a Point2D client that takes an integer value N from the
 * command line, generates N random points in the unit square,
 * and computes the distance separating the closest pair of points.
 *
 * java ClosetDistance 200
 * Closet Distance: 0.003952966158919392
 * 
 */

public class ClosetDistance {
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		Point2D[] points = new Point2D[N];
		for (int i = 0; i < N; i++) {
			double x = StdRandom.random();
			double y = StdRandom.random();
			Point2D p = new Point2D(x, y);
			points[i] = p;
			p.draw();
		}

		double min_distance = points[0].distanceTo(points[1]);

		for (int i = 0; i <= N - 2; i++) {
			for (int j = i + 1; j <= N - 1; j++) {
				if (i == 0 && j == 1) {
					continue;
				} else {
					double d = points[i].distanceTo(points[j]);
					if (d < min_distance) {
						min_distance = d;
					}
				}
			}
		}

		StdOut.println("Closet Distance: " + min_distance);
	}
}