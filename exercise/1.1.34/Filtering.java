/* 
 * Filtering. Which of the following require saving all 
 * the values from standard input (in an array, say), 
 * and which could be implemented as a filter using 
 * only a fixed number of variables and arrays of fixed 
 * size (not dependent on N)? For each, the input comes 
 * from standard input and consists of N real numbers between 0 and 1.
 * Print the maximum and minimum numbers.
 * Print the median of the numbers.
 * Print the kth smallest value, for k less than 100.
 * Print the sum of the squares of the numbers.
 * Print the average of the N numbers.
 * Print the percentage of numbers greater than the average.
 * Print the N numbers in increasing order.
 * Print the N numbers in random order.
 *
 * java Filtering doubles.data
 * 
 */

import java.util.Arrays;

public class Filtering {

	public static void main(String[] args) {

		// use a file doubles.data(containing 150 doubles) to simulate standard input
		String file = args[0];

		// Print the maximum and minimum numbers.
		print_max_min(file);
		
		// Print the sum of the squares of the numbers.
		print_sum_of_square(file);

		// Print the average of the N numbers.
		print_average(file);

		// Print the kth smallest value, for k less than 100.
		// use doubles_sorted.txt to test result
		print_kth_smallest(file, StdRandom.uniform(101));

		// all below need to save all item
		// Print the median of the numbers.
		print_median(file);

		// Print the percentage of numbers greater than the average.
		print_the_percentage(file);

		// Print the N numbers in increasing order.
		print_in_increasing_order(file);

		// Print the N numbers in random order.
		print_in_random_order(file);
		

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

		while(!in.isEmpty()) {
			double x = in.readDouble();
			sum += x * x;
		}

		in.close();
		StdOut.println("sum of squares: " + sum);

	}

	public static void print_average(String file) {

		In in = new In(file);

		double sum = 0;
		int N = 0;

		while(!in.isEmpty()) {
			double x = in.readDouble();
			sum += x;
			N += 1;
		}

		in.close();
		StdOut.println("average: " + sum / N);

	}

	public static void print_kth_smallest(String file, int k) {

		In in = new In(file);

		double[] result = new double[k];

		for (int i = 0; i < k; i++) {
			result[i] = in.readDouble();
		}
		Arrays.sort(result);

		while(!in.isEmpty()) {
			double x = in.readDouble();
			result = make_new_array(result, x);
		}

		in.close();

		StdOut.println(k + "th smallest: " + result[k - 1]);

	}

	public static double[] make_new_array(double[] arr, double x) {

		int len = arr.length;
		
		double[] arr1 = new double[len + 1];
		for (int i = 0; i < len; i++) {
			arr1[i] = arr[i];
		}
		arr1[len] = x;
		Arrays.sort(arr1);

		double[] arr2 = new double[len];
		for (int i = 0; i < len; i++) {
			arr2[i] = arr1[i];
		}

		return arr2;
	}

	// Print the median of the numbers.
	public static void print_median(String file) {

		In in = new In(file);
		double[] arr = in.readAllDoubles();
		in.close();
		Arrays.sort(arr);

		int median_index_base_zero = arr.length / 2;
		int median_index_base_one = median_index_base_zero + 1;
		if (arr.length % 2 == 1) {
			StdOut.println("median at: " + median_index_base_one + " value: " + arr[median_index_base_zero]);
		} else {
			StdOut.println("median at: " + (median_index_base_one - 1) + " value: " + arr[median_index_base_zero - 1]);
			StdOut.println("median at: " + median_index_base_one + " value: " + arr[median_index_base_zero]);
		}

	}

	public static void print_the_percentage(String file) {

		In in = new In(file);
		double[] arr = in.readAllDoubles();
		in.close();
		Arrays.sort(arr);

		int N = arr.length;
		double sum = 0;

		for (int i = 0; i < N; i++) {
			sum += arr[i];
		}

		double avg = sum / N;

		int c = 0;

		for (int i = 0; i < N; i++) {
			if (arr[i] > avg) {
				c += 1;
			}
		}

		StdOut.println("the percentage of numbers greater than the average: " + (double)c * 100 / N + "%");

	}

	public static void print_in_increasing_order(String file) {

		In in = new In(file);
		double[] arr = in.readAllDoubles();
		in.close();
		Arrays.sort(arr);

		for (int i = 0; i < arr.length; i++) {
			if (i == arr.length - 1) {
				StdOut.print(arr[i] + "\n");
			} else {
				StdOut.print(arr[i] + ", ");
			}
		}

	}

	public static void print_in_random_order(String file) {

		In in = new In(file);
		double[] arr = in.readAllDoubles();
		in.close();

		StdRandom.setSeed(123456789);
		StdRandom.shuffle(arr);

		for (int i = 0; i < arr.length; i++) {
			StdOut.println((i + 1) + "th: " + arr[i]);
		}

	}


}