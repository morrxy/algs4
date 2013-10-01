public class Solver {

  // private SearchNode current;
  private MinPQ<SearchNode> pq;
  private MinPQ<SearchNode> pqTwin;
  private int TRY_TIMES = 10;
  private boolean solvable;
  private SearchNode solvedNode;

  private class SearchNode implements Comparable<SearchNode> {
    private Board board;
    private int moves;
    private SearchNode previous;

    public SearchNode(Board bd, int mv, SearchNode prev) {
      this.board = bd;
      this.moves = mv;
      this.previous = prev;
    }

    public int compareTo(SearchNode that) {
      int p0 = this.board.manhattan() + this.moves;
      int p1 = that.board.manhattan() + that.moves;
      if (p0 < p1) return -1;
      if (p0 > p1) return +1;
      return 0;
    }
  }

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    SearchNode nd = new SearchNode(initial, 0, null);
    pq = new MinPQ<SearchNode>();
    pq.insert(nd);

    SearchNode ndTwin = new SearchNode(initial.twin(), 0, null);
    pqTwin = new MinPQ<SearchNode>();
    pqTwin.insert(ndTwin);

    while (true) {
      // try solve for pq
      if (tryPQ(pq)) {
        solvable = true;
        break;
      }

      // try solve for pqTwin
      if (tryPQ(pqTwin)) {
        solvable = false;
        break;
      }
    }

  }

  private boolean tryPQ(MinPQ<SearchNode> testPQ) {

    for (int i = 0; i < TRY_TIMES; i++) {
      SearchNode nd = testPQ.delMin();

      // insert all neribours of nd to testPQ if not equals to previous board
      for (Board bd: nd.board.neighbors()) {
        if (nd.previous != null) {
          if (bd.equals(nd.previous.board)) continue;
        }
        SearchNode tmp = new SearchNode(bd, nd.moves + 1, nd);
        testPQ.insert(tmp);
      }

      if (nd.board.isGoal()) {
        // StdOut.println(nd.board);
        solvedNode = nd;
        return true;
      }
    }

    return false;
  }

  // is the initial board solvable?
  public boolean isSolvable() {
    return solvable;
  }

  // min number of moves to solve initial board; -1 if no solution
  public int moves() {
    if (isSolvable()) return solvedNode.moves;
    return -1;
  }

  // sequence of boards in a shortest solution; null if no solution
  public Iterable<Board> solution() {
    if (!isSolvable()) return null;

    Stack<Board> bds = new Stack<Board>();
    SearchNode node = solvedNode;

    while (true) {
      bds.push(node.board);
      if (node.previous == null) break;
      node = node.previous;
    }

    return bds;
  }

  // solve a slider puzzle
  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        blocks[i][j] = in.readInt();

    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable()) {
      StdOut.println("No solution possible");
      StdOut.println(solver.solution());
    }
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }

}