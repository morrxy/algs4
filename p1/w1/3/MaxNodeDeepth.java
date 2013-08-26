public class MaxNodeDeepth {
  public static void main(String[] args) {
    int[] nodes = StdIn.readAllInts();
    for (int i = 0; i < nodes.length; i++) {
      StdOut.println(i + ": " + countDeepth(nodes, i));
    }
  }

  public static int countDeepth(int[] arr, int n) {
    int deepth = 0;

    while(true) {
      if (arr[n] == n) break;

      n = arr[n];
      deepth += 1;
    }

    return deepth;
  }
}