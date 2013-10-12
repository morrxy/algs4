/*
 * 1.1.15 Write a static method histogram() that takes an 
 * array a[] of int values and an integer M as arguments and
 * returns an array of length M whose ith entry is the number 
 * of times the integer i appeared in the argument array. If 
 * the values in a[] are all between 0 and MÐ1, the sum of the 
 * values in the returned array should be equal to a.length.
 */

import java.util.Arrays;

public class ex_1_1_15 {
    
    public static void main(String[] args) {
        int[] a = {1, 1, 2, 3, 5, 5, 8};
        int[] b = histogram(a, 9);
        StdOut.println(Arrays.toString(a));
        StdOut.println(Arrays.toString(b));
    }
    
    public static int[] histogram(int[] a, int M) {
        int[] result = new int[M];
        for (int i = 0; i < a.length; i++) {
            result[a[i]] += 1;
        }
        return result;
    }
    
}