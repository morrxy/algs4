public class ex_1_1_7a {
    public static void main(String[] args) {
        double t = 9.0;
        while(Math.abs(t - 9.0/t) > .001) {
            StdOut.printf("%.5f\n", t);
        }
    }
}