/**
 * 1.3.26  Write a method remove() that takes a linked list 
 * and a string key as arguments and removes all of the nodes 
 * in the list that have key as its item field.
 *
 * java LinkListRemove1 to
 * to to be or to to go to to to die
 * ^z / ^d
 * before remove:
 * to to be or to to go to to to die
 * result of remove:
 * be or go die
 * size: 4
 * 
 */

import java.util.Iterator; 

public class LinkListRemove1<Item> implements Iterable<Item> {

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

	public void remove(LinkListRemove1<Item> q, Item key) {
		q.removeKeys(key);
	}

	private void removeKeys(Item key) {

		// first setp: remove Consecutive Keys From First
		// after processed,the linklist begin with a non-key node or is empty
		
		// if linklist is empty do nothing
		if (first == null) return; 

		// the linklist has 1 node at least
		Node newfirst = first;
		while(true) {
			if (newfirst == null) {break;}

			if (newfirst.item.equals(key)) {
				newfirst = newfirst.next;
				N--;
			} else {
				break;
			}
		}
		first = newfirst;

		// second step: remove matched node after first node
		// now the linklist is begin with a non-key node or is empty
		
		// if linklist is empty do nothing
		if (first == null) return; 

		// the link has 1 node at least and that node is non-key node
		Node current = first;
		while (true) {
			if (current == null) { break; }

			// find the non-key node "Node after" after current node
			Node after = current.next;
			while(after != null) {
				if (after.item.equals(key)) {
					after = after.next;
					N--;
				} else {
					break;
				}
			}

			// modify current node's next field to the finded node
			current.next = after;

			// process that non-key node
			current = after;
		}

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

		LinkListRemove1<String> q = new LinkListRemove1<String>();

		while (!StdIn.isEmpty()) {
			q.enqueue(StdIn.readString());
		}

		StdOut.println("before remove:");
		for (String str : q) {
			StdOut.print(str + " ");
		}
		StdOut.println("");

		String key = args[0];
		q.remove(q, key);

		StdOut.println("result of remove:");
		for (String str : q) {
			StdOut.print(str + " ");
		}
		StdOut.println("");
		StdOut.println("size: " + q.size());
	}

}