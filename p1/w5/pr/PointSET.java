public class PointSET {

  private SET<Point2D> set;

  // construct an empty set of points
  public PointSET() {
    set = new SET<Point2D>();
  }

  // is the set empty?
  public boolean isEmpty() {
    return set.isEmpty();
  }

  // number of points in the set
  public int size() {
    return set.size();
  }

  // add the point p to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (!set.contains(p)) {
      set.add(p);
    }
  }

  // does the set contain the point p?
  public boolean contains(Point2D p) {
    return set.contains(p);
  }

  // draw all of the points to standard draw
  public void draw() {
    for (Point2D pt : set) {
      pt.draw();
    }
  }

  // all points in the set that are inside the rectangle
  public Iterable<Point2D> range(RectHV rect) {
    Queue<Point2D> q = new Queue<Point2D>();

    for (Point2D pt : set) {
      if (rect.contains(pt)) {
        q.enqueue(pt);
      }
    }

    return q;
  }

  // a nearest neighbor in the set to p; null if set is empty
  public Point2D nearest(Point2D p) {
    if (isEmpty()) return null;

    Point2D resultPoint = set.max();

    for (Point2D pt : set) {
      if (p.distanceTo(pt) < p.distanceTo(resultPoint)) resultPoint = pt;
    }

    return resultPoint;
  }

}
