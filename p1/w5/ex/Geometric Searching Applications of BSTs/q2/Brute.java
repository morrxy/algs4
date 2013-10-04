import java.util.Arrays;

public class Brute {
  public static void main(String[] args) {

    StdDraw.setXscale(0, 100);
    StdDraw.setYscale(0, 100);
    StdDraw.show(0);



    String filename = args[0];
    In in = new In(filename);
    int N = in.readInt();
    Point[] points = new Point[N];

    String name = "A";
    for (int i = 0; i < N; i++) {
      int x = in.readInt();
      int y = in.readInt();
      Point p = new Point(x, y);
      points[i] = p;
      p.draw();
      String cor = p.x + ", " + p.y;

      StdDraw.text(points[i].x, points[i].y, name + cor);
      // points[i].drawTo(points[i - 1]);

      int charValue = name.charAt(0);
      name = String.valueOf( (char) (charValue + 1));
    }

    // Arrays.sort(points);

    // for (int i = 0; i < N; i++) {
    //   for (int j = i + 1; j < N; j++) {
    //     for (int k = j + 1; k < N; k++) {
    //       for (int m = k + 1; m < N; m++) {
    //         double slp1 = points[i].slopeTo(points[j]);
    //         double slp2 = points[i].slopeTo(points[k]);
    //         double slp3 = points[i].slopeTo(points[m]);
    //         if (slp1 == slp2 && slp2 == slp3) {
    //           StdOut.println(points[i].toString() + " -> "
    //             + points[j].toString() + " -> " + points[k].toString()
    //             + " -> " + points[m].toString());
    //           points[i].drawTo(points[m]);
    //         }
    //       }
    //     }
    //   }
    // }

    // display to screen all at once
    StdDraw.show(0);
  }
}