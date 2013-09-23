import java.util.Arrays;

public class Fast {
  public static void sortPoints(Point p, Point[] ps) {
    Arrays.sort(ps, p.SLOPE_ORDER);
  }

  // is ps sorted of slope to p?
  public static boolean isSorted(Point p, Point[] ps) {
    for (int i = 1; i < ps.length; i++) {
      if (p.slopeTo(ps[i]) < p.slopeTo(ps[i-1])) {
        return false;
      }
    }
    return true;
  }

  public static Point[] rightPoints(int i, Point[] ps) {
    Point[] arr = new Point[ps.length - i - 1];
    for (int k = 0; k < arr.length; k++) {
      arr[k] = ps[k + i + 1];
    }
    return arr;
  }

  public static void showPoints(Point[] ps) {
    StdOut.println();
    for (Point p : ps) {
      StdOut.print(p.toString() + ", ");
    }
    StdOut.println();
  }

  public static void showResultPoints(Point[] ps) {
    StdOut.println();
    for (int i = 0; i < ps.length; i++) {
      StdOut.print(ps[i].toString());
      if (i != ps.length - 1) StdOut.print(" -> ");
    }
    StdOut.println();
  }

  public static void outPut(Point[] ps, int from, int to, Point p0) {

    StdOut.println("output");
    StdOut.println(p0.toString());
    showPoints(ps);
    StdOut.println("from:" + from);
    StdOut.println("to:" + to);


    Point[] arr = new Point[to - from + 1 + 1];
    for (int i = 0; i < arr.length - 1 ; i++) {
      arr[i] = ps[from];
      from += 1;
    }
    arr[arr.length - 1] = p0;

    Arrays.sort(arr);
    arr[0].drawTo(arr[arr.length-1]);
    showResultPoints(arr);
  }

  public static void checkAdjacentPoints(Point[] ps, Point p0) {
    if (ps.length < 3) return;

    int c = 0;
    double prev = p0.slopeTo(ps[0]);

    for (int i = 1; i < ps.length; i++) {
      double slp = p0.slopeTo(ps[i]);

      if (slp == prev) {
        c++;
      } else {
        if (c >= 3) {
          outPut(ps, i - 1 - c, i - 1, p0);
        }
        c = 0;
      }

      prev = slp;
    }

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

    for (int i = 0; i < N; i++) {
      Point p0 = points[i];
      Point[] ps = rightPoints(i, points);
      // showPoints(ps);
      Arrays.sort(ps, p0.SLOPE_ORDER);
      // showPoints(ps);
      checkAdjacentPoints(ps, p0);

    }

    // Point p0 = points[0];
    // Point[] ps = new Point[N-1];
    // for (int i = 0; i < N-1; i++) {
    //   ps[i] = points[i+1];
    // }

    // look points
    // StdOut.println("p0: " + p0.toString());
    // for (Point p : ps) {
    //   StdOut.println(p.toString());
    // }

    // sortPoints(p0, ps);
    // assert isSorted(p0, ps);

    // look points
    // StdOut.println("p0: " + p0.toString());
    // for (Point p : ps) {
    //   StdOut.println(p.toString());
    //   StdOut.println(p0.slopeTo(p));
    // }

    // int c = 0;
    // double slp;
    // double prev = p0.slopeTo(ps[0]);

    // for (int i = 1; i < ps.length; i++) {
    //   double slp = p0.slopeTo(ps[i]);

    //   if (slp == prev) {
    //     c++;
    //     if (c >= 3) {
    //       StdOut.println("c:" + c);
    //       StdOut.println(ps[i].toString());
    //       p0.drawTo(ps[i]);
    //     }
    //   } else {
    //     c = 0;
    //   }

    //   prev = slp;
    // }

    // test end

    // show result canvas
    StdDraw.show(0);

  }
}