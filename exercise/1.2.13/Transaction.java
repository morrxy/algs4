/** 
 * 1.2.13 Using our implementation of Dateasamodel(page91),
 * develop an implementation of Transaction.
 *
 * java Transaction
 * Tom 
 * 5/11/2013 
 * 3000.0
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

	public static void main(String[] args) {
		Transaction t = new Transaction("Tom", new Date(5, 11, 2013), 3000);
		StdOut.println(t.who());
		StdOut.println(t.when());
		StdOut.println(t.amount());
	}

}