public class LifoStackPop {
  public static void main(String[] args) {
    Stack<String> s = new Stack<String>();
    String output = "";
    int x = 0;
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      if (!item.equals("-")) {
        s.push(item);
        x += 1;

        StdOut.println("\noutput:");
        StdOut.println(output);
        StdOut.println("stack: " + s.toString());
        StdOut.println("next input: " + x);
      } else {
        if (!s.isEmpty()) {
          output = output + s.pop() + " ";

          StdOut.println("\noutput:");
          StdOut.println(output);
          StdOut.println("stack: " + s.toString());
          StdOut.println("next input: " + x);
        }
      }
    }
  }
}