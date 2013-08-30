public class BitonicSearch {
  public static int rank(int key, int[] a) {
    int lo = 0;
    int hi = a.length - 1;
    int max = searchMax(a);
    // StdOut.println("max:" + max);

    int leftResult = biSearch(key, a, 0, max);

    if (leftResult != -1) {
      return leftResult;
    } else {
      return biSearchRev(key, a, max + 1, a.length - 1);
    }

  }

  public static int biSearch(int key, int[] a, int lo, int hi) {
    if (lo > hi) return -1;

    int mid = lo + (hi - lo) / 2;
    if (key < a[mid]) {
      return biSearch(key, a, lo, mid - 1);
    } else if (key > a[mid]) {
      return biSearch(key, a, mid + 1, hi);
    } else {
      return mid;
    }
  }

  public static int biSearchRev(int key, int[] a, int lo, int hi) {
    if (lo > hi) return -1;

    int mid = lo + (hi - lo) / 2;
    if (key > a[mid]) {
      return biSearchRev(key, a, lo, mid - 1);
    } else if (key < a[mid]) {
      return biSearchRev(key, a, mid + 1, hi);
    } else {
      return mid;
    }
  }

  public static int searchMax(int[] a) {
    return searchMax(a, 0, a.length - 1);
  }

  public static int searchMax(int[] a, int lo, int hi) {
    int mid = lo + (hi - lo) / 2;
    if (a[mid] > a[mid-1] && a[mid] > a[mid + 1]) return mid;
      else if (a[mid] < a[mid-1] && a[mid] > a[mid+1])
        return searchMax(a, lo, mid-1);
      else
        return searchMax(a, mid+1, hi);
  }

  public static void main(String[] args) {
    int[] t = {0,1,-2,-3,-4,-5,-6,-7,-8,-9};
    // int[] t = {1, 2, -1};
    // BitonicSearch bs = new BitonicSearch();
    // StdOut.println(BitonicSearch.rank(1, t));
    // StdOut.println(BitonicSearch.rank(2, t));
    StdOut.println(BitonicSearch.rank(0, t));
    StdOut.println(BitonicSearch.rank(1, t));
    StdOut.println(BitonicSearch.rank(-2, t));
    StdOut.println(BitonicSearch.rank(-8, t));
    StdOut.println(BitonicSearch.rank(-9, t));
  }

}