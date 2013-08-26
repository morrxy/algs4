public class WQUF
{
  private int[] id;
  private int[] sz;
  private int count;

  public WQUF(int N)
  {
    count = N;
    id = new int[N];
    for (int i = 0; i < N; i++)
      id[i] = i;
    sz = new int[N];
    for (int i = 0; i < N; i++) {
      sz[i] = 1;
    }
  }

  public int count() {
    return count;
  }


  public boolean connected(int p, int q)
  {
    return id[p] == id[q];
  }

  private int find(int p) {
    while (p != id[p]) p = id[p];
    return p;
  }

  public void union(int p, int q)
  {
    int i = find(p);
    int j = find(q);
    if (i == j) return;

    if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
    else {id[j] = i; sz[i] += sz[j];}
    count--;
  }

  public void show()
  {
    for (int i = 0; i < id.length; i++) {
      StdOut.print(id[i] + " ");
    }
  }

  public static void main(String[] args) {
    int N = 10;
    WQUF uf = new WQUF(N);

    uf.union(8, 4);
    uf.union(5, 0);
    uf.union(6, 3);
    uf.union(8, 6);
    uf.union(0, 7);
    uf.union(2, 5);
    uf.union(0, 9);
    uf.union(1, 6);
    uf.union(2, 4);

    uf.show();

  }

}