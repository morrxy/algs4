/*
 * Matrix library. Write a library  Matrix that implements the following API:
 *
 * public class  Matrix 
 * static     double  dot(double[] x, double[] y)  // vector dot product
 * static double[][]  mult(double[][] a, double[][] b)  // matrix-matrix product
 * static double[][]  transpose(double[][] a)  // transpose
 * static   double[]  mult(double[][] a, double[] x)  // matrix-vector product
 * static   double[]  mult(double[] y, double[][] a)  // vector-matrix product
 * 
 * Develop a test client that reads values from standard input and tests all the methods.
 * 
 */

public class ex_1_1_33 {
	public static void main(String[] args) {
		double[][] a1 = {{1, 2, 3}, {4, 5, 6}};
		double[][] a2 = transpose(a1);

		StdOut.println("before transpese, a1:");
		show_arr_2d(a1);
		StdOut.println("after transpese, a2:");
		show_arr_2d(a2);

		double[][] b1 = {{1, 0, 2}, {-1, 3, 1}};
		double[][] b2 = {{3, 1}, {2, 1}, {1, 0}};
		double[][] b3 = mult(b1, b2);

		StdOut.println("b1:");
		show_arr_2d(b1);
		StdOut.println("b2:");
		show_arr_2d(b2);
		StdOut.println("mult(b1, b2):");
		show_arr_2d(b3);

		double[] c1 = {1, 0, 2};
		double[][] c2 = {{3, 1}, {2, 1}, {1, 0}};
		double[] c3 = mult(c1, c2);

		StdOut.println("c1:");
		show_arr_1d(c1);
		StdOut.println("c2:");
		show_arr_2d(c2);
		StdOut.println("mult(c1, c2):");
		show_arr_1d(c3);

		double[][] d1 = {{1, 0, 2}, {-1, 3, 1}};
		double[] d2 = {3, 2, 1};
		double[] d3 = mult(d1, d2);

		StdOut.println("d1:");
		show_arr_2d(d1);
		StdOut.println("d2:");
		show_arr_1d(d2);
		StdOut.println("mult(d1, d2):");
		show_arr_1d(d3);

	}

	public static void show_arr_2d(double[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				StdOut.print(a[i][j] + " ");
			}
			StdOut.print("\n");
		}
	}

	public static void show_arr_1d(double[] a) {
		for (int i = 0; i < a.length; i++) {
			StdOut.print(a[i] + " ");
		}
		StdOut.print("\n");
	}

	public static double[][] transpose(double[][] a) {
		double[][] result = new double[a[0].length][a.length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				result[j][i] = a[i][j];
			}
		}
		return result;
	}

	public static double[][] mult(double[][] a, double[][] b) {
		double[][] result = new double[a.length][b[0].length];
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				result[i][j] = calculate_item(i, j, a, b);
			}
		}
		return result;
	}

	public static double calculate_item(int i, int j, double[][] a, double[][] b) {
		double result = 0;
		int length = a[0].length;
		for (int n = 0; n < length; n++) {
			result += a[i][n] * b[n][j];
		}
		return result;
	}

	public static double[] mult(double[] a, double[][] b) {
		double[] result = new double[b[0].length];
		for (int i = 0; i < result.length; i++) {
			result[i] = calculate_item_2(i, a, b);
		}
		return result;
	}

	public static double calculate_item_2(int i, double[] a, double[][] b) {
		double result = 0;
		int length = a.length;
		for (int n = 0; n < length; n++) {
			result += a[n] * b[n][i];
		}
		return result;
	}

	public static double[] mult(double[][] a, double[] b) {
		double[] result = new double[a.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = calculate_item_3(i, a, b);
		}
		return result;
	}

	public static double calculate_item_3(int i, double[][] a, double[] b) {
		double result = 0;
		int length = a[0].length;
		for (int n = 0; n < length; n++) {
			result += a[i][n] * b[n];
		}
		return result;
	}

}