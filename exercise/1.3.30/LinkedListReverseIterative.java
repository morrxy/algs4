/**
 * 1.3.30  Write a function that takes the first Node in a linked list 
 * as argument and (destructively) reverses the list, returning the 
 * first Node in the result.
 *
 * Iterative solution
 *
 * java LinkedListReverseIterative
 * to be or not
 * ^z / ^d
 * not or be to
 * 
 */

import java.util.Iterator;

public class LinkedListReverseIterative<Item> implements Iterable<Item> {
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

	public void reverseList() {
		first = reverse_book(first);
	}

	public Node reverse(Node x) {
		Node first = null;
		Node after = null;
		for (; x != null; x = x.next) {
			first = new Node();
			first.item = x.item;
			first.next = after;

			after = first;
		}
		return first;
	}

	public Node reverse_book(Node x) {
		Node first = x;
		Node reverse = null;
		while (first != null) {
			Node second = first.next;
			first.next = reverse;
			reverse = first;
			first = second;
		}
		return reverse;
	}

	public static void main(String[] args) {
		// Create a queue and enqueue/dequeue strings.
		LinkedListReverseIterative<String> q = new LinkedListReverseIterative<String>();

		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			q.enqueue(item);
		}

		q.reverseList();

		// StdOut.println("(" + q.size() + " left on queue)");
		for (String s : q) {
			StdOut.print(s + " ");
		}
		StdOut.println();
	}

}