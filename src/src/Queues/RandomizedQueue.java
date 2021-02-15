import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 8;

    private int n;
    private Item[] data;

    // construct an empty randomized queue
    public RandomizedQueue() {
        data = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    private void resize(int newsize) {
        Item[] newdata = (Item[]) new Object[newsize];
        for (int i = 0; i < n ; i++) {
            newdata[i] = data[i];
        }

        data = newdata;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("man, carefully!");
        if (n == data.length) resize(data.length * 2);
        data[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("man, come on");
        StdRandom.shuffle(data, 0, n);

        if ((n < data.length / 4) && (data.length != INIT_CAPACITY)) {
            resize(data.length / 2);
        }

        Item value = data[n-1];
        data[n-1] = null;
        n--;

        return value;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("man, come on");
        StdRandom.shuffle(data, 0, n);
        return data[n-1];
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString();
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int nums;
        private Item[] itdata;

        public RandomizedQueueIterator() {
            nums = n;
            itdata = (Item[]) new Object[nums];
            for (int i = 0; i < nums; i++) {
                itdata[i] = data[i];
            }

            StdRandom.shuffle(itdata);
        }

        public boolean hasNext()  { return nums != 0;}
        public void remove()      { throw new UnsupportedOperationException();}

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("ops");
            return itdata[--nums];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> r = new RandomizedQueue<String>();

        // Test as normal queue
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) r.enqueue(item);
            else if (!r.isEmpty()) StdOut.print(r.dequeue() + " ");
            StdOut.println("  Now is:  " + r);
        }
        StdOut.println("(" + r.size() + " left on randomized queue)");

        // Test when the deque goes from non-empty to empty and then non-empty
//        StdOut.println("now is " + deque);
//        deque.removeFirst();
//        deque.removeLast();
//        StdOut.println("now is " + deque);
//        deque.addFirst("hey");
//        StdOut.println("now is " + deque);
    }
}
