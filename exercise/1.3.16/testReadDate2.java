/**
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
 * java testReadDate2
 * 5/22/1939 5/4/1990
 * 3/1/2001 
 * ctrl+d (for mac) / ctrl+z (for win)
 * 5/22/1939
 * 5/4/1990
 * 3/1/2001
 * 
 */

public class testReadDate2 {
	
	public static void main(String[] args) {
		String[] d = readDates();
		for (int i = 0; i < d.length; i++) {
			StdOut.println(d[i]);
		}
	}

	public static String[] readDates() {
		Queue<String> q = new Queue<String>();
		
		while (!StdIn.isEmpty()) {
			q.enqueue(StdIn.readString());
		}

		int N = q.size();
		String[] a = new String[N];

		for (int i = 0; i < N; i++) {
			a[i] = q.dequeue();
		}
		
		return a;

	}

}