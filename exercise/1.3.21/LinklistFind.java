/**
 * 1.3.21  Write a method find() that takes a linked list
 * and a string key as arguments and returns true if some 
 * node in the list has key as its item ﬁeld, false otherwise.
 *
 * java LinklistFind be
 * to be or
 * ctrl+z / ctrl+d
 * true
 * 
 * java LinklistFind live
 * to be or
 * ctrl+z / ctrl+d
 * false
 * 
 */

import java.util.Iterator;

public class LinklistFind<Item> implements Iterable<Item> {

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

	public static boolean find(LinklistFind<String> list, String key) {

		boolean contain = false;

		for (String s : list) {
			if (s.equals(key)) {
				contain = true;
				break;
			}
		}
		return contain;
	}

	public static void main(String[] args) {
		LinklistFind<String> q = new LinklistFind<String>();

		while (!StdIn.isEmpty()) {
			q.enqueue(StdIn.readString());
		}

		String key = args[0];

		StdOut.println(find(q, key));
	}

}

