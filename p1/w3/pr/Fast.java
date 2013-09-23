import java.util.Arrays;

public class Fast {
  public static void sortPoints(Point p, Point[] ps) {
    Arrays.sort(ps, p.SLOPE_ORDER);
  }

  public static void main(String[] args) {

    // initialize canvas
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.show(0);

    // read all points into an array Point[] points
    String filename = args[0];
    In in = new In(filename);
    int N = in.readInt();
    Point[] points = new Point[N];
    for (int i = 0; i < N; i++) {
      int x = in.readInt();
      int y = in.readInt();
      Point p = new Point(x, y);
      points[i] = p;
      p.draw();
    }

    // test begin
    Point p0 = points[0];
    Point[] ps = new Point[N-1];
    for (int i = 0; i < N-1; i++) {
      ps[i] = points[i+1];
    }

    // look points
    // StdOut.println("p0: " + p0.toString());
    // for (Point p : ps) {
    //   StdOut.println(p.toString());
    // }

    sortPoints(p0, ps);

    // look points
    StdOut.println("p0: " + p0.toString());
    for (Point p : ps) {
      StdOut.println(p.toString());
      StdOut.println(p0.slopeTo(p));
    }

    int c = 0;
    // double slp;
    double prev = p0.slopeTo(ps[0]);

    for (int i = 1; i < ps.length; i++) {
      double slp = p0.slopeTo(ps[i]);

      if (slp == prev) {
        c++;
        if (c >= 3) {
          StdOut.println(ps[i].toString());
          p0.drawTo(ps[i]);
        }
      } else {
        c = 0;
      }

      prev = slp;
    }

    // test end

    // show result canvas
    StdDraw.show(0);

  }
}