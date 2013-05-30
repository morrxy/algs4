/**
 * 1.3.20 Write a method delete() that takes an int 
 * argument k and deletes the kth element in a linked list, if it exists.
 *
 * java TestDelete 1
 * ctrl+z / ctrl+d
 * before removeLastNode:
 * size:0 
 * the 1th item don't exist
 * after removeLastNode:
 * size:0
 *
 * java TestDelete 2
 * to
 * ctrl+z / ctrl+d
 * before removeLastNode:
 * size:1
 * to
 * the 2th item don't exist
 * after removeLastNode:
 * size:1
 * to
 * 
 * java TestDelete 2
 * to be
 * ctrl+z / ctrl+d
 * before removeLastNode:
 * size:2
 * to be
 * after removeLastNode:
 * size:1
 * to
 * 
 * 
 * java TestDelete 2
 * to be or
 * ctrl+z / ctrl+d
 * before removeLastNode:
 * size:3
 * to be or
 * after removeLastNode:
 * size:2
 * to or
 * 
 * 
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
		if (k > N || N == 0) {
			StdOut.println("the " + k + "th item don't exist");
		} else if (N == 1) {
			first = null;
			last = null;
			N--;
		} else {
			// find the node before the kth node
			Node nodeBeforeK = first;
			for (int i = 1; i <= k - 2; i++) {
				nodeBeforeK = nodeBeforeK.next;
			}

			// remove the kth node
			if (nodeBeforeK.next.next == null) { // k is the last node
				nodeBeforeK.next = null;
				N--;
			} else { // k is not the last node
				nodeBeforeK.next = nodeBeforeK.next.next;
				N--;
			}
			
		}
	}

	public static void main(String[] args) {
		TestDelete<String> q = new TestDelete<String>();

		while (!StdIn.isEmpty()) {
			q.enqueue(StdIn.readString());
		}

		StdOut.println("before removeLastNode:");
		StdOut.println("size:" + q.size());

		for (String str : q) {
			StdOut.print(str + " ");
		}
		StdOut.println("");

		int k = Integer.parseInt(args[0]);
		q.delete(k);

		StdOut.println("after removeLastNode:");
		StdOut.println("size:" + q.size());

		for (String str : q) {
			StdOut.print(str + " ");
		}
		StdOut.println("");
	}


}

