public class PercolationStats {
  private double[] results;
  private int size;
  private int experiment_times;

  /** perform T independent computational experiments on N-by_N grid */
  public PercolationStats(int N, int T) {
    experiment_times = T;
    size = N;
    results = new double[experiment_times];

    for (int i = 0; i < experiment_times; i++) {
      results[i] = percolation_once(size);
      // StdOut.println(results[i]);
    }
  }

  private double percolation_once(int n) {
    Percolation p = new Percolation(n);
    int opened = 0;
    while(true) {
      if (p.percolates()) break;

      // select a non-open site
      int row;
      int column;
      while(true) {
        row = StdRandom.uniform(1, size + 1);
        column = StdRandom.uniform(1, size + 1);
        if (!p.isOpen(row, column)) break;
      }

      // open that non-open site
      p.open(row, column);
      opened += 1;
    }

    return opened / (size * size * 1.0);
  }

  /** sample mean of percolation threshold */
  public double mean() {
    double t = 0;
    for (int i = 0; i < experiment_times; i++) {
      t += results[i];
    }
    return t / experiment_times;
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

    PercolationStats ps = new PercolationStats(N, T);
    StdOut.printf("%-23s = %f\n", "mean", ps.mean());
    StdOut.printf("%-23s = %f\n", "stddev", ps.stddev());
    StdOut.printf("%-23s = %f, %f\n", "95% confidence interval", ps.confidenceLo(), ps.confidenceHi());
  }

}