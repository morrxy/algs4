/**
 * 1.3.17   Do Exercise 1.3.16 for Transaction.
 * 1.3.16   Using readInts() on page 126 as a model, write a static 
 * method readDates() for Date that reads dates from standard input 
 * in the format specified in the table on page 119 and returns an 
 * array containing them. 
 *
 * 	public static int[] readInts(String name) {
 *		In in = new In(name);
 *		Queue<Integer> q = new Queue<Integer>();
 *		while (!in.isEmpty()) {
 *			q.enqueue(in.readInt());
 *		}
 *		int N = q.size();
 *		int[] a = new int[N];
 *		for (int i = 0; i < N; i++) {
 *			a[i] = q.dequeue();
 *		}
 *		return a;
 *	}
 *
 * Date  
 * integers separated by slashes  
 * 5/22/1939
 *
 * Transaction 
 * customer, date, and amount, separated by whitespace
 * Turing 5/22/1939 11.99
 *
 * java testTransaction
 * Turing 5/2/1939 11.99
 * Turing2 5/12/1939 21.99
 * Turing3 5/22/1939 31.99
 * ctrl+d (for mac) / ctrl+z (for win)
 * Turing 5/2/1939 11.99
 * Turing2 5/12/1939 21.99
 * Turing3 5/22/1939 31.99
 * 
 */
public class testTransaction {

	public static void main(String[] args) {
		String[] d = readTransaction();
		for (int i = 0; i < d.length; i++) {
			StdOut.println(d[i]);
		}
	}

	public static String[] readTransaction() {
		Queue<String> q = new Queue<String>();
		
		while (!StdIn.isEmpty()) {
			q.enqueue(StdIn.readString());
		}

		int N = q.size();
		String[] a = new String[N / 3];
		String temp = "";

		int k = 0;
		for (int i = 0; i < N; i++) {
			temp += q.dequeue() + " ";
			if (i % 3 == 2) {
				a[k] = temp;
				k += 1;
				temp = "";
			}
		}
		
		return a;

	}

}