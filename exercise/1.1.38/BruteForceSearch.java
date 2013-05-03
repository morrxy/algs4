/* 
 * exercise 1.1.38
 * Binary search versus brute-force search.
 * Write a program BruteForceSearch that uses the brute-force search method given 
 * on page 48 and compare its running time on your computer with that of BinarySearch 
 * for largeW.txt and largeT.txt.
 *
 * java BruteForceSearch largeW.txt largeT.txt
 * 367966 not in sorted whitelist
 * Binary search time: 0.25
 * 367966 not in whitelist
 * Brute-force search time: 316.657
 */

import java.util.Arrays;

public class BruteForceSearch {

    public static void main(String[] args) {
        
    	// make white list
        In in1 = new In(args[0]);
        int[] whitelist = in1.readAllInts();
        in1.close();

        // make sorted whie list
        int[] whitelist_sorted = new int[whitelist.length];
        for (int i = 0; i < whitelist_sorted.length; i++) {
        	whitelist_sorted[i] = whitelist[i];
        }
        Arrays.sort(whitelist_sorted);

        // make test list
        In in2 = new In(args[1]);
        int[] test = in2.readAllInts();
        in2.close();
        
        // calculate time using binary search
        double time_binary = calculate_binary_time(whitelist_sorted, test);
        StdOut.println("Binary search time: " + time_binary);

        // calculate time using brute-force search
        double time_bruteforce = calculate_brute_time(whitelist, test);
        StdOut.println("Brute-force search time: " + time_bruteforce);

    }

    public static double calculate_binary_time(int[] w, int[] t) {

    	int n = 0;

        Stopwatch watch_binary = new Stopwatch();

        for (int i = 0; i < t.length; i++) {
            if (rank_binary(t[i], w) == -1) {
            	n += 1;
            }
        }

        double time_binary = watch_binary.elapsedTime();

        StdOut.println(n + " not in sorted whitelist");

        return time_binary;

    }

    public static double calculate_brute_time(int[] w, int[] t) {

    	int n = 0;

        Stopwatch watch_bruteforce = new Stopwatch();

        for (int i = 0; i < t.length; i++) {
            if (rank_bruteforce(t[i], w) == -1) {
            	n += 1;
            }
        }

        double time_bruteforce = watch_bruteforce.elapsedTime();

        StdOut.println(n + " not in whitelist");

        return time_bruteforce;

    }
    
    public static int rank_binary(int key, int[] a) {
    
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

    public static int rank_bruteforce(int key, int[] a) {
    	for (int i = 0; i < a.length; i++) {
    		if (a[i] == key) {
    			return i;
    		}
    	}
    	return -1;
    }


}
