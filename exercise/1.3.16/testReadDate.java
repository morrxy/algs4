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
 * java testReadDate
 * 5/22/1939
 * ctrl+d (for mac) / ctrl+z (for win)
 * 5
 * 22
 * 1939
 * 
 */

public class testReadDate {
	
	public static void main(String[] args) {
		int[] d = readDates();
		for (int i = 0; i < d.length; i++) {
			StdOut.println(d[i]);
		}
	}

	public static int[] readDates() {
		Queue<Character> q = new Queue<Character>();
		
		while (!StdIn.isEmpty()) {
			q.enqueue(StdIn.readChar());
		}

		int N = q.size();
		int[] a = new int[3];
		String temp = "";
		int k = 0;

		for (int i = 0; i < N; i++) {
			char c = q.dequeue();
			if (c == '/') {
				a[k] = Integer.parseInt(temp);
				k += 1;
				temp = "";
			} else {
				temp += c;
			}
		}
		a[k] = Integer.parseInt(temp);
		
		return a;

	}

}