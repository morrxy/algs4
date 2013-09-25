import java.util.*;
import java.lang.*;

public class Fast_scott {

   public static void main(String[] args)
   {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        Point[] others = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
        }

        Arrays.sort(points, 0, N);

        for (int p = 0; p < N; p++) {

            points[p].draw();

            for (int i = 0; i < N; i++)
                others[i] = points[i];

            // sort the points according to the slopes they makes with p
            Arrays.sort(others, 0, N, points[p].SLOPE_ORDER);

            // Check if any 3 (or more) adjacent points in the sorted order
            // have equal slopes with respect to p.
            // If so, these points, together with p, are collinear.
            for (int i = 0, number = 0, dup = 0; i < N - 1; )
            {
                while (points[p].slopeTo(others[i]) == points[p].slopeTo(others[++i]))
                {
                    number++;

                    if (others[i-1].compareTo(points[p]) <= 0 || others[i].compareTo(points[p]) <= 0)
                        dup = 1;

                    if (i >= N - 1) {
                        i++; // corner case
                        break;
                    }
                }

                if (number >= 2 && dup == 0) {
                    // print the line
                    StdOut.print(points[p] + " -> ");
                    for (int j = number; j > 0; j--)
                        StdOut.print(others[i - 1 - j] + " -> ");
                    StdOut.println(others[i - 1]);

                    points[p].drawTo(others[i - 1]);
                }

                number = 0;
                dup = 0;
            }
        }

        // display to screen all at once
        StdDraw.show(0);
   }
}
