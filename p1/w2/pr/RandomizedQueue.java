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

  private Item[] a;   // array of items
  private int N;      // number of elements on queue

// construct an empty randomized queue
  public RandomizedQueue() {
    a = (Item[]) new Object[2];
  }

// is the queue empty?
  public boolean isEmpty() { return N == 0; }

// return the number of items on the queue
  public int size() { return N; }

// resize the underliying array holding the elements
  private void resize(int capacity) {
    assert capacity >= N;
    Item[] temp = (Item[]) new Object[capacity];
    for (int i = 0; i < N; i++) {
      temp[i] = a[i];
    }
    a = temp;
  }

// add the item
  public void enqueue(Item item) {
    if (item == null) throw new NullPointerException();

    if (N == a.length) resize(2 * a.length);
    a[N] = item;
    N++;

    assert check();

  }

// delete and return a random item
  public Item dequeue() {
    if (isEmpty()) throw new NoSuchElementException();

    int x = StdRandom.uniform(N);
    Item item = a[x];

    exch(x, N-1);
    a[N-1] = null;
    N--;

    if (N > 0 && N == a.length/4) resize(a.length/2);

    assert check();
    return item;

  }

  private void exch(int i, int j) {
    Item tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

// return (but do not delete) a random item
  public Item sample() {
    if (isEmpty()) throw new NoSuchElementException();
    int x = StdRandom.uniform(N);
    return a[x];
  }

// return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomArrayIterator();
  }

  // an iterator, doesn't implement remove() since it's optional
  private class RandomArrayIterator implements Iterator<Item> {
    private int[] idxArr;
    // every next(),random select from 0 to lastIdx
    private int lastIdx;

    public RandomArrayIterator() {
      lastIdx = N - 1; 
      idxArr = new int[N];
      for (int k = 0; k < N; k++) {
        idxArr[k] = k;
      }
    }

    public boolean hasNext() {
      return lastIdx >= 0;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();

      // i--;
      // int x = StdRandom.uniform(N);
      // return a[x];

      int i = StdRandom.uniform(lastIdx+1);
      Item item = a[idxArr[i]];
      // exchange idxArr[i] and idxArr[lastIdx]
      int tmp = idxArr[i];
      idxArr[i] = idxArr[lastIdx];
      idxArr[lastIdx] = tmp;

      lastIdx--;
      return item;
    }

  }

  private boolean check() {
    // check internal consistency of instance varible a and N
    int number = 0;
    for (int i = 0; i < a.length; i++) {
      if (a[i] != null) number++;
    }
    if (number != N) return false;
    return true;
  }

  public static void main(String[] args) {
    RandomizedQueue<String> r = new RandomizedQueue<String>();
    r.enqueue("a");
    r.enqueue("b");
    // r.enqueue("c");
    Iterator<String> it = r.iterator();
    // Iterator<String> it2 = r.iterator();
    StdOut.println(it.next());
    StdOut.println(it.next());

    for (String s : r) {
      for (String s2 : r) {
        StdOut.println("inner1:" + s2);
      }
      StdOut.println("outer: " + s);
      for (String s3: r) {
        StdOut.println("inner2:" + s3);
      }
    }
    // StdOut.println(it.next());
    // StdOut.println(r.sample());
    // r.dequeue();
    // it.remove();
  }

}