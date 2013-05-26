/**
 * 1.3.4 Write a stack client Parentheses that reads in a text stream from
 * standard input and uses a stack to determine whether its parentheses 
 * are properly balanced. For example, your program should print true 
 * for [()]{}{[()()]()} and false for [(]).
 *
 * java Parentheses
 * [()]{}{[()()]()}
 * true
 *
 * java Parentheses
 * [(])
 * false
 * 
 */

public class Parentheses {

	public static void main(String[] args) {
		Stack<String> p = new Stack<String>();
		boolean result = true;
		while (!StdIn.isEmpty()) {
			char c = StdIn.readChar();
			String s = c + "";
			if (s.equals(")")) {
				if (!p.pop().equals("(")) {
					result = false;
					break;
				}
			} else if (s.equals("]")){
				if (!p.pop().equals("[")) {
					result = false;
					break;
				}
			} else if (s.equals("}")) {
				if (!p.pop().equals("{")) {
					result = false;
					break;
				}
			} else {
				p.push(s);
			}
		}
		StdOut.println(result);
	}

}