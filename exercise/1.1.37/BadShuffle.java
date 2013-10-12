/*
 * exercise 1.1.37
 * Bad shuffling.  
 * Suppose that you choose a random integer between 0 and N-i
 * in our shuffling code instead of one between i and N-i. Show 
 * that the resulting order is not equally likely to be one of 
 * the N! possibilities. Run the test of the previous 
 * exercise for this version.
 *
 * java BadShuffle 10000 10
 */

public class BadShuffle {

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
			// int r = i + StdRandom.uniform(N-i);
			int r = StdRandom.uniform(N-i);
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