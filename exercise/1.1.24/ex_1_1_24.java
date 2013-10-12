/*
 * Give the sequence of values of p and q that are computed when Euclid’s algo-rithm is 
 * used to compute the greatest common divisor of 105 and 24. Extend the code given on 
 * page 4 to develop a program  Euclid that takes two integers from the command line and 
 * computes their greatest common divisor, printing out the two arguments for each call on 
 * the recursive method. Use your program to compute the greatest common divisor or 1111111 and 1234567
 */

public class ex_1_1_24 {

 public static void main(String[] args) {
  int int1 = Integer.parseInt(args[0]);
  int int2 = Integer.parseInt(args[1]);
  StdOut.println(gcd(int1, int2));
 }

 public static int gcd(int p, int q) {
  StdOut.println(p + " " + q);
  if (q == 0) {
   return p;
  }
  int r = p % q;
  return gcd(q, r);
 }

}