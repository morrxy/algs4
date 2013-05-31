/**
 * 1.3.26  Write a method remove() that takes a linked list 
 * and a string key as arguments and removes all of the nodes 
 * in the list that have key as its item ﬁeld.
 *
 * java LinkListRemove to
 * to be or to not
 * 
 */

import java.util.Iterator; 

public class LinkListRemove<Item> implements Iterable<Item> {

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

	public void remove(LinkListRemove<Item> q, Item key) {
		q.removeKey(key);
	}

	private void removeKey(Item key) {

		// Node newFirst = null;
		// Node newLast = null;
		// int K = 0;

		// Node current = first;
		// for (int i = 0; i < N; i++) {
		// 	if (current.item.equals(key)) {
		// 		K += 1;
		// 		if (K == 1) {

		// 		}
		// 	}
		// }

		// if (N == 0) {
		// 	return;
		// } else if (N == 1) {
		// 	if (first.item.equals(key)) {
		// 		first = null;
		// 		last = null;
		// 		N--;
		// 	}
		// } else {

		// }

		// Node before = null;
		// Node current = first;

		// while (current.next != null) {
		// 	StdOut.println(current.item);

		// 	if (current.item.equals(key)) {
		// 		current
		// 	}

		// 	before = current;
		// 	current = current.next;
		// }

		// for (int i = 0; i < N; i++) {
		// 	// StdOut.println(current.item);
		// 	if (current.item.equals(key)) {
		// 		StdOut.println(key);
		// 		before.next = current.next;

		// 		before = current;
		// 		current = current.next;

		// 		N--;
		// 	}
		// 	before = current;
		// 	current = current.next;
		// }
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
			} else { // k is not the last noded
				nodeBeforeK.next = nodeBeforeK.next.next;
				N--;
			}
			
		}
	}


	public static void main(String[] args) {

		LinkListRemove<String> q = new LinkListRemove<String>();

		while (!StdIn.isEmpty()) {
			q.enqueue(StdIn.readString());
		}

		String key = args[0];
		q.remove(q, key);

		for (String str : q) {
			StdOut.print(str + " ");
		}
		StdOut.println("");
	}

}

