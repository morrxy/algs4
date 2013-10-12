/**
 * java Evaluate 
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) 
 * 101.0
 *
 * java Evaluate
 * ( ( 1 + sqrt ( 5.0 ) ) / 2.0 )
 * 1.618033988749895
 */
public class Evaluate {
	public static void main(String[] args) {
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();
		while (!StdIn.isEmpty()) {
			// REad token, push if operator.
			String s = StdIn.readString();
			if (s.equals("("));
			else if (s.equals("+")) ops.push(s);
			else if (s.equals("-")) ops.push(s);
			else if (s.equals("*")) ops.push(s);
			else if (s.equals("/")) ops.push(s);
			else if (s.equals("sqrt")) ops.push(s);
			else if (s.equals(")")) {
				// Pop, evalute, and push result if token is ")"
				String op = ops.pop();
				double v = vals.pop();
				if (op.equals("+")) v = vals.pop() + v;
				else if (op.equals("-")) v = vals.pop() - v;
				else if (op.equals("*")) v = vals.pop() * v;
				else if (op.equals("/")) v = vals.pop() / v;
				else if (op.equals("sqrt")) v = Math.sqrt(v);
				vals.push(v);
			} // Token not operator or paren: push double value.
			else vals.push(Double.parseDouble(s));
		}
		StdOut.println(vals.pop());
	}
}