import java.util.Arrays;

public class ex_1_1_11 {
    public static void main(String[] args) {
        boolean[][] a = {{true, false}, {false, true}};
        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                StdOut.print(' ');
                for (int k = 0; k < a[0].length; k++) {
                    StdOut.print(k);
                }
                StdOut.print('\n');
            }
            StdOut.print(i);
            for (int j = 0; j < a[i].length; j++) {
                //StdOut.print(j);
                StdOut.print(a[i][j] == true ? '*' : ' ');
            }
            StdOut.print('\n');
        }
    }
}