/*************************************************************************
 *  Compilation:  javac SeparateChainingHashST1.java
 *  Execution:    java SeparateChainingHashST1
 *
 *  A symbol table implemented with a separate-chaining hash table.
 *
 *  % java SeparateChainingHashST1
 *
 *************************************************************************/

public class SeparateChainingHashST1<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    // largest prime <= 2^i for i = 3 to 31
    // not currently used for doubling and shrinking
    // private static final int[] PRIMES = {
    //    7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
    //    32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
    //    8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
    //    536870909, 1073741789, 2147483647
    // };

    private int N;                                // number of key-value pairs
    private int M;                                // hash table size
    private SequentialSearchST1<Key, Value>[] st;  // array of linked-list symbol tables


    // create separate chaining hash table
    public SeparateChainingHashST1() {
        this(INIT_CAPACITY);
    }

    // create separate chaining hash table with M lists
    public SeparateChainingHashST1(int M) {
        this.M = M;
        st = (SequentialSearchST1<Key, Value>[]) new SequentialSearchST1[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST1<Key, Value>();
    }

    // resize the hash table to have the given number of chains b rehashing all of the keys
    private void resize(int chains) {
        SeparateChainingHashST1<Key, Value> temp = new SeparateChainingHashST1<Key, Value>(chains);
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.M  = temp.M;
        this.N  = temp.N;
        this.st = temp.st;
    }

    // hash value between 0 and M-1
    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    // return number of key-value pairs in symbol table
    public int size() {
        return N;
    }

    // is the symbol table empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // is the key in the symbol table?
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // return value associated with key, null if no such key
    public Value get(Key key) {
        int i = hash(key);
        // StdOut.println(key);
        return st[i].get(key);

        // for (Node x = st[i]; x != null; x = x.next) {
        //     StdOut.println(x.key);
        //     if (key.equals(x.key)) return (Value) x.val;
        // }

        // return null;
    }

    // insert key-value pair into the table
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }

        // double table size if average length of list >= 10
        if (N >= 10*M) resize(2*M);

        int i = hash(key);
        if (!st[i].contains(key)) N++;
        st[i].put(key, val);
    }

    // delete key (and associated value) if key is in the table
    public void delete(Key key) {
        int i = hash(key);
        if (st[i].contains(key)) N--;
        st[i].delete(key);

        // halve table size if average length of list <= 1
        if (M > INIT_CAPACITY && N <= 2*M) resize(M/2);
    }

    // return keys in symbol table as an Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }


   /***********************************************************************
    *  Unit test client.
    ***********************************************************************/
    public static void main(String[] args) {
        SeparateChainingHashST1<String, Integer> st = new SeparateChainingHashST1<String, Integer>(3);
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys())
            StdOut.println(s + " " + st.hash(s) + " " + st.get(s));

        // StdOut.println(st.hash("B"));
        StdOut.println("search B:");
        StdOut.println(st.get("B"));
    }

}
