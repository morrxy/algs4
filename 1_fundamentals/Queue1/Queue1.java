/**
 * java Queue1 < tobe.txt
 * to be or not to be (2 left on queue)
 * that
 * is
 */
import java.util.Iterator;

public class Queue1<Item> implements Iterable<Item> {
	private Node first; // link to least recently added node
	private Node last; // link to most recently added node
	private int N; // number of items on the queue

	private class Node {
		// nested class to define nodes
		Item item;
		Node next;
	}

	public boolean isEmpty() {
		return first == null; // Or: N == 0.
	}

	public int size() {
		return N;
	}

	public void enqueue(Item item) {
		// Add item to the end of the list.
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) first = last;
		else oldlast.next = last;
		N++;
	}

	public Item dequeue() {
		// Remove item from the beginning of the list
		Item item = first.item;
		first = first.next;
		if (isEmpty()) last = null;
		N--;
		return item;
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {}

		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {
		// Create a queue and enqueue/dequeue strings.
		Queue1<String> q = new Queue1<String>();

		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) {
				q.enqueue(item);
			} else if (!q.isEmpty()) {
				StdOut.print(q.dequeue() + " ");
			}
		}

		StdOut.println("(" + q.size() + " left one queue)");

		for (String s : q) {
			StdOut.println(s);
		}
	}

}