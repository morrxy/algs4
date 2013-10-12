/*
 * Binomial distribution. Estimate the number of recursive calls that
 * would be used by the code to compute binomial(100, 50). Develop a 
 * better implementation that is based on saving computed values in an array.
 */
public class ex_1_1_27 {
 public static void main(String[] args) {
  int N = Integer.parseInt(args[0]);
  int k = Integer.parseInt(args[1]);
  double p = Double.parseDouble(args[2]);
  StdOut.println(binomial(N, k, p));
 }

 public static double binomial(int N, int k, double p) {
  if ((N == 0) && (k == 0)) return 1.0;
  if (N < 0 || k < 0) return 0.0;
  return (1.0 - p) * binomial(N-1, k, p) + p * binomial(N-1, k-1, p);
 }
}