public class KdTree {

  private Node root;
  private int N;
  private String V = "verticalLine";
  private String H = "horizontalLine";

  private static class Node {
    // the point
    private Point2D p;
    // the axis-aligned rectangle corresponding to this node
    private RectHV rect;
    // the left/bottom subtree
    private Node lb;
    // the right/top subtree
    private Node rt;

    public Node(Point2D pt, RectHV rectangle) {
      this.p = pt;
      this.rect = rectangle;
    }
  }

  // construct an empty set of points
  public KdTree() {
  }

  // is the set empty?
  public boolean isEmpty() {
    return size() == 0;
  }

  // number of points in the set
  public int size() {
    return N;
  }

  // add the point p to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null || contains(p)) return;
    root = put(root, p, V, null);
    N += 1;
  }

  private Node put(Node x, Point2D p, String splitLine, Node parent) {

    RectHV currentRect = makeCurrentRect(parent, p, splitLine);

    if (x == null) {
      Node tmpNode = new Node(p, currentRect);
      return tmpNode;
    }

    int cmp;
    String nextsplitLine;
    if (splitLine.equals(V)) {
      cmp = Point2D.X_ORDER.compare(p, x.p);
      nextsplitLine = H;
    } else {
      cmp = Point2D.Y_ORDER.compare(p, x.p);
      nextsplitLine = V;
    }

    if (cmp < 0) x.lb = put(x.lb, p, nextsplitLine, x);
    else x.rt = put(x.rt, p, nextsplitLine, x);

    return x;
  }

  private RectHV makeCurrentRect(Node x, Point2D p, String splitLine) {
    if (x == null) return new RectHV(0.0, 0.0, 1.0, 1.0);

    int cmp;
    double xmin = x.rect.xmin();
    double ymin = x.rect.ymin();
    double xmax = x.rect.xmax();
    double ymax = x.rect.ymax();

    if (splitLine.equals(V)) {
      cmp = Point2D.Y_ORDER.compare(p, x.p);
      if (cmp < 0) {
        ymax = x.p.y();
      } else {
        ymin = x.p.y();
      }
    } else {
      cmp = Point2D.X_ORDER.compare(p, x.p);
      if (cmp < 0) {
        xmax = x.p.x();
      } else {
        xmin = x.p.x();
      }
    }

    return new RectHV(xmin, ymin, xmax, ymax);
  }

  // does the set contain the point p?
  public boolean contains(Point2D p) {
    return get(p) != null;
  }

  private Point2D get(Point2D p) {
    return get(root, p, V);
  }

  private Point2D get(Node x, Point2D p, String splitLine) {
    if (x == null) return null;
    if (p.equals(x.p)) return x.p;

    int cmp;
    String nextLine;
    if (splitLine.equals(V)) {
      cmp = Point2D.X_ORDER.compare(p, x.p);
      nextLine = H;
    } else {
      cmp = Point2D.Y_ORDER.compare(p, x.p);
      nextLine = V;
    }

    if (cmp < 0) {
      return get(x.lb, p, nextLine);
    } else {
      return get(x.rt, p, nextLine);
    }
  }

  // draw all of the points to standard draw
  public void draw() {
    root.rect.draw();
    draw(root, V);
  }

  private void draw(Node x, String splitLine) {
    if (x == null) return;

    // draw point
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius(.01);
    x.p.draw();

    // draw split line
    StdDraw.setPenRadius();
    if (splitLine.equals(V)) {
      StdDraw.setPenColor(StdDraw.RED);
      Point2D p1 = new Point2D(x.p.x(), x.rect.ymin());
      Point2D p2 = new Point2D(x.p.x(), x.rect.ymax());
      p1.drawTo(p2);
    } else {
      StdDraw.setPenColor(StdDraw.BLUE);
      Point2D p1 = new Point2D(x.rect.xmin(), x.p.y());
      Point2D p2 = new Point2D(x.rect.xmax(), x.p.y());
      p1.drawTo(p2);
    }

    // set splitLine for next level
    String nextsplitLine;
    if (splitLine.equals(V)) nextsplitLine = H;
    else nextsplitLine = V;

    draw(x.lb, nextsplitLine);
    draw(x.rt, nextsplitLine);
  }

  // all points in the set that are inside the rectangle
  public Iterable<Point2D> range(RectHV rect) {
    Queue<Point2D> q = new Queue<Point2D>();
    range(rect, root, q);
    return q;
  }

  private void range(RectHV rect, Node x, Queue<Point2D> q) {
    if (x == null) return;
    if (!x.rect.intersects(rect)) return;
    if (rect.contains(x.p)) q.enqueue(x.p);
    range(rect, x.lb, q);
    range(rect, x.rt, q);
  }

  // a nearest neighbor in the set to p; null if set is empty
  public Point2D nearest(Point2D p) {
    if (size() == 0) return null;
    return nearest(p, root);
    // return nearest2(p, root);
  }

  private Point2D nearest(Point2D p, Node x) {
    Point2D result = x.p;

    if ((x.lb != null && x.rt == null)
      && (x.lb.rect.distanceSquaredTo(p) <= p.distanceSquaredTo(result))) {
      // only need go lb
      Point2D left = nearest(p, x.lb);
      if (p.distanceSquaredTo(left) < p.distanceSquaredTo(result)) result = left;
    }

    if ((x.lb == null && x.rt != null)
      && (x.rt.rect.distanceSquaredTo(p) <= p.distanceSquaredTo(result))) {
      // only need go rt
      Point2D right = nearest(p, x.rt);
      if (p.distanceSquaredTo(right) < p.distanceSquaredTo(result)) result = right;
    }

    if (x.lb != null && x.rt != null) {
      // first go near, next go far
      if ((x.lb.rect.distanceSquaredTo(p) <= p.distanceSquaredTo(result))
          && (x.rt.rect.distanceSquaredTo(p) <= p.distanceSquaredTo(result))) {

        if (x.lb.rect.distanceSquaredTo(p) <= x.rt.rect.distanceSquaredTo(p)) {
          Point2D left = nearest(p, x.lb);
          if (p.distanceSquaredTo(left) < p.distanceSquaredTo(result)) result = left;

          if (x.rt.rect.distanceSquaredTo(p) <= p.distanceSquaredTo(result)) {
            Point2D right = nearest(p, x.rt);
            if (p.distanceSquaredTo(right) < p.distanceSquaredTo(result)) result = right;
          }
        } else {
          Point2D right = nearest(p, x.rt);
          if (p.distanceSquaredTo(right) <= p.distanceSquaredTo(result)) result = right;

          if (x.lb.rect.distanceSquaredTo(p) <= p.distanceSquaredTo(result)) {
            Point2D left = nearest(p, x.lb);
            if (p.distanceSquaredTo(left) < p.distanceSquaredTo(result)) result = left;
          }
        }
      }
    }

    return result;
  }

  // private Point2D nearest2(Point2D p, Node x) {
  //   Point2D result = x.p;
  //   // StdOut.println(result);

  //   if (x.lb != null) {
  //     if (x.lb.rect.distanceSquaredTo(p) < p.distanceSquaredTo(result)) {
  //       // StdOut.println("recursion begin");
  //       Point2D left = nearest2(p, x.lb);
  //       if (p.distanceSquaredTo(left) < p.distanceSquaredTo(result)) result = left;
  //     }
  //   }

  //   if (x.rt != null) {
  //     if (x.rt.rect.distanceSquaredTo(p) < p.distanceSquaredTo(result)) {
  //       // StdOut.println("recursion begin");
  //       Point2D right = nearest2(p, x.rt);
  //       if (p.distanceSquaredTo(right) < p.distanceSquaredTo(result)) result = right;
  //     }
  //   }

  //   return result;
  // }

  public static void main(String[] args) {
    // KdTree tree = new KdTree();
    // StdOut.println(tree.isEmpty());
    // tree.insert(new Point2D(0.2, 0.3));
    // StdOut.println(tree.isEmpty());
    // StdOut.println(tree.size());

    String filename = args[0];
    In in = new In(filename);

    // initialize the data structures with N points from standard input
    KdTree kdtree = new KdTree();
    StdOut.println("before:" + kdtree.size());
    while (!in.isEmpty()) {
      double x = in.readDouble();
      double y = in.readDouble();
      Point2D p = new Point2D(x, y);
      kdtree.insert(p);
      // if (!kdtree.contains(p)) StdOut.println("wroing at point:" + p);
      // StdOut.println(kdtree.size() + "point: " + p);
    }

    StdOut.println("after:" + kdtree.size());
    kdtree.draw();

    Point2D query = new Point2D(0.81, 0.30);
    query.draw();
    kdtree.nearest(query).drawTo(query);
  }
}
