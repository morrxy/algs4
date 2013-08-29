public class BitonicSearch {
  public static int bitonicRank(int key, int[] a) {
    int lo = 0;
    int hi = a.length - 1;
    // int max = searchMax(a);

    return -1;
  }

  public static void main(String[] args) {
    int[] t = {1, 2, 4, 7, 9, 6, 5, 3};
    StdOut.println(bitonicRank(4, t));
    StdOut.println(bitonicRank(0, t));
  }

}