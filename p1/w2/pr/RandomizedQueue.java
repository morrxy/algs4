/**
* A randomized queue is similar to a stack or queue, except that the
* item removed is chosen uniformly at random from items in the data structure
*
* Throw a java.lang.NullPointerException if the client attempts to
* add a null item;
* 
* throw a java.util.NoSuchElementException if the client attempts to
* sample or dequeue an item from an empty randomized queue;
* 
* throw a java.lang.UnsupportedOperationException if the client
* calls the remove() method in the iterator;
* 
* throw a java.util.NoSuchElementException if the client calls the
* next() method in the iterator and there are no more items to return.
*
* Your randomized queue implementation should support each randomized
* queue operation (besides creating an iterator) in constant amortized
* time and use space proportional to the number of items currently in
* the queue.
*  
* That is, any sequence of M randomized queue operations
* (starting from an empty queue) should take at most cM steps in the
* worst case, for some constant c. 
* 
* Additionally, your iterator implementation should support construction 
* in time linear in the number of items and it should support the operations 
* next() and hasNext() in constant worst-case time; 
* 
* you may use a linear amount of extra memory per iterator.
* 
* The order of two or more iterators to the same randomized queue should 
* be mutually independent; 
* 
* each iterator must maintain its own random order.
*
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private int N; // number of elements on deque
  private Node first; // beginning of deque
  private Node last; // end of deque

  private class Node {
    private Item item;
    private Node next;
    private Node prev;
  }

// construct an empty randomized queue
  public RandomizedQueue() {
    first = null;
    last = null;
    N = 0;
  }

// is the queue empty?
  public boolean isEmpty() {
    return first == null;
  }

// return the number of items on the queue
  public int size() {
    return N;
  }

// add the item
  public void enqueue(Item item) {
    if (item == null) throw new NullPointerException();
    Node oldlast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    last.prev = oldlast;
    if (isEmpty()) first = last;
    else oldlast.next = last;
    N += 1;
  }

// delete and return a random item
  public Item dequeue() {
    if (isEmpty()) throw new NoSuchElementException();
    int x = StdRandom.uniform(N);
    Node nx = findNthNode(x);
    Item item = nx.item;

    if (N == 1) {
      first = null;
      last = null;
    }

    if (N == 2) {
      if (nx == first) {
        first = last;
        last.prev = null;
      }
      if (nx == last) {
        last = first;
        first.next = null;
      }
    }

    if (N >= 3) {
      if (nx == first) {
        first = first.next;
        first.prev = null;
      } else if (nx == last) {
        last = last.prev;
        last.next = null;
      } else {
        nx.prev.next = nx.next;
        nx.next.prev = nx.prev;
      }
    }

    N--;
    return item;
  }

// return (but do not delete) a random item
  public Item sample() {
    if (isEmpty()) throw new NoSuchElementException();
    int x = StdRandom.uniform(N);
    Node nx = findNthNode(x);
    return nx.item;
  }

  private Node findNthNode(int n) {
    if (n < N / 2) {
      int i = 0;
      Node nx = first;
      while (true) {
        if (i == n) return nx;
        nx = nx.next;
        i++;
      }
    } else {
      int i = N - 1;
      Node nx = last;
      while (true) {
        if (i == n) return nx;
        nx = nx.prev;
        i--;
      }
    }
  }

// return an independent iterator over items in random order
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

  public static void main(String[] args) {
    RandomizedQueue<String> r = new RandomizedQueue<String>();
    // r.sample();
    // r.dequeue();
    // Iterator<String> it = r.iterator();
    // it.remove();
    // r.enqueue(null);
  }

}