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
    StdOut.println("i, j, targetRow, targetColumn: " + i + ", " + j + ", " + targetRow + ", " + targetColumn);
    return Math.abs(targetRow - i) + Math.abs(targetColumn - j);
  }

  private int tileToRow(int tile) {
    return tile / N;
  }

  private int tileToColumn(int tile) {
    return (tile - 1) % N;
  }

  // is this board the goal board?
  public boolean isGoal() {
    return false;
  }

  // a board obtained by exchanging two adjacent blocks in the same row
  // public Board twin() {}

  // does this board equal y?
  public boolean equals(Object y) {
    return false;
  }

  // all neighboring boards
  // public Iterable<Board> neighbors() {}

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
    StdOut.println("dimension: " + initial.dimension());
    StdOut.println("hamming: " + initial.hamming());
    StdOut.println("manhattan: " + initial.manhattan());

  }

}