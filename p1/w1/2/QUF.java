/**
 * 7-4 8-1 6-9 4-9 0-6 2-3 1-3 2-0 3-5
 */

public class QUF
{
  private int[] id;

  public QUF(int N) {
    id = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }


  private int root(int i) {
    while (i != id[i]) i = id[i];
    return i;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  public void union(int p, int q) {
    int i = root(p);
    int j = root(q);
    id[i] = j;
  }

  public void show() {
    for (int i = 0; i < id.length; i++) {
      StdOut.print(id[i] + " ");
    }
  }

  public static void main(String[] args) {
    int N = 10;
    QUF quf = new QUF(N);

    quf.union(7, 4);
    quf.union(8, 1);
    quf.union(6, 9);
    quf.union(4, 9);
    quf.union(0, 6);
    quf.union(2, 3);
    quf.union(1, 3);
    quf.union(2, 0);
    quf.union(3, 5);

    quf.show();
  }



}