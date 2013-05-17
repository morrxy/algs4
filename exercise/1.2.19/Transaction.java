/**
 * 1.2.19     Parsing.  
 * Develop the parse constructors for your Date and Transaction implementations  
 * of  Exercise  1.2.13  that  take  a  single  String  argument  to  specify  the 
 * initialization values, using the formats given in the table below.
 * Partial solution:
 * public Date(String date) 
 * {
 *    String[] fields = date.split("/");
 *    month = Integer.parseInt(fields[0]);
 *    day   = Integer.parseInt(fields[1]);
 *    year  = Integer.parseInt(fields[2]); 
 * }
 *
 * type        format                         example
 * Date        integers separated by slashes  5/22/1939
 * Transaction customer, date, and amount, separated by whitespace  Turing 5/22/1939 11.99
 * Formats for parsing
 *
 * java Transaction
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

    Transaction(String transaction) {
        String[] fields = transaction.split(" ");
        who = fields[0];
        when = new Date(fields[1]);
        amount = Double.parseDouble(fields[2]);
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

        Transaction t3 = new Transaction("Turing 5/22/1939 11.99");
        StdOut.println(t3);
    }

}