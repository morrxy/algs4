/**
 * java Bag1 < tobe.txt
 * is - - - that - - be - to not or be to
 */

import java.util.Iterator;

public class Bag1<Item> implements Iterable<Item> {
	private Node first; // first node in list

	private class Node {
		Item item;
		Node next;
	}

	public void add(Item item) {
		// same as push() in Stack
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
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
		Bag1<String> b = new Bag1<String>();

		while (!StdIn.isEmpty()) {	
			String item = StdIn.readString();
			b.add(item);
		}

		for (String s : b) {
			StdOut.print(s + " ");
		}
	}

}