public class Solver {

  // private SearchNode current;
  private MinPQ<SearchNode> pq;
  private MinPQ<SearchNode> pqTwin;
  private int TRY_TIMES = 10;

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

    boolean notSolve = true;

    while (true) {
      // try tryTimes for pq
      // if solve, notSolve = true
      if (try(pq)) break;


      // try tryTimes for pqTwin
      // if solve, notSolve = true

      // break;
    }

    for (Board bd: nd.board.neighbors()) {
      if (nd.previous != null) {
        if (bd.equals(nd.previous.board)) continue;
      }
      SearchNode tmp = new SearchNode(bd, nd.moves + 1, nd);
      pq.insert(tmp);
    }


    for (SearchNode sn : pq) {
      StdOut.println("priority: " + (sn.moves + sn.board.manhattan()));
      StdOut.println("moves:" + sn.moves);
      StdOut.println("manhattan:" + sn.board.manhattan());
      StdOut.println(sn.board);
    }

    // current = new SearchNode();
    // current.board = initial;
    // current.moves = 0;
    // current.previous = null;


  //   while (true) {
  //     min = pq.delMin();
  //     for (Board bd : min.neighbors()) {
  //       if (!bd.equals(min.previous.board)) {
  //         SearchNode nd = new SearchNode();
  //         nd.board = bd;
  //         nd.moves = min.moves + 1;
  //         nd.previous = min;
  //         pq.insert(nd);
  //       }
  //     }
  //     if (min.board.isGoal()) {
  //       break;
  //     }
  //   }
  }

  // is the initial board solvable?
  public boolean isSolvable() {
    return false;
  }

  // min number of moves to solve initial board; -1 if no solution
  public int moves() {
    return 0;
  }

  // sequence of boards in a shortest solution; null if no solution
  public Iterable<Board> solution() {
    Queue<Board> bds = new Queue<Board>();
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
    // Board initialTwin = initial.twin();

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      // for (Board board : solver.solution())
      //   StdOut.println(board);
    }
  }

}