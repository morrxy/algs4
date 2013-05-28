/**
 * 1.3.15 Write a Queue client that takes a command-line argument k
 * and prints the kth from the last string found on standard 
 * input (assuming that standard input has k or more strings).
 *
 * java QueueClient 1
 * 1 2 3 4 5
 * 5
 *
 * java QueueClient 2
 * 1 2 3 4 5
 * 4
 * 
 * java QueueClient 5
 * 1 2 3 4 5
 * 1
 */

public class QueueClient {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		Queue<Integer> q = new Queue<Integer>();

		while (!StdIn.isEmpty()) {
			int item = StdIn.readInt();
			q.enqueue(item);
		}

		int p = q.size() - k + 1;

		for (int i = 0; i < p; i++) {
			int x = q.dequeue();
			if (i == p - 1) {
				StdOut.println(x);
			}
		}


	}
}