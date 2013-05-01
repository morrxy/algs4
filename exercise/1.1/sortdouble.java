import java.util.Arrays;

public class sortdouble {
	public static void main(String[] args) {

		In in = new In(args[0]);
		double[] arr = in.readAllDoubles();
		in.close();

		Arrays.sort(arr);
		Out out = new Out(args[1]);
		for (int i = 0; i < arr.length; i++) {
			out.println(arr[i]);
		}
		out.close();
	}
}