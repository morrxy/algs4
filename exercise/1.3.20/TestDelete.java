/**
 * 1.3.20 Write a method delete() that takes an int 
 * argument k and deletes the kth element in a linked list, if it exists.
 */

import java.util.Iterator;

public class TestDelete<Item> implements Iterable<Item> {

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

	public void delete(int k) {
		if (k > N) {
			StdOut.println("kth item don't exist");
		} else if (N == 0) {
			StdOut.println("the linked list is empty");
		} else if (N == 1) {
			first = null;
			last = null;
			N--;
		} else {
			
		}
	}

	// public void removeLastNode() {
	// 	if (N == 0) {
	// 		return;
	// 	} else if (N == 1) {
	// 		first = null;
	// 		last = null;
	// 	} else {
	// 		Node beforeLast = first;
	// 		while (true) {
	// 			if (beforeLast.next.next == null) break;
	// 			beforeLast = beforeLast.next;
	// 		}
	// 		beforeLast.next = null;
	// 	}
	// }

	public static void main(String[] args) {
		Queue2<String> q = new Queue2<String>();

		while (!StdIn.isEmpty()) {
			q.enqueue(StdIn.readString());
		}

		StdOut.println("before removeLastNode:");
		for (String str : q) {
			StdOut.print(str + " ");
		}

		q.removeLastNode();

		StdOut.println("\nafter removeLastNode:");
		for (String str : q) {
			StdOut.print(str + " ");
		}
	}


}

