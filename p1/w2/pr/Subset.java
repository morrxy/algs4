/**
 * Write a client program Subset.java that takes a command-line integer k,
 * reads in a sequence of N strings from standard input using
 * StdIn.readString(), and prints out exactly k of them, uniformly at random.
 * Each item from the sequence can be printed out at most once.
 * You may assume that k >= 0 and no greater than the number of string
 * on standard input.
 *
 * Your client should use only constant space plus one object either of
 * type Deque or of type RandomizedQueue; use generics properly to avoid
 * casting and compiler warnings. It should also use time and space
 * proportional to at most N in the worst case, where N is the number
 * of strings on standard input. (For an extra challenge, use space
 * proportional to k.)
 *
 * % echo A B C D E F G H I | java Subset 3
 *
 */

public class Subset {
   public static void main(String[] args) {
    int k = Integer.parseInt(args[0]);
    RandomizedQueue<String> r = new RandomizedQueue<String>();

    while (!StdIn.isEmpty()) {
      String str = StdIn.readString();
      r.enqueue(str);
    }

    for (int i = 0; i < k; i++) {
      StdOut.println(r.dequeue());
    }
   }
}