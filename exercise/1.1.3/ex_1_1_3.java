/*
 * exercise 1.1.3
 * Write a program that takes three integer command-line
 * arguments and prints equal if all three are equal, and
 * not equal otherwise.
 */

public class ex_1_1_3 {
  public static void main(String[] args) {
    if (args[0].equals(args[1]) && args[0].equals(args[2])) {
      StdOut.println("equal");
    } else {
      StdOut.println("not equal");
    }
  }
}