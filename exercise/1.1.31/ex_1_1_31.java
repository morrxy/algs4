/*
 * Random connections.  Write a program that takes as command-line arguments 
 * an integer N and a double value p (between 0 and 1), plots N equally spaced dots of size 
 * .05 on the circumference of a circle, and then, with probability p for each pair of points, 
 * draws a gray line connecting them.
 *
 * java ex_1_1_31 10 .5
 */

public class ex_1_1_31 {

	// circle's radius and center
	public static final double RADIUS = .5;
	public static final Point2D CENTER = new Point2D(.5, .5);

	public static void main(String[] args) {

		// set optionally pen color
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);

		// draw optionally a reference circle
		StdDraw.circle(CENTER.x(), CENTER.y(), RADIUS);

		int N = Integer.parseInt(args[0]);
		if (N <= 0) return;

		// make points according N, CENTER, RADIUS
		Point2D[] points = make_points(N, CENTER, RADIUS);

		// draw points and line according probability
		double probability = Double.parseDouble(args[1]);
		random_connect(points, probability);

	}

	public static Point2D[] make_points(int n, Point2D center, Double radius) {

		Point2D[] points = new Point2D[n];
		Point2D pt1;
		double average_angel = 360 / n;
		
		for (int i = 0; i < n; i++) {
			pt1 = calculate_point(center, radius, average_angel * i);
			points[i] = new Point2D(pt1.x(), pt1.y());
		}

		return points;

	}

	public static Point2D calculate_point(Point2D center, double radius, double angel) {

		double arc = (angel * Math.PI) / 180;

		double dx = RADIUS * Math.cos(arc);
		double dy = RADIUS * Math.sin(arc);

		Point2D p2d = new Point2D(center.x() + dx, center.y() + dy);

		return p2d;

	}

	public static void random_connect(Point2D[] points, double probability) {

		for (int j = 0; j < points.length; j++) {
			// draw every point
			StdDraw.setPenRadius(.05); // set pen to draw point
			points[j].draw();

			// random connect each points of pair with probability
			StdDraw.setPenRadius(); // set pen to draw line
			int k = j + 1; // k is next point in array to draw a line with j
			while(true) {
				if (k > points.length - 1) {
					break;
				}
				if (StdRandom.bernoulli(probability)) {
					StdDraw.line(points[j].x(), points[j].y(), points[k].x(), points[k].y());
				}
				k += 1;
			}
		}

	}

}