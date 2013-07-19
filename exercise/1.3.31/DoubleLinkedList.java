/**
 * 1.3.31 Implement a nested class DoubleNode for building doubly-linked 
 * lists, where each node contains a reference to the item preceding it and 
 * the item following it in the  list (null if there is no such item). 
 * Then implement static methods for the following tasks: insert at the 
 * beginning, insert at the end, remove from the beginning, remove from 
 * the end, insert before a given node, insert after a given node, and 
 * remove a given node.
 *
 * java DoubleLinkedList be
 * to be or not
 * ^z / ^d
 * 
 */

import java.util.Iterator;

public class DoubleLinkedList<Item> implements Iterable<Item> {
	private Node first; // link to least recently added node
	private Node last; // link to most recently added node
	private int N; // number of items on the queue

	private class Node {
		// nested class to define nodes
		Item item;
		Node next;
		Node prev;
	}

	public boolean isEmpty() {
		return first == null; // Or: N == 0.
	}

	public int size() {
		return N;
	}

	public void addToLast(Item item) {
		// Add item to the end of the list.
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		last.prev = oldlast;

		if (oldlast != null) {
			oldlast.next = last;
		}

		if (first == null) {
			first = last;
		}

		N++;
	}

	public void addToBegin(Item item) {
		// add item to the beginng of the lis
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		first.prev = null;

		if (oldfirst != null) {
			oldfirst.prev = first;
		}

		if (last == null) {
			last = first;
		}

		N++;
	}

	public Item removeFirst() {
		if (first == null) return null;

		// Remove item from the beginning of the list
		Item item = first.item;
		first = first.next;

		if (first != null) {
			first.prev = null;
		} else {
			last = null;
		}

		N--;

		return item;
	}

	public Item removeLast() {
		if (last == null) return null;

		// Remove last item in the list
		Item item = last.item;
		last = last.prev;

		if (last != null) {
			last.next = null;
		} else {
			first = null;
		}

		N--;

		return item;
	}

	public void insertBefore(Item item, Node n) {
		Node newnode = new Node();
		newnode.item = item;
		newnode.next = n;
		newnode.prev = n.prev;

		if (n.prev == null) {
			addToBegin(item);
		} else {
			n.prev.next = newnode;
			n.prev = newnode;
			N++;
		}
	}

	public void insertBeforeItem(Item it1, Item it2) {
		Node target = findNode(it1);
		if (target == null) return;
		insertBefore(it2, target);
	}

	public void insertAfter(Item item, Node n) {
		Node newnode = new Node();
		newnode.item = item;
		newnode.prev = n;
		newnode.next = n.next;

		if (n.next == null) {
			this.addToLast(item);
		} else {
			n.next.prev = newnode;
			n.next = newnode;
			N++;
		}
	}

	public void insertAfterItem(Item it1, Item it2) {
		Node target = findNode(it1);
		if (target == null) return;
		insertAfter(it2, target);
	}

	public Item removeNode(Node n) {

		if (n.prev != null && n.next == null) {
			n.prev.next = null;
		}

		if (n.prev == null && n.next != null) {
			n.next.prev = null;
		}

		if (n.prev != null && n.next != null) {
			n.prev.next = n.next;
			n.next.prev = n.prev;
		}

		N--;
		return n.item;
	}

	public Node findNode(Item item) {
		Node c = first;
		while(true) {
			if (c == null) return null;
			if (c.item.equals(item)) return c;
			c = c.next;
		}
	}

	public void removeMatchNode(Item item) {
		Node n = findNode(item);
		if (n == null) return;
		removeNode(n);
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

	public static void displayq(DoubleLinkedList<String> q) {
		for (String s : q) {
			StdOut.print(s + " ");
		}
		StdOut.println("\nsize: " + q.size());
	}

	public static void main(String[] args) {
		String key = args[0];

		DoubleLinkedList<String> q = new DoubleLinkedList<String>();

		StdOut.println("input string list:");
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			q.addToLast(item);
		}

		displayq(q);

		StdOut.println("addToBegin:");
		q.addToBegin("addToBegin");
		displayq(q);

		StdOut.println("addToLast:");
		q.addToLast("addToLast");
		displayq(q);

		StdOut.println("removeFirst:");
		q.removeFirst();
		displayq(q);

		StdOut.println("removeLast:");
		q.removeLast();
		displayq(q);

		StdOut.println("insertBefore:" + key);
		q.insertBeforeItem(key, "insertBefore");
		displayq(q);

		StdOut.println("insertAfter:" + key);
		q.insertAfterItem(key, "insertAfter");
		displayq(q);

		StdOut.println("removeNode:" + key);
		q.removeMatchNode(key);
		displayq(q);

	}

}