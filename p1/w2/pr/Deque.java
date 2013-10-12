/**
* A double-ended queue or deque (pronounced "deck") is a generalization
* of a stack and a queue that supports inserting and removing items
* from either the front or the back of the data structure.
*
* Throw a java.lang.NullPointerException if the client attempts to
* add a null item;
*
* throw a java.util.NoSuchElementException if the client attempts to remove
* an item from an empty deque;
*
* throw a java.lang.UnsupportedOperationException if the client calls
* the remove() method in the iterator;
*
* throw a java.util.NoSuchElementException if the client calls the
* next() method in the iterator and there are no more items to return.
*
* Your deque implementation should support each deque operation in constant
* worst-case time and use space proportional to the number of items
* currently in the deque.
*
* Additionally, your iterator implementation should
* support the operations next() and hasNext() (plus construction) in
* constant worst-case time and use a constant amount of extra
* space per iterator.
*
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private int N; // number of elements on deque
  private Node first; // beginning of deque
  private Node last; // end of deque

  private class Node {
    private Item item;
    private Node next;
    private Node prev;
  }


// construct an empty deque
  public Deque() {
    first = null;
    last = null;
    N = 0;
    assert check();
  }

// is the deque empty?
  public boolean isEmpty() {
    // return first == null;
    return N == 0;
  }

// return the number of items on the deque
  public int size() {
    return N;
  }

// insert the item at the front
  public void addFirst(Item item) {
    if (item == null) throw new NullPointerException();
    Node oldfirst = first;
    first = new Node();
    first.item = item;

    if (isEmpty()) {
      last = first;
    } else {
      first.next = oldfirst;
      oldfirst.prev = first;
    }

    N += 1;
    assert check();
  }

// insert the item at the end
  public void addLast(Item item) {
    if (item == null) throw new NullPointerException();
    Node oldlast = last;
    last = new Node();
    last.item = item;

    if (isEmpty()) {
      first = last;
    } else {
      last.prev = oldlast;
      oldlast.next = last;
    }

    N += 1;
    assert check();
  }

// delete and return the item at the front
  public Item removeFirst() {
    if (isEmpty()) throw new NoSuchElementException();
    Item item = first.item;

    if (N == 1) {
      first = null;
      last = null;
    }

    if (N == 2) {
      first = last;
      first.next = null;
      last.prev = null;
    }

    if (N > 2) {
      first = first.next;
      first.prev = null;
    }

    N--;
    assert check();
    return item;
  }

// delete and return the item at the end
  public Item removeLast() {
    if (isEmpty()) throw new NoSuchElementException();
    Item item = last.item;

    if (N == 1) {
      first = null;
      last = null;
    }

    if (N == 2) {
      last = first;
      last.prev = null;
      first.next = null;
    }

    if (N > 2) {
      last = last.prev;
      last.next = null;
    }

    N--;
    assert check();
    return item;
  }

// return an iterator over items in order from front to end
  public Iterator<Item> iterator() {
    return new ListIterator();
  }

// an iterator, doesn't implement remove() since it's optional
  private class ListIterator implements Iterator<Item> {
    private Node current = first;

    public boolean hasNext()  { return current != null;                     }
    public void remove()      { throw new UnsupportedOperationException();  }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

// check internal invariants
  private boolean check() {
    if (N == 0) {
      if (first != null) return false;
      if (last  != null) return false;
    }
    else if (N == 1) {
      if (first == null || last == null) return false;
      if (first != last)                 return false;
      if (first.next != null)            return false;
      if (first.prev != null)            return false;
    }
    else {
      if (first == last)      return false;
      if (first.next == null) return false;
      if (first.prev != null) return false;
      if (last.next  != null) return false;
      if (last.prev  == null) return false;

// check internal consistency of instance variable N
      int numberOfNodes = 0;
      for (Node x = first; x != null; x = x.next) {
        numberOfNodes++;
      }
      if (numberOfNodes != N) return false;

      numberOfNodes = 0;
      for (Node x = last; x != null; x = x.prev) {
        numberOfNodes++;
      }
      if (numberOfNodes != N) return false;

// check internal consistency of instance variable last
      Node lastNode = first;
      while (lastNode.next != null) {
        lastNode = lastNode.next;
      }
      if (last != lastNode) return false;

      Node firstNode = last;
      while (firstNode.prev != null) {
        firstNode = firstNode.prev;
      }
      if (first != firstNode) return false;
    }

    return true;
  }

  public static void main(String[] args) {
    Deque<String> q = new Deque<String>();
    q.addFirst("a");
    q.addFirst("a");
    q.addFirst("a");
    // q.removeLast();
    // q.addFirst("a");
    // Iterator<String> it = q.iterator();
    // Iterator<String> it2 = q.iterator();
    // it.next();
    // q.removeLast();
    // q.addFirst(null);
    // while (!StdIn.isEmpty()) {
    //   String item = StdIn.readString();
    //   if (!item.equals("-")) q.addLast(item);
    //   else if (!q.isEmpty()) StdOut.print(q.removeFirst() + " ");
    // }
    // StdOut.println("(" + q.size() + " left on queue)");
  }

}