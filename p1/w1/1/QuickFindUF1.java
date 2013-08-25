public class QuickFindUF1
{
  private int[] id;

  public QuickFindUF1(int N)
  {
    id = new int[N];
    for (int i = 0; i < N; i++)
      id[i] = i;
  }

  public boolean connected(int p, int q)
  {
    return id[p] == id[q];
  }

  public void union(int p, int q)
  {
    int pid = id[p];
    int qid = id[q];
    for (int i = 0; i < id.length; i++) {
      if (id[i] == pid) id[i] = qid;
    }
  }

  public void show()
  {
    for (int i = 0; i < id.length; i++) {
      StdOut.print(id[i] + " ");
    }
  }

  public static void main(String[] args) {
    int N = 10c;
    QuickFindUF1 uf = new QuickFindUF1(N);

    uf.union(2, 7);
    uf.union(3, 8);
    uf.union(2, 0);
    uf.union(4, 5);
    uf.union(9, 6);
    uf.union(7, 5);

    uf.show();

  }

}