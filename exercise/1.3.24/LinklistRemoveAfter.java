/**
 * 1.3.24  Write a method removeAfter() that takes a linked-list 
 * Node as argument and removes the node following the given one 
 * (and does nothing if the argument or the next ﬁeld in the 
 * argument node is null).
 * 
 */

import java.util.Iterator;

public class LinklistRemoveAfter<Item> implements Iterable<Item> {

	private Node first = null;
	private Node last = null;
	private int N = 0;

	public class Node {
		Item item;
		Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public void removeAfter(Node nd) {
		if (nd.next == null || nd == null) return;
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

		Node keynode = new Node();
		keynode.item = args[0];

		q.removeAfter(keynode);

		// StdOut.println("before removeLastNode:");
		// StdOut.println("size:" + q.size());

		// for (String str : q) {
		// 	StdOut.print(str + " ");
		// }
		// StdOut.println("");

		// int k = Integer.parseInt(args[0]);
		// q.delete(k);

		// StdOut.println("after removeLastNode:");
		// StdOut.println("size:" + q.size());

		// for (String str : q) {
		// 	StdOut.print(str + " ");
		// }
		// StdOut.println("");
	}


}