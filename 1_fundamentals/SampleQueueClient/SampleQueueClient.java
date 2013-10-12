/**
 * java SampleQueueClient int.data
 * 2
 * 5
 * 3
 * 7
 */
public class SampleQueueClient {

	public static int[] readInts2(String name) {
		In in = new In(name);
		Queue<Integer> q = new Queue<Integer>();

		while(!in.isEmpty()) {
			q.enqueue(in.readInt());
		}

		int N = q.size();
		int[] a = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = q.dequeue();
		}

		return a;
	}

	public static void main(String[] args) {
		int[] a = readInts2(args[0]);
		for (int i = 0; i < a.length; i++) {
			StdOut.println(a[i]);
		}
	}

}
