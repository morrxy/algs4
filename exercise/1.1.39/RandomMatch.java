/*
 * exercise 1.1.39
 * Random matches.
 * Write a BinarySearch client that takes an int value T as
 * command-line argument and runs T trials of the following experiment
 * for N = 100, 1000, 10000, and 100000: generate two arrays of N randomly
 * generated positive six-digit int values, and find the number of
 * values that appear in both arrays. Print a table giving the average
 * value of this quantity over the T trials for each value of N.
 *
 * java RandomMatch 100
 * T: 100 size: 100 sum: 0
 * T: 100 size: 100 avg: 0
 *
 * T: 100 size: 1000 sum: 105
 * T: 100 size: 1000 avg: 1
 *
 * T: 100 size: 10000 sum: 11252
 * T: 100 size: 10000 avg: 112
 *
 * T: 100 size: 100000 sum: 1053023
 * T: 100 size: 100000 avg: 10530
 */

import java.util.Arrays;

public class RandomMatch {

	public static void main(String[] args) {

		int T = Integer.parseInt(args[0]);
		int[] sizes = {100, 1000, 10000, 100000};

		for (int i = 0; i < sizes.length; i++) {
			int size = sizes[i];

			int total_matched_number = calculate_total_matched_number(T, size);
			int average_matched_number = total_matched_number / T;

			StdOut.println("T: " + T + " size: " + size + " avg: " + average_matched_number + "\n");
		}

	}

	public static int[] make_arr(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = StdRandom.uniform(100000, 1000000);
		}
		return a;
	}

	public static int calculate_total_matched_number(int T, int size) {

		int sum = 0;

		for (int i = 0; i < T; i++) {
			int[] a1 = make_arr(size);
			int[] a2 = make_arr(size);
			int x = calculate_two_array_matched_number(a1, a2);
			sum += x;			
		}

		StdOut.println("T: " + T + " size: " + size + " sum: " + sum);

		return sum;

	}

	public static int calculate_two_array_matched_number(int[] a1, int[] a2) {
		int sum = 0;
		Arrays.sort(a2);

		for (int i = 0; i < a1.length; i++) {
			int key = a1[i];
			if (rank(key, a2) != -1) {
				sum += 1;
			}
		}

		return sum;
	}

	public static int rank(int key, int[] whitelist) {

		if (key < whitelist[0] || key > whitelist[whitelist.length - 1]) {
			return -1;
		}

		int lo = 0;
		int hi = whitelist.length - 1;

		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key < whitelist[mid]) {
				hi = mid - 1;
			} else if (key > whitelist[mid]) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}

		return -1;

	}

}