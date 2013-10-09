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

    // private int count;

    // public Node(Point2D pt, int c) {
    //   this.p = pt;
    //   this.count = c;
    // }

    public Node(Point2D pt) {
      this.p = pt;
    }

    // public Node() {}
  }

  // construct an empty set of points
  public KdTree() {
    // root = null;
  }

  // is the set empty?
  public boolean isEmpty() {
    return size() == 0;
  }

  // number of points in the set
  public int size() {
    // return size(root);
    return N;
  }

  // private int size(Node x) {
  //   if (x == null) return 0;
  //   else return x.count;
  // }

  // add the point p to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null || contains(p)) {
      StdOut.println("not insert: " + p);
      return;
    }
    N += 1;
    root = put(root, p, V);
  }

  private Node put(Node x, Point2D p, String orientation) {
    if (x == null) return new Node(p);

    int cmp;
    if (orientation.equals(V)) {
      cmp = Point2D.X_ORDER.compare(p, x.p);
      orientation = H;
    } else {
      cmp = Point2D.Y_ORDER.compare(p, x.p);
      orientation = V;
    }

    if (cmp < 0) x.lb = put(x.lb, p, orientation);
    else x.rt = put(x.rt, p, orientation);

    // x.count = 1 + size(x.lb) + size(x.rt);
    return x;
  }

  // private Node put1(Node x, Point2D p) {
  //   if (x == null) return new Node(p, 1);
  //   int cmp = p.compareTo(x.p);
  //   if      (cmp < 0) x.lb = put(x.lb, p);
  //   else if (cmp > 0) x.rt = put(x.rt, p);
  //   else              x.p  = p;
  //   x.count = 1 + size(x.lb) + size(x.rt);
  //   return x;
  // }

  // does the set contain the point p?
  public boolean contains(Point2D p) {
    return get(p) != null;
  }

  private Point2D get(Point2D p) {
    return get(root, p, V);
  }

  private Point2D get(Node x, Point2D p, String orientation) {
    if (x == null) return null;

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
    } else if (cmp > 0) {
      return get(x.rt, p, orientation);
    } else {
      return x.p;
    }

  }

  // private Point2D get1(Node x, Point2D p) {
  //   if (x == null) return null;
  //   int cmp = p.compareTo(x.p);
  //   if (cmp < 0) return get(x.lb, p);
  //   else if (cmp > 0) return get(x.rt, p);
  //   else return x.p;
  // }

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
      StdOut.println(kdtree.size() + "point: " + p);
    }

    StdOut.println("after:" + kdtree.size());
  }
}
