public class DoublingRatio3 {

  public static double timeTrial(int N) {
    // seclt a random max value index for the bitonics arry
    // the index not include the first and last item
    int maxIdx = StdRandom.uniform(1, N-1);

    int[] a = new int[N];
    for (int i = 0; i < N; i++) {
      if (i <= maxIdx) { a[i] = i; }
      else { a[i] = -i; }
      // StdOut.print(a[i] + ",");
    }
    // StdOut.println();

    int b = N + N / 3;
    int key = StdRandom.uniform(-b, b);
    // StdOut.println("key: " + key);
    Stopwatch timer = new Stopwatch();
    int result = BitonicSearch.rank(key, a);
    // // StdOut.println("result: " + result);
    // if (result != -1) StdOut.println("a[result]: " + a[result]);
    return timer.elapsedTime();
  }

  public static void main(String[] args) {
    double prev = timeTrial(125);
    StdOut.println(prev);
    for (int N = 250; true; N += N) {
      double time = timeTrial(N);
      StdOut.printf("%6d %7.1f %5.1f\n", N, time, time/prev);
      prev = time;
    }
  }

}