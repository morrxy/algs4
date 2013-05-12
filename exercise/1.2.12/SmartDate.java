/**
 * exercise 1.2.12
 * Add a method dayOfTheWeek() to SmartDate that returns a String
 * value Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, or
 * Sunday, giving the appropriate day of the week for the date.
 * You may assume that the date is in the 21st century.
 *
 * java SmartDate 5 11 2013
 * 5/11/2013
 * Saturday
 * 
 */

import java.util.Calendar;
import java.util.GregorianCalendar;

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

	public static String dayOfTheWeek(int y, int m, int d) {

	    Calendar cal = Calendar.getInstance();

        /*
        set(int year, int month, int date)
        Jan=0,Feb=1,Mar=2...
        */
        cal.set(y, m - 1, d);

        String[] strDays = new String[]{
        	"Sunday",
        	"Monday",
        	"Tuesday",
        	"Wednesday",
        	"Thusday",
        	"Friday",
        	"Saturday"
        };

	    return strDays[cal.get(Calendar.DAY_OF_WEEK) - 1];
	}

	public static void main(String[] args) {
		int m = Integer.parseInt(args[0]);
		int d = Integer.parseInt(args[1]);
		int y = Integer.parseInt(args[2]);
		SmartDate date = new SmartDate(m, d, y);

		StdOut.println(date);

		StdOut.println(dayOfTheWeek(y, m, d));
	}

}