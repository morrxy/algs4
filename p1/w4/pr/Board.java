public class Board {

  private int N;
  private int[][] tiles;

  // construct a board from an N-by-N array of blocks
  // (where blocks[i][j] = block in row i, column j)
  public Board(int[][] blocks) {
    N = blocks.length;
    tiles = new int[N][N];

    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        tiles[i][j] = blocks[i][j];
  }

  // board dimension N
  public int dimension() {
    return N;
  }

  // number of blocks out of place
  public int hamming() {
    int h = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (tiles[i][j] == 0) continue;
        if (tiles[i][j] != idxToValue(i, j)) h += 1;
      }
    }
    return h;
  }

  private int idxToValue(int i, int j) {
    return i * N + j + 1;
  }

  // sum of Manhattan distances between blocks and goal
  public int manhattan() {
    int m = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (tiles[i][j] == 0) continue;
        if (tiles[i][j] != idxToValue(i, j)) m += manSteps(tiles[i][j], i, j);
      }
    }
    return m;
  }

  private int manSteps(int tile, int i, int j) {
    int targetRow = tileToRow(tile);
    int targetColumn = tileToColumn(tile);
    return Math.abs(targetRow - i) + Math.abs(targetColumn - j);
  }

  private int tileToRow(int tile) {
    return (tile - 1) / N;
  }

  private int tileToColumn(int tile) {
    return (tile - 1) % N;
  }

  // is this board the goal board?
  public boolean isGoal() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (i == N - 1 && j == N - 1) {
          if (tiles[i][j] != 0) return false;
        } else {
          if (tiles[i][j] != i * N + j + 1) return false;
        }
      }
    }
    return true;
  }

  // a board obtained by exchanging two adjacent blocks in the same row
  public Board twin() {
    int[][] b = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        b[i][j] = tiles[i][j];
      }
    }

    boolean stopOuter = false;
    for (int m = 0; m < N; m++) {
      if (stopOuter) break;
      for (int l = 1; l < N; l++) {
        if (b[m][l - 1] != 0 && b[m][l] != 0) {
          int t = b[m][l - 1];
          b[m][l - 1] = b[m][l];
          b[m][l] = t;
          stopOuter = true;
          break;
        }
      }
    }

    return new Board(b);
  }

  // does this board equal y?
  public boolean equals(Object y) {
    if (y == this) return true;
    if (y == null) return false;
    if (y.getClass() != this.getClass()) return false;

    Board that = (Board) y;
    if (this.tiles.length != that.tiles.length) return false;
    if (this.tiles[0].length != that.tiles[0].length) return false;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (this.tiles[i][j] != that.tiles[i][j]) return false;
      }
    }
    return true;
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    Queue<Board> bds = new Queue<Board>();

    int[] blankPosition = findBlankPosition();
    int blankRow = blankPosition[0];
    int blankColumn = blankPosition[1];
    int[][] tmp;

    if (existLeftTile(blankRow, blankColumn)) {
      tmp = swapTwoElement(blankRow, blankColumn, blankRow, blankColumn - 1);
      bds.enqueue(new Board(tmp));
    }

    if (existRightTile(blankRow, blankColumn)) {
      tmp = swapTwoElement(blankRow, blankColumn, blankRow, blankColumn + 1);
      bds.enqueue(new Board(tmp));
    }

    if (existAboveTile(blankRow, blankColumn)) {
      tmp = swapTwoElement(blankRow, blankColumn, blankRow - 1, blankColumn);
      bds.enqueue(new Board(tmp));
    }

    if (existBelowTile(blankRow, blankColumn)) {
      tmp = swapTwoElement(blankRow, blankColumn, blankRow + 1, blankColumn);
      bds.enqueue(new Board(tmp));
    }

    return bds;
  }

  private int[][] swapTwoElement(int i, int j, int k, int m) {
    int[][] arr = new int[N][N];
    for (int x = 0; x < N; x++) {
      for (int y = 0; y < N; y++) {
        arr[x][y] = tiles[x][y];
      }
    }

    int tmp = arr[i][j];
    arr[i][j] = arr[k][m];
    arr[k][m] = tmp;

    return arr;
  }

  private boolean existLeftTile(int i, int j) {
    return j - 1 >= 0;
  }

  private boolean existRightTile(int i, int j) {
    return j + 1 < N;
  }

  private boolean existAboveTile(int i, int j) {
    return i - 1 >= 0;
  }

  private boolean existBelowTile(int i, int j) {
    return i + 1 < N;
  }

  private int[] findBlankPosition() {
    int[] arr = new int[2];
    boolean found = false;
    for (int i = 0; i < N; i++) {
      if (found) break;
      for (int j = 0; j < N; j++) {
        if (tiles[i][j] == 0) {
          arr[0] = i;
          arr[1] = j;
          found = true;
          break;
        }
      }
    }
    return arr;
  }

  // string representation of the board (in the output format specified below)
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(N + "\n");
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        s.append(String.format("%2d ", tiles[i][j]));
      }
      s.append("\n");
    }
    return s.toString();
  }

  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    StdOut.println(initial);
    // StdOut.println("dimension: " + initial.dimension());
    // StdOut.println("hamming: " + initial.hamming());
    // StdOut.println("manhattan: " + initial.manhattan());
    // StdOut.println("isGoal: " + initial.isGoal());
    // StdOut.println(initial.twin());
    for (Board bd : initial.neighbors()) {
      StdOut.println(bd);
    }

    // int[][] ts = {{1, 3, 4}, {2, 0, 5}, {7, 8, 7}};
    // Board b1 = new Board(ts);
    // StdOut.println(b1);
    // b1.neighbors();

    // StdOut.println("equals: " + initial.equals(b1));


  }

}