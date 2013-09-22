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
      double a = 1.0;
      return (a - a) / a;
    }

    // treat the slope of a vertical line segment as positive infinity;
    if (this.x == that.x && this.y != that.y) {
      return Double.POSITIVE_INFINITY;
    }

    // treat the slope of a degenerate line segment (between a point
    // and itself) as negative infinity
    if (this.x == that.x && this.y == that.y) {
      return Double.NEGATIVE_INFINITY;
    }

    return (that.y - this.y) / (that.x - this.x);
  }

  private class BySlope implements Comparator<Point> {
    public int compare(Point v, Point w) {
      if ((Point.this.x != v.x && Point.this.y == v.y) && (Point.this.x != w.x && Point.this.y == w.y)) {
        return 0;
      }

      if ((Point.this.slopeTo(v) == Double.POSITIVE_INFINITY) && (Point.this.slopeTo(w) == Double.POSITIVE_INFINITY)) {
        return 0;
      }

      if ((Point.this.slopeTo(v) == Double.NEGATIVE_INFINITY) && (Point.this.slopeTo(w) == Double.NEGATIVE_INFINITY)) {
        return 0;
      }

      double result = Point.this.slopeTo(v) - Point.this.slopeTo(w);
      if (result > 0) return 1;
      else if (result < 0) return -1;
      else return 0;
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
  }

}
