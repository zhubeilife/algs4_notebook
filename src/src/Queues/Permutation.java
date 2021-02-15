import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = (int)args[0];
        RandomizedQueue<String> rd = new RandomizedQueue<String>();
        if (!StdIn.isEmpty()) {
            rd.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(rd.dequeue());
        }
    }
}
