/**
 * more tobe.txt
 * to be or not to - be - - that - - - is
 *
 * java FixedCapacityStackOfStrings < tobe.txt
 * to be not that or be (2 left on stack)
 */


public class FixedCapacityStackOfStrings {

	private String[] a; // stack entries
	private int N;      // size

	public FixedCapacityStackOfStrings(int cap) {
		a = new String[cap];
	}

	public boolean isEmpty() {
		return N == 0;
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
		FixedCapacityStackOfStrings s;
		s = new FixedCapacityStackOfStrings(100);
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-"))
				s.push(item);
			else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
		}

		StdOut.println("(" + s.size() + " left on stack)");
	}

	
}


