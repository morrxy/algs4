/**
 * 1.3.1 Add a method isFull()to FixedCapacityStackOfStrings.
 *
 * java FixedCapacityStackOfStrings1 < tobe.txt
 * to be not that or be (2 left on stack)
 * 
 */
public class FixedCapacityStackOfStrings1 {

	private String[] a; // stack entries
	private int N; // size

	public FixedCapacityStackOfStrings1(int cap) {
		a = new String[cap];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public boolean isFull() {
		return N == a.length;
	}

	public int size() {
		return N;
	}

	public void push(String item) {
		a[N] = item;
		N += 1;
	}

	public String pop() {
		N -= 1;
		return a[N];
	}

	public static void main(String[] args) {
		FixedCapacityStackOfStrings1 s = new FixedCapacityStackOfStrings1(100);

		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) {
				if (!s.isFull()) {
					s.push(item);
				}
			} else if (!s.isEmpty()) {
				StdOut.print(s.pop() + " ");
			}
		}

		StdOut.println("(" + s.size() + " left on stack)");
	}

}