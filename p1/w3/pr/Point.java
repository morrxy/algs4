/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;
import java.util.Arrays;

public class Point implements Comparable<Point> {

  // compare points by slope
  public final Comparator<Point> SLOPE_ORDER = new BySlope(); // YOUR DEFINITION HERE

  private final int x;                              // x coordinate
  private final int y;                              // y coordinate

  // create the point (x, y)
  public Point(int x, int y) {
    /* DO NOT MODIFY */
    this.x = x;
    this.y = y;
  }

  // plot this point to standard drawing
  public void draw() {
    /* DO NOT MODIFY */
    StdDraw.point(x, y);
  }

  // draw line between this point and that point to standard drawing
  public void drawTo(Point that) {
    /* DO NOT MODIFY */
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  // slope between this point and that point
  public double slopeTo(Point that) {
    /* YOUR CODE HERE */

    // Treat the slope of a horizontal line segment as positive zero
    if (this.x != that.x && this.y == that.y) {
      // StdOut.println("horizontal");
      return 0.0;
    }

    // treat the slope of a vertical line segment as positive infinity;
    if (this.x == that.x && this.y != that.y) {
      // StdOut.println("vertical");
      return Double.POSITIVE_INFINITY;
    }

    // treat the slope of a degenerate line segment (between a point
    // and itself) as negative infinity
    if (this.x == that.x && this.y == that.y) {
      // StdOut.println("degenerate");
      return Double.NEGATIVE_INFINITY;
    }

    // StdOut.println("normal");
    return (double)(that.y - this.y) / (that.x - this.x);
  }

  private class BySlope implements Comparator<Point> {
    public int compare(Point v, Point w) {

      double slp1 = Point.this.slopeTo(v);
      double slp2 = Point.this.slopeTo(w);

      // StdOut.println("slp1:" + slp1);
      // StdOut.println("slp2:" + slp2);

      if (slp1 == slp2) return 0;

      double result = slp1 - slp2;

      if (result > 0) return 1;
      else return -1;
    }
  }

  // is this point lexicographically smaller than that one?
  // comparing y-coordinates and breaking ties by x-coordinates
  public int compareTo(Point that) {
    /* YOUR CODE HERE */
    if (this.y > that.y) return 1;
    if (this.y < that.y) return -1;
    if (this.x > that.x) return 1;
    if (this.x < that.x) return -1;
    return 0;
  }

  // return string representation of this point
  public String toString() {
    /* DO NOT MODIFY */
    return "(" + x + ", " + y + ")";
  }

  // unit test
  public static void main(String[] args) {
    /* YOUR CODE HERE */
    Point p0 = new Point(1, 1);
    Point p1 = new Point(1, 1);
    Point p2 = new Point(1, 3);
    int r = p0.SLOPE_ORDER.compare(p1, p2);
    StdOut.println(r);

    // StdOut.println(Double.POSITIVE_INFINITY > 0.0);
    // StdOut.println(0.0 > Double.NEGATIVE_INFINITY);
    // StdOut.println(Double.POSITIVE_INFINITY > Double.NEGATIVE_INFINITY);

    // StdOut.println(Double.POSITIVE_INFINITY - Double.POSITIVE_INFINITY);
    // StdOut.println(Double.POSITIVE_INFINITY - Double.NEGATIVE_INFINITY);
    // StdOut.println(Double.POSITIVE_INFINITY - 0.0);

    // StdOut.println(Double.NEGATIVE_INFINITY - Double.NEGATIVE_INFINITY);
    // StdOut.println(Double.NEGATIVE_INFINITY - Double.POSITIVE_INFINITY);
    // StdOut.println(Double.NEGATIVE_INFINITY - 0.0);

    // StdOut.println(0.0 - Double.POSITIVE_INFINITY);
    // StdOut.println(0.0 - Double.NEGATIVE_INFINITY);

    // StdOut.println(Double.POSITIVE_INFINITY == Double.POSITIVE_INFINITY);
    // StdOut.println(Double.NEGATIVE_INFINITY == Double.NEGATIVE_INFINITY);

    // String filename = args[0];
    // In in = new In(filename);
    // int N = in.readInt();

    // int x = in.readInt();
    // int y = in.readInt();
    // Point po = new Point(x, y);

    // Point[] points = new Point[N - 1];
    // for (int i = 0; i < N-1; i++) {
    //   x = in.readInt();
    //   y = in.readInt();
    //   Point p = new Point(x, y);
    //   points[i] = p;
    // }

    // StdOut.println("po: " + po.toString());
    // for (Point p : points) {
    //   StdOut.println(p.toString());
    // }

    // Arrays.sort(points);
    // Arrays.sort(points, Point.SLOPE_ORDER);

    // StdOut.println("after:");
    // for (Point p : points) {
    //   StdOut.println(p.toString());
    // }

    // int x1 = po.SLOPE_ORDER.compare(points[0], points[1]);
    // StdOut.println(x1);
  }

}
