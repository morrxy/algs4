/**
 * 1.3.29 Write a Queue implementation that uses a circular linkedlist,
 * which is the same as a linked list except that no links are null 
 * and the value of last.next is first whenever the list is not empty. 
 * Keep only one Node instance variable (last).
 *
 * java LinkedListCircular < tobe.txt
 * 
 */

import java.util.Iterator;

public class LinkedListCircular<Item> implements Iterable<Item> {

	private Node last;
	private int N;

	private class Node {
		Item item;
		Node next;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void enqueue(Item item) {
		// Add item to the end of list.
		Node oldlast = last;
		last = new Node();
		last.item = item;
		// StdOut.println(last.item);
		if (isEmpty()) {
			last.next = last;
		} else {
			last.next = oldlast.next;
			oldlast.next = last;
		}
		N++;
	}

	public Item dequeue() {
		// Remove item from the beginning of the list
		Item item = last.next.item;
		last.next = last.next.next;

		if (last.next == last) {
			last = null;
		}

		N--;
		return item;
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = (N == 0) ? null : last.next;
		private int k = 0;

		public boolean hasNext() {
			if (current == null) {
				return false;
			} else {
				return k < N;
			}
		}

		public Item next() {
			Item item = current.item;
			current = current.next;
			k += 1;
			return item;
		}

		public void remove() {}

	}

	public static void main(String[] args) {
		LinkedListCircular<String> q = new LinkedListCircular<String>();

		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			q.enqueue(item);
			// if (!item.equals("-")) {
			// 	q.enqueue(item);
			// } else if (!q.isEmpty()) {
			// 	StdOut.print(q.dequeue() + " ");
			// }
		}

		StdOut.println("(" + q.size() + " left on queue)");

		for (String s : q) {
			StdOut.print(s + " ");
		}
		StdOut.println();
	}

}