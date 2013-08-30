public class DoublingRatio2 {

  public static double timeTrial(int N) {
    int MAX = 1000000;
    int[] a = new int[N];
    for (int i = 0; i < N; i++) {
      a[i] = StdRandom.uniform(-MAX, MAX);
    }
    Stopwatch timer = new Stopwatch();
    // int cnt = ThreeSum.count(a);
    int cnt = ThreeSumFast.count(a);
    return timer.elapsedTime();
  }

  public static void main(String[] args) {
    double prev = timeTrial(125);
    for (int N = 250; true; N += N) {
      double time = timeTrial(N);
      StdOut.printf("%6d %7.1f %5.1f\n", N, time, time/prev);
      prev = time;
    }
  }

}