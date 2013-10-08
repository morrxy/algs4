public class KdTree {

  private static class Node {
    // the point
    private Point2D p;
    // the axis-aligned rectangle corresponding to this node
    private RectHV rect;
    // the left/bottom subtree
    private Node lb;
    // the right/top subtree
    private Node rt;
  }

  // construct an empty set of points
  public KdTree() {}

  // is the set empty?
  public boolean isEmpty() {
    return false;
  }

  // number of points in the set
  public int size() {
    return 0;
  }

  // add the point p to the set (if it is not already in the set)
  public void insert(Point2D p) {

  }

  // does the set contain the point p?
  public boolean contains(Point2D p) {
    return false;
  }

  // draw all of the points to standard draw
  public void draw() {}

  // all points in the set that are inside the rectangle
  public Iterable<Point2D> range(RectHV rect) {
    Queue<Point2D> q = new Queue<Point2D>();
    return q;
  }

  // a nearest neighbor in the set to p; null if set is empty
  public Point2D nearest(Point2D p) {
    return new Point2D(0, 0);
  }
}