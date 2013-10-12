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
 * java Date 5 11 2013
 * 
 */

public class Date{
    private final int month;
    private final int day;
    private final int year;

    public Date(int m, int d, int y) {
        month = m;
        day = d;
        year = y;
    }

    public Date(String date) {
        String[] fields = date.split("/");
        month = Integer.parseInt(fields[0]);
        day = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);
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

    public boolean equals(Object x) {
        if (this == x) {
            return true;
        }
        if (x == null) {
            return false;
        }
        if (this.getClass() != x.getClass()) {
            return false;
        }

        Date that = (Date) x;

        return (this.year == that.year) && (this.month == that.month) && (this.day == that.day);
    }

    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int d = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        Date date = new Date(m, d, y);
        StdOut.println(date);
        Date date2 = new Date(args[0] + "/" + args[1] + "/" + args[2]);
        StdOut.println(date2);

    }
}