/*************************************************************************
 *  Compilation:  javac BinarySearch1.java
 *  Execution:    java BinarySearch1 whitelist.txt < input.txt
 *  Data files:   http://algs4.cs.princeton.edu/11model/tinyW.txt
 *                http://algs4.cs.princeton.edu/11model/tinyT.txt
 *                http://algs4.cs.princeton.edu/11model/largeW.txt
 *                http://algs4.cs.princeton.edu/11model/largeT.txt
 *
 *  % java BinarySearch1 tinyW.txt < tinyT.txt
 *  50
 *  99
 *  13
 *
 *  % java BinarySearch1 largeW.txt < largeT.txt | more
 *  499569
 *  984875
 *  295754
 *  207807
 *  140925
 *  161828
 *  [3,675,966 total values]
 *
 *************************************************************************/

import java.util.Arrays;

/**
 *  The <tt>BinarySearch1</tt> class provides a static method for binary
 *  searching for an integer in a sorted array of integers.
 *  <p>
 *  The <em>rank</em> operations takes logarithmic time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/11model">Section 1.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class BinarySearch1 {

    /**
     * This class should not be instantiated.
     */
    private BinarySearch1() { }

    /**
     * Searches for the integer key in the sorted array a[].
     * @param key the search key
     * @param a the array of integers, must be sorted in ascending order
     * @return index of key in array a[] if present; -1 if not present
     */
    public static int rank1(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            StdOut.print(a[mid] + " ");
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        StdOut.println();
        return -1;
    }

    /**
     * Reads in a sequence of integers from the whitelist file, specified as
     * a command-line argument. Reads in integers from standard input and
     * prints to standard output those integers that also appear in the file.
     */
    public static void main(String[] args) {

        // read in the integers from a file
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            rank1(key, whitelist);
            // StdOut.println(rank1(key, whitelist));
        }

        // read key; print if not in whitelist
        // while (!StdIn.isEmpty()) {
        //     int key = StdIn.readInt();
        //     if (rank1(key, whitelist) == -1)
        //         StdOut.println(key);
        // }
    }
}