public class PercolationStats {
  private double[] results;
  private int SIZE;
  private int TIMES;
  private double meanValue;
  private double stddevValue;
  private double confidenceLoValue;
  private double confidenceHiValue;

  /** perform T independent computational experiments on N-by_N grid */
  public PercolationStats(int N, int T) {
    if (N <= 0 || T <= 0) {
      throw new IllegalArgumentException("N or/and T not valid");
    }

    TIMES = T;
    SIZE = N;
    results = new double[TIMES];

    for (int i = 0; i < TIMES; i++) {
      results[i] = percolateOnce(SIZE);
    }

    calculateMean();
    calculateStddev();
    calculateCofidenceLo();
    calculateCofidenceHi();
  }

  private double percolateOnce(int n) {
    Percolation p = new Percolation(n);
    int opened = 0;

    // open a random site untill percolate
    while (true) {
      if (p.percolates()) break;

      // select a non-open site
      int row;
      int column;
      while (true) {
        row = StdRandom.uniform(1, SIZE + 1);
        column = StdRandom.uniform(1, SIZE + 1);
        if (!p.isOpen(row, column)) break;
      }

      // open that non-open site
      p.open(row, column);

      opened += 1;
    }

    return opened / (SIZE * SIZE * 1.0);
  }

  private void calculateMean() {
    double t = 0.0;
    for (int i = 0; i < TIMES; i++) {
      t += results[i];
    }
    meanValue = t / TIMES;
  }

  private void calculateStddev() {
    double t = 0.0;
    for (int i = 0; i < TIMES; i++) {
      double x = results[i] - meanValue;
      t += x * x;
    }

    if (TIMES != 1) {
      stddevValue = Math.sqrt(t / (TIMES - 1));
    } else {
      stddevValue = Double.NaN;
    }
  }

  private void calculateCofidenceLo() {
    if (TIMES != 1) {
      confidenceLoValue = meanValue - (1.96 * stddevValue) / Math.sqrt(TIMES);
    } else {
      confidenceLoValue = Double.NaN;
    }
  }

  private void calculateCofidenceHi() {
    if (TIMES != 1) {
      confidenceHiValue = meanValue + (1.96 * stddevValue) / Math.sqrt(TIMES);
    } else {
      confidenceHiValue = Double.NaN;
    }
  }

  /** sample mean of percolation threshold */
  public double mean() {
    return meanValue;
  }

  /** sample standard deviation of percolation threshold */
  public double stddev() {
    return stddevValue;
  }

  /** return lower bound of the 95% confidence interval */
  public double confidenceLo() {
    return confidenceLoValue;
  }

  /** return upper bound of the 95% confidence teterval */
  public double confidenceHi() {
    return confidenceHiValue;
  }

  /** performs T independent computational experiments on an N-by-N grid */
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);

    Stopwatch timer = new Stopwatch();
    PercolationStats ps = new PercolationStats(N, T);
    double time = timer.elapsedTime();

    StdOut.printf("%-23s = %.16f\n", "mean", ps.mean());
    StdOut.printf("%-23s = %.18f\n", "stddev", ps.stddev());
    StdOut.printf("%-23s = %.16f, %.16f\n", "95% confidence interval",
      ps.confidenceLo(), ps.confidenceHi());

    StdOut.println("time: " + time);

  }

}