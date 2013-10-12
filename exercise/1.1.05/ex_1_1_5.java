public class ex_1_1_5 {
    public static void main(String[] args) {
        compare(0.2, 0.5);
        compare(0.3, 4.0);
    }
    
    private static void compare(double x, double y) {
        if ((x > 0 && x < 1) && (y > 0 && y < 1)) {
            StdOut.println("true");
        } else {
            StdOut.println("false");
        }
    }
}