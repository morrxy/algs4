/**
 * exercise 1.2.10
 * Develop a class VisualCounter that allows both increment and
 * decrement operations. Take two arguments N and max in the
 * constructor, where N specifies the maximum number of operations
 * and max specifies the maximum absolute value for the counter.
 * As a side effect, create a plot showing the value of the
 * counter each time its tally changes.
 *
 * java VisualCounter 100 30
 *
 */

public class VisualCounter {

	private int count = 0;
	/**  */
	private int timesOfExp = 0;
	private int maxCount;

	public VisualCounter(int N, int max) {
		maxCount = max;
		StdDraw.setXscale(0, N);
		StdDraw.setYscale(0, max * 2);
		StdDraw.line(0, max, N, max);
	}

	public void increment() {
		timesOfExp += 1;
		count++;
		if (count > maxCount) {
			count = maxCount;
		}
		addOneCount(count, timesOfExp);
	}

	public void decrement() {
		timesOfExp += 1;
		count--;
		if (count < -maxCount) {
			count = -maxCount;
		}
		addOneCount(count, timesOfExp);
	}

	private void addOneCount(int count, int timesOfExp) {
		double x = (timesOfExp - 1) * 1 + .5;
		double y = maxCount + count / 2.0;
		double halfWidth = .5;
		double halfHeight = Math.abs(count / 2.0);
		StdDraw.rectangle(x, y, halfWidth, halfHeight);
	}

	public int tally() {
		return count;
	}

	public String toString() {
		return count + "";
	}

	public static void main(String[] args) {
		int T = Integer.parseInt(args[0]);
		int max = Integer.parseInt(args[1]);
		VisualCounter vc = new VisualCounter(T, max);

		for (int i = 0; i < T; i++) {
			if (StdRandom.bernoulli()) {
				vc.increment();
			} else {
				vc.decrement();
			}
		}
	}

}