/*
 * java ex_1_1_29 tinyW.txt < tinyT.txt
 * sorted whitelist:
 * 10 11 12 16 18 23 29 33 48 48 54 57 68 77 84 98
 * key: 23 i: 5 j: 1
 * key: 50 i: 10 j: 0
 * key: 10 i: 0 j: 1
 * .................
 * 
 * Equal keys.  Add to BinarySearch a static method rank() that takes a key and 
 * a sorted array of int values (some of which may be equal) as arguments and returns the 
 * number of elements that are smaller than the key and a similar method count() that 
 * returns the number of elements equal to the key. Note : If i and j are the values returned 
 * by rank(key, a) and count(key, a) respectively, then a[i..i+j-1] are the values in 
 * the array that are equal to key.
 */
import java.util.Arrays;

public class ex_1_1_29 {
    
    public static void main(String[] args) {
        
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);

        StdOut.println("sorted whitelist:");
        for (int i = 0; i < whitelist.length; i++) {
            if (i == whitelist.length - 1) {
                StdOut.print(whitelist[i] + "\n");
            } else {
                StdOut.print(whitelist[i] + " ");
            }
        }

        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            int i = rank(key, whitelist);
            int j = count(key, whitelist);

            StdOut.print("key: " + key +" ");
            StdOut.print("i: " + i + " ");
            StdOut.print("j: " + j + "\n");
        }
    }
    
    // returns the number of elements that are smaller than the key
    public static int rank(int key, int[]a) {
        if (key < a[0]) {
            return 0;
        }

        if (key > a[a.length - 1]) {
            return a.length;
        }

        int c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < key) {
                c += 1;
            } else {
                break;
            }
        }
        return c;
    }

    public static int count(int key, int[]a) {

        if (key < a[0] || key > a[a.length - 1]) { return 0; }

        int c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) {
                c += 1;
            }
            if (a[i] > key) {
                break;
            }
        }
        return c;
    }

}