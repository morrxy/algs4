/*
 * exercise 1.1.35
 * Dice simulation. The following code computes the exact probability 
 * distribution for the sum of two dice:
 *
 * int SIDES = 6; 
 * double[] dist = new double[2*SIDES+1]; 
 * for (int i = 1; i <= SIDES; i++)
 *    for (int j = 1; j <= SIDES; j++)
 *       dist[i+j] += 1.0;
 * for (int k = 2; k <= 2*SIDES; k++)
 *    dist[k] /= 36.0;
 *
 * The value dist[i] is the probability that the dice sum to k. Run experiments to 
 * validate this calculation simulating N dice throws, keeping track of the frequencies of
 * occurrence of each value when you compute the sum of two random integers between 1 
 * and 6. How large does N have to be before your empirical results match the exact results 
 * to three decimal places?
 *
 * java DiceSimulation 9000000
 *
 */

public class DiceSimulation {

	public static void main(String[] args) {

		double[] dist = calculate_dist();

		int experiment_times = Integer.parseInt(args[0]);
		double[] experiment_dist = validate_dist(experiment_times);

		for (int i = 2; i <= 12; i++) {
			StdOut.println("sum " + i);
			StdOut.println("cal: " + dist[i]);
			StdOut.println("exp: " + experiment_dist[i]);
			StdOut.println("");
		}

	}

	public static double[] calculate_dist() {
		int SIDES = 6;
		double[] dist = new double[2*SIDES + 1];
		for (int i = 1; i <= SIDES; i++) {
			for (int j = 1; j <= SIDES; j++) {
				dist[i + j] += 1.0;
			}
		}

		for (int k = 2; k <= 2 * SIDES; k++) {
			dist[k] /= 36.0;
		}

		return dist;
	}

	public static double[] validate_dist(int times) {

		double[] dist = new double[13];

		for (int i = 0; i < times; i++) {
			int dice_a = StdRandom.uniform(1, 7);
			int dice_b = StdRandom.uniform(1, 7);
			dist[dice_a + dice_b] += 1.0;
		}

		for (int k = 2; k <= 12; k++) {
			dist[k] /= times;
		}

		return dist;
	}

}