/**
 * 1.3.24  Write a method removeAfter() that takes a linked-list 
 * Node as argument and removes the node following the given one 
 * (and does nothing if the argument or the next field in the 
 * argument node is null).
 *
 * java LinklistRemoveAfter or
 * to be or not
 * ctrl+z / ctrl+d
 * to be not
 * 
 */

import java.util.Iterator;

public class LinklistRemoveAfter<Item> implements Iterable<Item> {

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

	public void deleteAfter(Item key) {
		Node keynode = find(key);
		removeAfter(keynode);
	}

	private Node find(Item key) {
		Node current = first;
		Node result = null;
		for (int i = 0; i < N; i++) {
			if (current.item.equals(key)) {
				result = current;
				break;
			} else {
				current = current.next;
			}
		}
		return result;
	}

	private void removeAfter(Node nd) {
		if (nd == null || nd.next == null) return;
		// the node after nd is last node
		if (nd.next.next == null) {
			nd.next = null;
			N--;
		} else { // the node after nd is not last node
			nd.next = nd.next.next;
			N--;
		}
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

	public static void main(String[] args) {
		LinklistRemoveAfter<String> q = new LinklistRemoveAfter<String>();

		while (!StdIn.isEmpty()) {
			q.enqueue(StdIn.readString());
		}

		String key = args[0];

		q.deleteAfter(key);

		for (String str : q) {
			StdOut.print(str + " ");
		}
		StdOut.println("");

	}


}