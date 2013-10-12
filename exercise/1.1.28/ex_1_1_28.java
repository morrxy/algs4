/*
 * java ex_1_1_28 tinyW.txt < tinyT.txt
 * 50
 * 99
 * 13
 * Remove duplicates.  Modify the test client in BinarySearch 
 * to remove any duplicate keys in the whitelist after the sort.
 */
import java.util.Arrays;
import java.util.ArrayList;

public class ex_1_1_28 {
    
    public static void main(String[] args) {
        
        int[] whitelist = In.readInts(args[0]);
        
        Arrays.sort(whitelist);
        whitelist = removeDuplicate(whitelist);
        
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

    private static int[] removeDuplicate(int[] a) {
        ArrayList<Integer> intlist = new ArrayList<Integer>();

        intlist.add(a[0]);
        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[i - 1]) {
                intlist.add(a[i]);
            }
        }

        int size = intlist.size();
        int[] a1 = new int[size];

        for (int i = 0; i < a1.length; i++) {
            a1[i] = intlist.get(i);
        }

        return a1;
    }
    
}