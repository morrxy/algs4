/**
 * 1.3.9  Write a program that takes from standard input an expression without left 
 * parentheses and prints the equivalent inﬁx expression with the parentheses 
 * inserted. For example, given the input:
 * 
 * 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) ) 
 * 
 * your program should print
 * 
 * ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
 *
 * java LeftParentheses
 * 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
 * ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
 *
 * java LeftParentheses
 * 1 + 2 + 3 ) * 4 * 5 ) ) )
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
 * 
 */

public class LeftParentheses {
	public static void main(String[] args) {
		Stack<String> ops = new Stack<String>();
		Stack<String> expression = new Stack<String>();

		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
				ops.push(s);
			} else if (s.equals(")")) {
				String op = ops.pop();
				String right = expression.pop();
				String left = expression.pop();

				String newVal = "( " + left + " " + op + " " + right + " )";
				expression.push(newVal);
			} else {
				expression.push(s);
			}
		}

		String result = "";
		for (String str : expression) {
			result = result + str;
		}

		StdOut.println(result);


	}
}