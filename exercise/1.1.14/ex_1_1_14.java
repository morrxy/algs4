//Write a static method lg() that takes an int value N as argument 
//and returns the largest int not larger than the base-2 logarithm of N. 
//Do not use Math
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