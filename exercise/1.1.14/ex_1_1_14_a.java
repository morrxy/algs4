public class ex_1_1_14 {
    
    public static void main(String[] args) {
        
        int n = Integer.parseInt(args[0]);
        
        int i = 0;
        
        while(true) {
            if (n / 2 == 0) {
                break;
            }
            i += 1;
            n /= 2;
        }
        
        StdOut.println(i);

    }
}