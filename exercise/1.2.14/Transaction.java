/** 
 * 1.2.14 
 * Using our implementation of equals() in Date as a model(page103),
 * develop implementations of equals() for Transaction.
 * 
 */

public class Transaction {

	private String who;
	private Date when;
	private double amount;

	Transaction(String who1, Date when1, double amount1) {
		who = who1;
		when = when1;
		amount = amount1;
	}

	public String who() {
		return who;
	}

	public Date when() {
		return when;
	}

	public double amount() {
		return amount;
	}

	public boolean equals(Object x) {
		if (this == x) return true;
		if (x == null) return false;
		if (this.getClass() != x.getClass()) return false;
		Transaction that = (Transaction) x;
		if (!this.who.equals(that.who)) return false;
		if (!this.when.equals(that.when)) return false;
		if (this.amount != that.amount) return false;
		return true;
	}

	public String toString() {
		return who() + " " + when() + " " + amount();
	}

	public static void main(String[] args) {
		Transaction t1 = new Transaction("Tom", new Date(5, 11, 2013), 3000);
		Transaction t2 = new Transaction("Tom", new Date(5, 11, 2013), 3000);
		StdOut.println(t1);
		StdOut.println(t2);
		if (t1.equals(t2)) {
			StdOut.println("t1 equals t2");
		} else {
			StdOut.println("t1 not equals t2");
		}
	}

}