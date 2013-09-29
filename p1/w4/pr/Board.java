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
    // StdOut.println("i, j, targetRow, targetColumn: " + i + ", " + j + ", " + targetRow + ", " + targetColumn);
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
      for (int j = 0; j < N ; j++) {
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
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (this.tiles[i][j] != that.tiles[i][j]) return false;
      }
    }
    return true;
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
    // StdOut.println("dimension: " + initial.dimension());
    // StdOut.println("hamming: " + initial.hamming());
    // StdOut.println("manhattan: " + initial.manhattan());
    // StdOut.println("isGoal: " + initial.isGoal());
    StdOut.println(initial.twin());

    // int[][] ts = {{0, 1, 3}, {4, 2, 5}, {7, 8, 7}};
    // Board b1 = new Board(ts);

    // StdOut.println("equals: " + initial.equals(b1));


  }

}