/*
 * java ex_1_1_28 tinyW.txt < tinyT.txt
 * 50
 * 99
 * 13
 * Remove duplicates.  Modify the test client in BinarySearch 
 * to remove any duplicate keys in the whitelist after the sort.
 */
import java.util.Arrays;

public class ex_1_1_28 {
    
    public static void main(String[] args) {
        
        int[] whitelist = In.readInts(args[0]);
        
        Arrays.sort(whitelist);
        
        StdOut.println("whitelist:");
        for (int i : whitelist) {
            StdOut.println(i);
        }
        
        StdOut.println("matched:");
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist) == -1)
                StdOut.println(key);
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