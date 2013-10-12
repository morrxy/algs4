//import java.util.Arrays;

public class ex_1_1_19 {
    
    private static long[] arr = new long[100];
    
    public static long F(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N-1) + F(N-2);
    }
    
    public static long F1(int N) {
        if (N == 0) {
            arr[0] = 0;
        } else if (N == 1) {
            arr[1] = 1;
        } else {
            arr[N] = arr[N-1] + arr[N-2];
        }
        return arr[N];
    }
    
    public static void main(String[] args) {
        for (int N = 0; N < 100; N++)
            StdOut.println(N + " " + F1(N));
    }
    
}