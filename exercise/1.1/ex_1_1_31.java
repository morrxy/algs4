/*
 * Random connections.  Write a program that takes as command-line arguments 
 * an integer N and a double value p (between 0 and 1), plots N equally spaced dots of size 
 * .05 on the circumference of a circle, and then, with probability p for each pair of points, 
 * draws a gray line connecting them.
 *
 * java ex_1_1_31.java 10
 */

public class ex_1_1_31 {

	public static final double CENTER_X = .5;
	public static final double CENTER_Y = .5;
	public static final double RADIUS = .5;


	public static void main(String[] args) {
		StdDraw.circle(CENTER_X, CENTER_Y, RADIUS);

		int N = Integer.parseInt(args[0]);
		Point2D[] points = new Point2D[N];

		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius(.01);

		for (int i = 0; i < N; i++) {
			double x1 = Math.random();
			double y1 = Math.random();
			points[i] = new Point2D(x1, y1);
			points[i].draw();
		}

		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.setPenRadius();

		for (int j = 0; j < points.length; j++) {
			int k = j;
			while(true) {
				if (k == points.length - 1) {
					break;
				}
				k += 1;
				StdDraw.line(points[j].x(), points[j].y(), points[k].x(), points[k].y());
			}
		}

	}

}