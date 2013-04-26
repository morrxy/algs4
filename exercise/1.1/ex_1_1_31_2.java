/*
 * Random connections.  Write a program that takes as command-line arguments 
 * an integer N and a double value p (between 0 and 1), plots N equally spaced dots of size 
 * .05 on the circumference of a circle, and then, with probability p for each pair of points, 
 * draws a gray line connecting them.
 *
 * java ex_1_1_31_2.java 10 .3
 */

public class ex_1_1_31_2 {

	public static final double CENTER_X = .5;
	public static final double CENTER_Y = .5;
	public static final double RADIUS = .5;


	public static void main(String[] args) {
		// StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		// StdDraw.circle(CENTER_X, CENTER_Y, RADIUS);

		int N = Integer.parseInt(args[0]);
		if (N <= 0) return;

		double probability = Double.parseDouble(args[1]);
		Point2D[] points = new Point2D[N];

		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius(.05);

		Point2D center = new Point2D(CENTER_X, CENTER_Y);
		Point2D pt1;
		double average_angel = 360 / N;

		for (int i = 0; i < N; i++) {
			pt1 = get_point(center, RADIUS, average_angel * i);
			points[i] = new Point2D(pt1.x(), pt1.y());
			points[i].draw();
		}

		StdDraw.setPenColor(StdDraw.ORANGE);
		StdDraw.setPenRadius();

		for (int j = 0; j < points.length; j++) {
			int k = j + 1;
			if (k == points.length) {
				k = 0;
			}
			if (StdRandom.bernoulli(probability)) {
				StdDraw.line(points[j].x(), points[j].y(), points[k].x(), points[k].y());
			}
		}

	}

	public static Point2D get_point(Point2D center, double radius, double angel) {

		double arc = (angel * Math.PI) / 180;

		double dx = RADIUS * Math.cos(arc);
		double dy = RADIUS * Math.sin(arc);

		Point2D p2d = new Point2D(center.x() + dx, center.y() + dy);

		return p2d;

	}

}