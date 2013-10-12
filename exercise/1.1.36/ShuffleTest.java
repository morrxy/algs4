/*
 * exercise 1.1.36
 * Empirical shuffle check.  
 * Run computational experiments to check that our 
 * shufflng code on page 32 works as advertised. Write a program ShuffleTest that takes 
 * command-line arguments M and N, does N shuffles of an array of size M that is
 * initialized with a[i] = i before each shuffle, and prints an M-by-M table such that row i
 * gives the number of times i wound up in position j for all j. All entries in the array 
 * should be close to N/M.
 *
 * java ShuffleTest 10000 10
 */

public class ShuffleTest {

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int M = Integer.parseInt(args[1]);

		int[] arr = new int[M];
		for (int i = 0; i < M; i++) {
			arr[i] = i;
		}

		int[][] position_dist = new int[M][M];

		StdRandom.setSeed(123456);
		for (int i = 0; i < N; i++) {
			shuffle(arr);
			calculate_position_dist(arr, position_dist);
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				StdOut.printf("%-5d", position_dist[i][j]);
			}
			StdOut.print("\n");
		}
	}

	public static void shuffle(int[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int r = i + StdRandom.uniform(N-i);
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	public static void calculate_position_dist(int[] a, int[][] d) {
		for (int i = 0; i < a.length; i++) {
			d[a[i]][i] += 1;
		}
	}

}