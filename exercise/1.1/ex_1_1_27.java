public class ex_1_1_27 {
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int k = Integer.parseInt(args[1]);
		double p = Double.parseDouble(args[2]);
		binomial(N, k, p);
	}

	public static double binomial(int N, int k, double p) {
		if ((N == 0) || (k < 0)) return 1.0;
		return (1.0 - p) * binomial(N-1, k) + p * binomial(N-1, k-1);
	}
}