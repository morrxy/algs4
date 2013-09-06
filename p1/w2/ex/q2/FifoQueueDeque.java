public class FifoQueueDeque {
  public static void main(String[] args) {
    Queue<String> s = new Queue<String>();
    String output = "";
    int x = 0;
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      if (!item.equals("-")) {
        s.enqueue(item);
        x += 1;

        StdOut.println("\noutput:");
        StdOut.println(output);
        StdOut.println("queue: " + s.toString());
        StdOut.println("next input: " + x);
      } else {
        if (!s.isEmpty()) {
          output = output + s.dequeue() + " ";

          StdOut.println("\noutput:");
          StdOut.println(output);
          StdOut.println("queue: " + s.toString());
          StdOut.println("next input: " + x);
        }
      }
    }
  }
}