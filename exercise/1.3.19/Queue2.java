/**
 * 1.3.19   Give a code fragment that removes the last node 
 * in a linked list whose first node is first.
 *
 * java Queue2
 * to be or not
 * ctrl+z / ctrl+d
 * to be or
 * 
 */

import java.util.Iterator;

public class Queue2<Item> implements Iterable<Item> {

	private Node first = null;
	private Node last = null;
	private int N = 0;

	private class Node {
		Item item;
		Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public void enqueue(Item item) {
		
		Node oldlast = last;

		last = new Node();
		last.item = item;
		last.next = null;

		if (isEmpty()) {
			first = last; 
		} else {
			oldlast.next = last;
		}

		N++;
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

	public void removeLastNode() {
		if (N == 0) {
			return;
		} else if (N == 1) {
			first = null;
			last = null;
			N--;
		} else {
			Node beforeLast = first;
			while (true) {
				if (beforeLast.next.next == null) break;
				beforeLast = beforeLast.next;
			}
			beforeLast.next = null;
			N--;
		}
	}

	public static void main(String[] args) {
		Queue2<String> q = new Queue2<String>();

		while (!StdIn.isEmpty()) {
			q.enqueue(StdIn.readString());
		}

		StdOut.println("before removeLastNode:");
		StdOut.println("size:" + q.size());
		for (String str : q) {
			StdOut.print(str + " ");
		}

		q.removeLastNode();

		StdOut.println("\nafter removeLastNode:");
		StdOut.println("size:" + q.size());
		for (String str : q) {
			StdOut.print(str + " ");
		}
	}


}

