public class PercolationStats {

  /** perform T independent computational experiments on N-by_N grid */
  public PercolationStats(int N, int T) {

  }

  /** sample mean of percolation threshold */
  public double mean() {
    return 0.0;
  }

  /** sample standard deviation of percolation threshold */
  public double stddev() {
    return 0.0;
  }

  /** return lower bound of the 95% confidence interval */
  public double confidenceLo() {
    return 0.0;
  }

  /** return upper bound of the 95% confidence teterval */
  public double confidenceHi() {
    return 0.0;
  }

  /** performs T independent computational experiments on an N-by-N grid */
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);

    StdOut.println("N:" + N);
    StdOut.println("T:" + T);
  }

}