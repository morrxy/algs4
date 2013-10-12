/* Array  exercise.  Write  a  code  fragment  that  creates  
 * an N-by-N boolean array a[][] such that a[i][j] is true if 
 * i and j are relatively prime (have no common fac-tors), 
 * and false otherwise
 *
 * > java ex_1_1_30 5
 * true    true    true    true    true    
 * true    false   true    false   true    
 * true    true    false   true    true    
 * true    false   true    false   true    
 * true    true    true    true    false 
*/

public class ex_1_1_30 {

	public static void main(String[] args) {

		int k = Integer.parseInt(args[0]);
		boolean[][] a = new boolean[k][k];

		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				a[i][j] = (relativePrime(i + 1, j + 1)) ? true : false;
				StdOut.printf("%-7s", a[i][j]);
				if (j == k - 1) {
					StdOut.print("\n");
				}
			}
		}
	}

	public static boolean relativePrime(int m, int n) {
		if (gcd(m, n) == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static int gcd(int m, int n) {
		if (n == 0) {
			return m;
		}
		int r = m % n;
		return gcd(n, r);
	}
}