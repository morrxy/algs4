/**
 * 1.3.11  Write a program EvaluatePostfix that takes a postfix expression from 
 * standard input, evaluates it, and prints the value. (Piping the output of 
 * your program from the previous exercise to this program gives equivalent 
 * behavior to Evaluate.
 *
 * Windows users: replace <Ctrl-d> with <Ctrl-z> to signify end of file.
 *
 * % java EvaluatePostfix
 * 3 4 5 + *
 * <Ctrl-d>
 * 27
 *
 * % java EvaluatePostfix
 * 1 2 3 4 5 * + 6 * * +
 * <Ctrl-d>
 * 277
 *
 * % java EvaluatePostfix
 * 7 16 16 16 * * * 5 16 16 * * 3 16 * 1 + + +
 * <Ctrl-d>
 * 30001
 * 
 * % java EvaluatePostfix
 * 7 16 * 5 + 16 * 3 + 16 * 1 +
 * <Ctrl-d>
 * 30001
 * 
 */

public class EvaluatePostfix {
	public static void main(String[] args) {
		Stack<String> vals = new Stack<String>();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("+")) {
				Double right = Double.parseDouble(vals.pop());
				Double left = Double.parseDouble(vals.pop());
				Double val = left + right;
				vals.push(val + "");
			} else if (s.equals("-")) {
				Double right = Double.parseDouble(vals.pop());
				Double left = Double.parseDouble(vals.pop());
				Double val = left - right;
				vals.push(val + "");
			} else if (s.equals("*")) {
				Double right = Double.parseDouble(vals.pop());
				Double left = Double.parseDouble(vals.pop());
				Double val = left * right;
				vals.push(val + "");
			} else if (s.equals("/")) {
				Double right = Double.parseDouble(vals.pop());
				Double left = Double.parseDouble(vals.pop());
				Double val = left / right;
				vals.push(val + "");
			} else {
				vals.push(s);
			}
		}

		StdOut.println(vals);
	}
}