/*
 * Write a code fragment to print the transposition (rows and columns changed) of 
 * a two-dimensional array with M rows and N columns.
 */

import java.util.Arrays;

public class ex_1_1_13 {
    public static void main(String[] args) {
        
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}};
        
        int i = a.length;
        int j = a[0].length;
        int[][] b = new int[j][i];
        
        for (int m = 0; m < i; m++) {
            for (int n = 0; n < j; n++) {
                b[n][m] = a[m][n];
            }
        }
        
        for (int m = 0; m < b.length; m++) {
            for (int n = 0; n < b[0].length; n++) {
                StdOut.println(b[m][n]);
            }
        }

    }
}