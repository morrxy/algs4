/**
 * exercise 1.2.6
 * A string s is a circular rotation of a string t if it matches when the characters
 * are circularly shifted by any number of positions; e.g., ACTGACG is a circular shift of
 * TGACGAC, and vice versa. Detecting this condition is important in the study of genomic
 * sequences. Write a program that checks whether two given strings s and t are circular
 * shifts of one another. Hint : The solution is a one-liner with indexOf(), length(), and
 * string concatenation.
 *
 * java CheckCircularRotation ACTGACG TGACGAC
 *
 */

public class CheckCircularRotation {
	public static void main(String[] args) {
		String s = args[0];
		String t = args[1];

		boolean matched = false;

		if (s.length() == t.length()) {
			for (int i = 0; i < s.length(); i++) {
				s =  s.substring(1) + s.substring(0, 1);
				if (s.equals(t)) {
					matched = true;
					break;
				}
			}
		}

		if (matched) {
			StdOut.println("yes");
		} else {
			StdOut.println("no");
		}

	}
}