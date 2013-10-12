/**
 * exercise 1.2.9
 * Instrument BinarySearch (page 47) to use a Counter to count the total number
 * of keys examined during all searches and then print the total after all searches
 * are complete. Hint : Create a Counter in main() and pass it as an argument to rank().
 *
 *  % java InstrumentBinarySearch tinyW.txt < tinyT.txt
 *  50
 *  99
 *  13
 *  total number of keys: 18
 * 
 */

import java.util.Arrays;

public class InstrumentBinarySearch {
    
    public static void main(String[] args) {
        
        int[] whitelist = In.readInts(args[0]);
        
        Arrays.sort(whitelist);

        Counter ct = new Counter("ct");
        
        while (!StdIn.isEmpty()) {
        	ct.increment();
            int key = StdIn.readInt();
            if (rank(key, whitelist) == -1)
                StdOut.println(key);
        }

        StdOut.println("total number of keys: " + ct.tally());
    }
    
    public static int rank(int key, int[] a) {
    
        int lo = 0;
        int hi = a.length - 1;
        
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        
        return -1;
        
    }
    
}