import java.util.Arrays;

public class Fast {
  private static void sortPoints(Point p, Point[] ps) {
    Arrays.sort(ps, p.SLOPE_ORDER);
  }

  // is ps sorted of slope to p?
  private static boolean isSorted(Point p, Point[] ps) {
    for (int i = 1; i < ps.length; i++) {
      if (p.slopeTo(ps[i]) < p.slopeTo(ps[i-1])) {
        return false;
      }
    }
    return true;
  }

  private static Point[] rightPoints(int i, Point[] ps) {
    Point[] arr = new Point[ps.length - i - 1];
    for (int k = 0; k < arr.length; k++) {
      arr[k] = ps[k + i + 1];
    }
    return arr;
  }

  private static Point[] otherPoints(int exclude, Point[] ps) {
    Point[] arr = new Point[ps.length - 1];
    int j = 0;
    for (int i = 0; i < ps.length; i++) {
      if (i == exclude) continue;
      arr[j] = ps[i];
      j++;
    }
    return arr;
  }

  private static void showPoints(Point[] ps) {
    for (int i = 0; i < ps.length; i++) {
      if (i != ps.length-1) StdOut.print(ps[i].toString() + ", ");
      else StdOut.print(ps[i].toString() + "\n");
    }
  }

  private static void showResultPoints(Point[] ps) {
    for (int i = 0; i < ps.length; i++) {
      StdOut.print(ps[i].toString());
      if (i != ps.length - 1) StdOut.print(" -> ");
      else StdOut.print("\n");
    }
  }

  private static void showSlopes(Point[] ps, Point p0) {
    for (int i = 0; i < ps.length; i++) {
      if (i != ps.length-1) StdOut.print(p0.slopeTo(ps[i]) + ", ");
      else StdOut.print(p0.slopeTo(ps[i]) + "\n");
    }
  }

  private static void outPutAdjacent(Point[] ps, int toIdx, int count, Point p0) {
    Point[] arr = new Point[count + 1];

    // which index start from ps
    int from = toIdx - count + 1;

    // input points in ps to arr
    for (int i = 0; i < arr.length - 1; i++) {
      arr[i] = ps[from];
      from += 1;
    }

    // input p0 as last item of arr
    arr[arr.length - 1] = p0;

    // output
    Arrays.sort(arr);
    showResultPoints(arr);
    arr[0].drawTo(arr[arr.length-1]);
  }

  // in ps,find adjacent points having equal slopte with p0
  // if these points have 3 or more, output these point plus p0
  private static void checkAdjacentPoints(Point[] ps, Point p0) {
    if (ps.length < 3) return;

    // first slope
    double prev = p0.slopeTo(ps[0]);

    // count of adjacent points with same slope to p0 for current point in ps
    int c = 1;

    for (int i = 1; i < ps.length; i++) {
      double slp = p0.slopeTo(ps[i]);

      if (slp == prev) {
        c++;
      } else {
        if (c >= 3) {
          outPutAdjacent(ps, i - 1, c, p0);
        }
        // reset count
        c = 1;
      }

      prev = slp;
    }

    // after loop if c >= 3, porcess once again
    if (c >= 3) {
      outPutAdjacent(ps, ps.length-1, c, p0);
    }

  }

  private static void checkAdjacentPoints2(Point[] ps, Point p0) {
    if (ps.length < 3) return;

    // first slope
    double prev = p0.slopeTo(ps[0]);

    // count of adjacent points with same slope to p0 for current point in ps
    int c = 1;

    int[] longest = new int[2];
    // StdOut.println(longest[0] + "," + longest[1]);

    for (int i = 1; i < ps.length; i++) {
      double slp = p0.slopeTo(ps[i]);

      if (slp == prev) {
        c++;
      } else {
        if (c >= 3) {
          // outPutAdjacent(ps, i - 1, c, p0);
          if (c > longest[1]) {
            longest[0] = i - 1;
            longest[1] = c;
          }
        }
        // reset count
        c = 1;
      }

      prev = slp;
    }

    // after loop if c >= 3, porcess once again
    if (c >= 3) {
      // outPutAdjacent(ps, ps.length-1, c, p0);
      if (c > longest[1]) {
        longest[0] = ps.length-1;
        longest[1] = c;
      }
    }

    // output longest line segment
    if (longest[1] != 0) {
      outPutAdjacent(ps, longest[0], longest[1], p0);
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

      // all points right the curent p0
      Point[] ps = rightPoints(i, points);

      Arrays.sort(ps, p0.SLOPE_ORDER);
      // checkAdjacentPoints(ps, p0);
      checkAdjacentPoints2(ps, p0);
    }
    // test end

    // show result canvas
    StdDraw.show(0);

  }
}