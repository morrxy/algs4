/**
 * 1.2.7  What does the following recursive function return?
 *
 * java Test abcdefghijklmn
 * 
 */
public class Test {
	public static void main(String[] args) {
		String s = args[0];
		StdOut.println(mystery(s));
	}

	public static String mystery(String s) 
	{
		int N = s.length();
		if (N <= 1) return s;
		String a = s.substring(0, N/2);
		String b = s.substring(N/2, N);
		return mystery(b) + mystery(a); 
	}

}
