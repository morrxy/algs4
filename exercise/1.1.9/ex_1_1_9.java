public class ex_1_1_9 {
    public static void main(String[] args) {
        String s = "";
        int N = Integer.parseInt(args[0], 10);
        for (int n = N; n > 0; n /= 2) {
            s = (n % 2) + s;
        }
        StdOut.println(s);      
    }
}