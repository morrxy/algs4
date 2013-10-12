/**
 * 1.3.27 
 * Write a method max() that takes a reference to the first node 
 * in a linked list as argument and returns the value of the maximum 
 * key in the list. Assume that all keys are positive integers, 
 * and return 0 if the list is empty.
 *
 * java LinkListMax
 * 1 3 5 2 4
 * ctrl+d / ctrl+z
 * max: 5
 */

import java.util.Iterator; 

public class LinkListMax<Item> implements Iterable<Item> {

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

	public Integer findMax() {
		return max(first);
	}

	private Integer max(Node f) {
		if (f == null) {
			return new Integer(0);
		}

		Integer result = (Integer)f.item;
		for (Node current = f.next; current != null; current = current.next) {
			if ((Integer)current.item > result) {
				result = (Integer)current.item;
			}
		}

		return result;
	}

	public static void main(String[] args) {

		LinkListMax<Integer> q = new LinkListMax<Integer>();

		while (!StdIn.isEmpty()) {
			q.enqueue(StdIn.readInt());
		}

		StdOut.println("max: " + q.findMax());

	}

}