public class ex_1_1_26 {
	public static void main(String[] args) {
		int a = Integer.parseInt(args[0]),
			b = Integer.parseInt(args[1]),
			c = Integer.parseInt(args[2]),
			t;

		if (a > b) { t = a; a = b; b = t;}
		if (a > c) { t = a; a = c; c = t;}
		if (b > c) { t = b; b = c; c = t;}

		StdOut.println(a + " " + b + " " + c);
	}
}