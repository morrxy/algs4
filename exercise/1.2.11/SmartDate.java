/**
 * exercise 1.2.11
 * Develop an implementation SmartDate of our Date API that raises an exception if the date is not legal.
 *
 * java SmartDate 3 1 2011
 * java SmartDate 3 41 2011
 * java SmartDate 23 1 2011
 */
public class SmartDate {

	private final int month;
	private final int day;
	private final int year;

	public SmartDate(int m, int d, int y) {
		if (m > 12 || m < 1) {
			throw new RuntimeException("Error month.");
		} else {
			month = m;
		}

		if (d > 31 || d < 1) {
			throw new RuntimeException("Error day.");
		} else {
			day = d;
		}

		year = y;
	}

	public int month() {
		return month;
	}

	public int day() {
		return day;
	}

	public int year() {
		return year;
	}

	public String toString() {
		return month() + "/" + day() + "/" + year();
	}

	public static void main(String[] args) {
		int m = Integer.parseInt(args[0]);
		int d = Integer.parseInt(args[1]);
		int y = Integer.parseInt(args[2]);
		SmartDate date = new SmartDate(m, d, y);
		StdOut.println(date);
	}

}