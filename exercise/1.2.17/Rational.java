/**
 * exercise 1.2.17
 * Robust implementation of rational numbers.  
 * Use assertions to develop an implementation of 
 * Rational (see Exercise 1.2.16) that is immune to overﬂow.
 *
 * java Rational -ea 20 40 12 15
 */

public class Rational {

	private final long numerator;
	private final long denominator;

	public Rational(long n, long d) {
		if (d == 0) {
			throw new RuntimeException("can't set denominator to zero");
		} else {
			assert n < Long.MAX_VALUE && n > Long.MIN_VALUE : "numerator overflow";
			assert d < Long.MAX_VALUE && d > Long.MIN_VALUE : "denominator overflow";
			// assert n < 10 && n > 8 : "numerator overﬂow";
			// assert d < 4 && d > 2 : "denominator overﬂow";
			long x = gcd(n, d);
			numerator = n / x;
			denominator = d / x;
		}
	}

	private long gcd(long p, long q) {
		if (q == 0) {
			return p;
		}
		long r = p % q;
		return gcd(q, r);
	}

	public Rational plus(Rational b) {
		long n = this.numerator * b.denominator + this.denominator * b.numerator;
		long d = this.denominator * b.denominator;
		long x = gcd(n, d);
		n = n / x;
		d = d / x;
		return new Rational(n, d);
	}

	public Rational minus(Rational b) {
		long n = this.numerator * b.denominator - this.denominator * b.numerator;
		long d = this.denominator * b.denominator;
		long x = gcd(n, d);
		n = n / x;
		d = d / x;
		return new Rational(n, d);
	}

	public Rational times(Rational b) {
		long n = this.numerator * b.numerator;
		long d = this.denominator * b.denominator;
		long x = gcd(n, d);
		n = n / x;
		d = d / x;
		return new Rational(n, d);
	}

	public Rational divides(Rational b) {
		if (b.numerator == 0) {
			throw new RuntimeException("divides by zero");
		} else {
			long n = this.numerator * b.denominator;
			long d = this.denominator * b.numerator;
			long x = gcd(n, d);
			n = n / x;
			d = d / x;
			return new Rational(n, d);
		}
	}

	public boolean equals(Rational that) {
		return this.numerator * that.denominator == this.denominator * that.numerator;
	}

	public String toString() {
		return numerator + "/" + denominator;
	}

	public static void main(String[] args) {
		long nu1 = Long.parseLong(args[0]);
		long de1 = Long.parseLong(args[1]);
		long nu2 = Long.parseLong(args[2]);
		long de2 = Long.parseLong(args[3]);

		Rational r1 = new Rational(nu1, de1);
		Rational r2 = new Rational(nu2, de2);

		StdOut.println("r1: " + r1);
		StdOut.println("r2: " + r2);
		StdOut.println("r1 + r2: " + r1.plus(r2));
		StdOut.println("r1 - r2: " + r1.minus(r2));
		StdOut.println("r1 * r2: " + r1.times(r2));
		StdOut.println("r1 / r2: " + r1.divides(r2));
		StdOut.println("r1 equals r2?: " + r1.equals(r2));
	}
}