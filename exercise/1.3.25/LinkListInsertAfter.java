/**
 * 1.3.25  Write a method insertAfter() that takes two linked-list Node 
 * arguments and inserts the second after the first  on its list 
 * (and does nothing if either argument is null).
 *
 * java LinkListInsertAfter
 * need 2 arguments
 *
 * java LinkListInsertAfter be
 * need 2 arguments
 *
 * java LinkListInsertAfter what kill
 * to be or not
 * ctrl+d / ctrl+z
 * to be or not
 *
 * java LinkListInsertAfter be kill
 * to be or not
 * ctrl+d / ctrl+z
 * to be kill or not
 * 
 * java LinkListInsertAfter not kill
 * to be or not
 * ctrl+d / ctrl+z
 * to be or not kill
 * 
 */

import java.util.Iterator;

public class LinkListInsertAfter<Item> implements Iterable<Item> {

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

	public void insertItemAfter(Item key, Item item) {
		
		Node keyNode = find(key);
		
		Node itemNode = new Node();
		itemNode.item = item;

		insertAfter(keyNode, itemNode);
	}

	private void insertAfter(Node key, Node item) {
		if (key == null) {return;}
		Node oldNext = key.next;
		key.next = item;
		item.next = oldNext;
		N++;
	};

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
		if (args.length != 2) {
			StdOut.println("need 2 arguments");
			return;
		}

		LinkListInsertAfter<String> q = new LinkListInsertAfter<String>();

		while (!StdIn.isEmpty()) {
			q.enqueue(StdIn.readString());
		}

		String key = args[0];
		String item = args[1];
		q.insertItemAfter(key, item);

		for (String str : q) {
			StdOut.print(str + " ");
		}
		StdOut.println("");

	}

}