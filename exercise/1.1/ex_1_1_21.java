public class ex_1_1_21 {

    public static void main(String[] args) {
        String str;
        while (!StdIn.isEmpty()) {
            str = StdIn.readLine();
            String[] arr = str.split(" ");
            int d1 = Integer.parseInt(arr[1]);
            int d2 = Integer.parseInt(arr[2]);
            double f1 = (double)d2 / d1;
            StdOut.printf("%-6s %-6d %-6d %.3f\n", arr[0], d1, d2, f1);
        } 
    }
    
}