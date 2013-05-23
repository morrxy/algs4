/**
 * java StackLinkedList < tobe.txt
 * to be not that or be (2 left on stack)
 * 
 */

import java.util.Iterator;

public class StackLinkedList<Item> implements Iterable<Item> {

	private Node first; // top of stack (most recently added node)
	private int N; // number of items

	private class Node {
		// nested class to define nodes
		Item item;
		Node next;
	}

	public boolean isEmpty() {
		return first == null; // or: N == 0
	}

	public int size() {
		return N;
	}

	public void push(Item item) {
		// Add item to top of stack.
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}

	public Item pop() {
		// remove Item from top of stack.
		Item item = first.item;
		first = first.next;
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

	public static void main(String[] args) {
		// Create a stack and push/pop strings as directed on StdIn
		StackLinkedList<String> s = new StackLinkedList<String>();

		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-"))
				s.push(item);
			else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
		}

		StdOut.println("(" + s.size() + " left on stack)");
	}

}