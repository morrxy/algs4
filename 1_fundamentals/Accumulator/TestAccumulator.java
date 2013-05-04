/**
 * typical client
 * java TestAccumulator 1000
 * Mean (1000 values): 0.51829
 */
public class TestAccumulator {
	public static void main(String[] args) {
		int T = Integer.parseInt(args[0]);
		Accumulator a = new Accumulator();
		for (int t = 0; t < T; t++) {
			a.addDataValue(StdRandom.random());
		}
		StdOut.println(a);
	}
}