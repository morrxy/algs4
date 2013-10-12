/**
 * 1.3.12 Write an iterable Stack client that has a static
 * method copy() that takes a stack of strings as argument 
 * and returns a copy of the stack. Note: This ability is 
 * a prime example of the value of having an iterator, 
 * because it allows development of such functionality 
 * without changing the basic API.
 *
 * java StackClient
 * a b c d
 * a b c d
 * 
 */

public class StackClient {
	public static void main(String[] args) {
		Stack<String> oldStk = new Stack<String>();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			oldStk.push(s);
		}

		Stack<String> newStk = copy(oldStk);

		StdOut.println(newStk);

	}

	public static Stack<String> copy(Stack<String> oldStack) {
		Stack<String> newStack = new Stack<String>();

		for (String s: oldStack) {
			newStack.push(s);
		}

		return newStack;
	}
}