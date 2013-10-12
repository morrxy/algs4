/**
 * 1.3.10  Write a filter InfixToPostfix that converts an arithmetic expression from infix to postfix.
 * 
 * Windows users: replace <Ctrl-d> with <Ctrl-z> to signify end of file.
 * 
 * java InfixToPostfix
 * ( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )
 * <Ctrl-d>
 * 2 3 4 + 5 6 * * + 
 *
* % java InfixToPostfix | java EvaluatePostfix
 * ( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )
 * <Ctrl-d>
 * 212
 */

public class InfixToPostfix {
	public static void main(String[] args) {
		Stack<String> ops = new Stack<String>();
		Stack<String> expression = new Stack<String>();

		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("(")) {}
			else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
				ops.push(s);
			} else if (s.equals(")")) {
				String op = ops.pop();
				String right = expression.pop();
				String left = expression.pop();

				String newVal = left + " " + right + " " + op;
				expression.push(newVal);
			} else {
				expression.push(s);
			}
		}

		StdOut.println(expression.toString());

	}
}