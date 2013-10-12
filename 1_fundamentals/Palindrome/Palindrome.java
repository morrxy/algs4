/*
 * java Palindrome noon
 */

public class Palindrome {
    public static void main(String[] args) {
        if (isPalindrome(args[0])) {
            StdOut.println("yes");
        } else {
            StdOut.println("no");
        }
    }

    public static boolean isPalindrome(String s) {
        int N = s.length();
        for (int i = 0; i < N / 2; i++) {
            if (s.charAt(i) != s.charAt(N-1-i)) {
                return false;
            }
         }
         return true;
    }

}