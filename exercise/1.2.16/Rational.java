/**
 * exercise 1.2.16 Rational numbers.
 * Implement an immutable data type Rational for rational
 * numbers that supports addition, subtraction, multiplication, and division.
 *
 * public class  Rational
 * Rational(int numerator. int denominator)
 * Rational  plus(Rational b)  sum of this number and b
 * Rational  minus(Rational b)          difference of this number and b
 * Rational  times(Rational b)          product of this number and b
 * Rational  divides(Rational b)        quotient of this number and b
 * boolean  equals(Rational that)      is this number equal to that ?
 * String  toString()  string representation
 *
 * You do not have to worry about testing for overﬂow (see Exercise 1.2.17), but use as
 * instance variables two long values that represent the numerator and denominator to
 * limit the possibility of overﬂow. Use Euclid’s algorithm (see page 4) to ensure that the
 * numerator and denominator never have any common factors. Include a test client that
 * exercises all of your methods.
 *
 * java Rational 1 2 1 5
 * r1: 1/2
 * r2: 1/5
 * r1 + r2: 7/10
 * r1 - r2: 3/10
 * r1 * r2: 1/10
 * r1 / r2: 5/2
 * r1 equals r2?: false
 *
 */

public class Rational {

	private final long numerator;
	private final long denominator;

	public Rational(long n, long d) {
		if (d == 0) {
			throw new RuntimeException("can't set denominator to zero");
		} else {
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