/**
 * java Reverse
 * 1
 * 2
 * 3
 * ^z
 * 3
 * 2
 * 1
 */
public class Reverse {
	public static void main(String[] args) {
		Stack<Integer> stack;
		stack = new Stack<Integer>();
		while (!StdIn.isEmpty()) {
			stack.push(StdIn.readInt());
		}

		for (int i : stack) {
			StdOut.println(i);
		}
	}
}