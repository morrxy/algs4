/** 
 * 1.3.14 Develop a class ResizingArrayQueueOfStrings that implements
 * the queue abstraction with a ﬁxedsize array, and then extend your
 * implementation to use array resizing to remove the size restriction.
 *
 * java ResizingArrayQueueOfStrings < tobe.txt
 * to be or not to be (2 left on queue)
 * 
 */

public class ResizingArrayQueueOfStrings {
	private String[] a;
	private int N;
	private int head;
	private int tail;

	public ResizingArrayQueueOfStrings() {
		a = new String[1];
	}

	private void resize(int max) {
		String[] a2 = new String[max];
		for (int i = 0; i < N; i++) {
			a2[i] = a[i + head];
		}
		a = a2;
		head = 0;
		tail = N;
	}

	public void enqueue(String s) {
		if (a.length == N) {
			resize(a.length * 2);
		}

		a[tail] = s;
		tail += 1;
		N += 1;

		// StdOut.println(a.length);
	}

	public String dequeue() {
		// StdOut.println("head " + head);
		String s = a[head];
		if (!this.isEmpty()) {
			a[head] = null;
			head += 1;
			N -= 1;
		}

		if (N <= a.length / 4) {
			resize(a.length / 2);
		}

		// StdOut.println(a.length);

		return s;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public static void main(String[] args) {
		ResizingArrayQueueOfStrings q = new ResizingArrayQueueOfStrings();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) q.enqueue(item);
			else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
		}
		StdOut.println("(" + q.size() + " left on queue)");
	}
}