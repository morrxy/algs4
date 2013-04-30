/* 
 * Filtering. Which of the following require saving all 
 * the values from standard input (in an array, say), 
 * and which could be implemented as a filter using 
 * only a fixed number of variables and arrays of fixed 
 * size (not dependent on N)? For each, the input comes 
 * from standard input and consists of N real numbers between 0 and 1.
 * ■ Print the maximum and minimum numbers.
 * ■ Print the median of the numbers.
 * ■ Print the k th smallest value, for k less than 100.
 * ■ Print the sum of the squares of the numbers.
 * ■ Print the average of the N numbers.
 * ■ Print the percentage of numbers greater than the average.
 * ■ Print the N numbers in increasing order.
 * ■ Print the N numbers in random order.
 * 
 */

public class ex_1_1_34 {

	public static void main(String[] args) {

		String file = args[0];
		
		print_max_min(file);
		
		print_sum_of_square(file);
		
	}

	public static void print_max_min(String file) {

		In in = new In(file);

		double n = 0;
		double x = in.readDouble();
		double min = x;
		double max = x;

		while (!in.isEmpty()) {
			x = in.readDouble();
			if (x < min) min = x;
			if (x > max) max = x;
			n += 1;
		}

		in.close();
		StdOut.println("min: " + min);
		StdOut.println("max: " + max);

	}

	public static void print_sum_of_square(String file) {

		In in = new In(file);

		double sum = 0;
		double x;

		while(!in.isEmpty()) {
			x = in.readDouble();
			sum += x * x;
		}

		in.close();
		StdOut.println("sum of squares: " + sum);

	}

}