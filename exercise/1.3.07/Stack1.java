/**
 * 1.3.7 
 * Add a method peek() to Stack that returns the most 
 * recently inserted item on the stack (without popping it).
 *
 * java Stack1 < tobe.txt
 * 
 */

import java.util.Iterator;

public class Stack1<Item> implements Iterable<Item> {

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

	public Item peek() {
		return first.item;
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
		Stack1<String> s = new Stack1<String>();

		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-"))
				s.push(item);
			else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
		}

		StdOut.println(s.peek());

		StdOut.println("(" + s.size() + " left on stack)");

		for (String str : s) {
			StdOut.println(str);
		}
		
		StdOut.println(s.peek());
	}

}