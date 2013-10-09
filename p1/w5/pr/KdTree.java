public class KdTree {

  private Node root;
  private int N;
  private String V = "vertical";
  private String H = "horizontal";

  private static class Node {
    // the point
    private Point2D p;
    // the axis-aligned rectangle corresponding to this node
    private RectHV rect;
    // the left/bottom subtree
    private Node lb;
    // the right/top subtree
    private Node rt;

    public Node(Point2D pt) {
      this.p = pt;
    }
    public Node(Point2D pt, RectHV rectangle) {
      this.p = pt;
      this.rect = rectangle;
    }
    // public Node(RectHV rec) {
    //   this.rect = rec;
    // }
  }

  // construct an empty set of points
  public KdTree() {
    // root = new Node(new RectHV(0.0, 0.0, 1.0, 1.0));
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
    // root = put(root, p, V);
    root = put2(root, p, V);
    N += 1;
  }

  // public void insert2(Point2D p) {
  //   if (p == null || contains(p)) return;
  //   root = put2(root, p, V);
  //   N += 1;
  // }

  // private Node put(Node x, Point2D p, String orientation) {
  //   if (x == null) return new Node(p);

  //   int cmp;
  //   String nextOrientation;
  //   if (orientation.equals(V)) {
  //     cmp = Point2D.X_ORDER.compare(p, x.p);
  //     nextOrientation = H;
  //   } else {
  //     cmp = Point2D.Y_ORDER.compare(p, x.p);
  //     nextOrientation = V;
  //   }

  //   if (cmp < 0) x.lb = put(x.lb, p, nextOrientation);
  //   else x.rt = put(x.rt, p, nextOrientation);

  //   return x;
  // }

  private Node put2(Node x, Point2D p, String orientation) {

    RectHV currentRect;
    if (size() == 0) currentRect = new RectHV(0.0, 0.0, 1.0, 1.0);
    else currentRect = makeCurrentRect(x, p, orientation);

    if (x == null) return new Node(p, currentRect);

    int cmp;
    String nextOrientation;
    if (orientation.equals(V)) {
      cmp = Point2D.X_ORDER.compare(p, x.p);
      nextOrientation = H;
    } else {
      cmp = Point2D.Y_ORDER.compare(p, x.p);
      nextOrientation = V;
    }

    if (cmp < 0) x.lb = put2(x.lb, p, nextOrientation);
    else x.rt = put2(x.rt, p, nextOrientation);

    return x;
  }

  private RectHV makeCurrentRect(Node x, Point2D p, String orientation) {
    int cmp;
    double xmin = x.rect.xmin();
    double ymin = x.rect.ymin();
    double xmax = x.rect.xmax();
    double ymax = x.rect.ymax();

    if (orientation.equals(V)) {
      cmp = Point2D.X_ORDER.compare(p, x.p);
      if (cmp < 0) {
        xmax = p.x();
      } else {
        xmin = p.x();
      }
    } else {
      cmp = Point2D.Y_ORDER.compare(p, x.p);
      if (cmp < 0) {
        ymax = p.y();
      } else {
        ymin = p.y();
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

  private Point2D get(Node x, Point2D p, String orientation) {
    if (x == null) return null;
    if (p.equals(x.p)) return x.p;

    int cmp;
    if (orientation.equals(V)) {
      cmp = Point2D.X_ORDER.compare(p, x.p);
      orientation = H;
    } else {
      cmp = Point2D.Y_ORDER.compare(p, x.p);
      orientation = V;
    }

    if (cmp < 0) {
      return get(x.lb, p, orientation);
    } else {
      return get(x.rt, p, orientation);
    }
  }

  // draw all of the points to standard draw
  public void draw() {
    draw(root);
  }

  private void draw(Node x) {
    if (x == null) return;
    x.rect.draw();
    draw(x.lb);
    draw(x.rt);
  }

  // all points in the set that are inside the rectangle
  public Iterable<Point2D> range(RectHV rect) {
    Queue<Point2D> q = new Queue<Point2D>();
    return q;
  }

  // a nearest neighbor in the set to p; null if set is empty
  public Point2D nearest(Point2D p) {
    return new Point2D(0, 0);
  }

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
  }
}
