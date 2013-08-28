public class Percolation {
  private WeightedQuickUnionUF uf;
  // siteStatus[i] is true means that the site is open,false is block
  private boolean[] siteStatus;
  private int SIZE;
  private int VIRTUAL_TOP;
  private int VIRTUAL_BOTTOM;
  private WeightedQuickUnionUF ufForIsFull;

  /** create N-by-N grid, with all sites blocked */
  public Percolation(int N) {
    SIZE = N;
    // the last two site for VIRTUAL_TOP and VIRTUAL_BOTTOM
    uf = new WeightedQuickUnionUF(SIZE * SIZE + 2);
    VIRTUAL_TOP = SIZE * SIZE;
    VIRTUAL_BOTTOM = SIZE * SIZE + 1;

    // initialize all site blocked
    siteStatus = new boolean[SIZE*SIZE + 2];
    for (int i = 0; i < SIZE*SIZE; i++) {
      siteStatus[i] = false;
    }
    // initialize two virtual site open
    siteStatus[VIRTUAL_TOP] = true;
    siteStatus[VIRTUAL_BOTTOM] = true;

    // connect each site in first row to VIRTUAL_TOP
    for (int i = 0; i < SIZE; i++) {
      uf.union(VIRTUAL_TOP, i);
    }

    // connect each site in last row to VIRTUAL_BOTTOM
    for (int i = (SIZE-1)*SIZE; i < SIZE*SIZE; i++) {
      uf.union(VIRTUAL_BOTTOM, i);
    }
  }

  /** open site (row i, column j) if it is not already */
  // i,j between 1 and N
  public void open(int i, int j) {
    if (!validIndex(i, j)) {
      throw new IndexOutOfBoundsException("row or/and column index out of bounds");
    }

    if (!isOpen(i, j)) {
      openSite(i, j);
      connectAdjacentSite(i, j);
    }
  }

  private void openSite(int i, int j) {
    int idx = xyTo1D(i, j);
    siteStatus[idx] = true;
  }

  private void connectAdjacentSite(int i, int j) {
    connectTwoSite(i, j, i-1, j);
    connectTwoSite(i, j, i+1, j);
    connectTwoSite(i, j, i  , j-1);
    connectTwoSite(i, j, i  , j+1);
  }

  private void connectTwoSite(int x1, int y1, int x2, int y2) {
    if (validIndex(x2, y2)) {
      if (isOpen(x2, y2)) {
        int p = xyTo1D(x1, y1);
        int q = xyTo1D(x2, y2);
        uf.union(p, q);
      }
    }
  }

  /** is site (row i, column j) open? */
  public boolean isOpen(int i, int j) {
    int n = xyTo1D(i, j);
    return siteStatus[n];
  }

  /** is site (row i, column j) full? */
  public boolean isFull(int i, int j) {
    int p = xyTo1D(i, j);
    return isOpen(i, j) && uf.connected(p, VIRTUAL_TOP);
  }

  /** does the system percolate? */
  public boolean percolates() {
    return uf.connected(VIRTUAL_BOTTOM, VIRTUAL_TOP);
  }

  // convert 2 dimensional(row, column) pair to 1 dimensional index
  private int xyTo1D(int r, int c) {
    return ((r - 1) * SIZE + c) - 1;
  }

  private boolean validIndex(int r, int c) {
    if (r < 1 || r > SIZE || c < 1 || c > SIZE) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    Percolation p = new Percolation(10);
    p.open(1, 1);
    p.open(1, 2);

    StdOut.println(p.percolates());
    StdOut.println(p.isFull(1, 1));
    StdOut.println(p.isFull(1, 2));
    StdOut.println(p.isFull(1, 3));
  }

}