/*
 * Add to the BinarySearch test client the ability to respond to a second argu-ment: 
 * + to print numbers from standard input that are not in the whitelist, 
 * - to print numbers that are in the whitelist
 *
 * java ex_1_1_23 tinyW.txt + < tinyT.txt
 * 50
 * 99
 * 13
 *
 * java ex_1_1_23 tinyW.txt - < tinyT.txt
 * 23
 * 10
 * 18
 * ...
 */
import java.util.Arrays;

public class ex_1_1_23 {
    
    public static void main(String[] args) {
        
        int[] whitelist = In.readInts(args[0]);
        String listWay = args[1];
        
        Arrays.sort(whitelist);
        
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (listWay.equals("+")) {
                if (rank(key, whitelist) == -1)
                    StdOut.println(key);
            }
            if (listWay.equals("-")) {
                if (rank(key, whitelist) != -1)
                    StdOut.println(key);
            }
        }
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