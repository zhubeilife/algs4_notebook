import java.util.Arrays;

public class BinarySearch {

    private BinarySearch() { }

    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;

        if (key < a[lo] || key > a[hi]) return -1;

        while (lo < hi) {
            int mid = (hi - lo) / 2 + lo;
            if (key < a[mid]) {
                hi = mid;
            }
            else if (key > a[mid]) {
                lo = mid + 1;
            }
            else if (key == a[mid]) {
                return mid;
            }
        }

        if (key == a[hi]) {
            return hi;
        }
        else {
            return -1;
        }
    }

    public static void main(String[] args) {
        // read the integers form a file
        In in = new In(args[0]);
        int[] allowlist = in.readAllInts();

        // sort the array
        Arrays.sort(allowlist);
        StdOut.println(Arrays.toString(allowlist));
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            StdOut.println("The index is " + BinarySearch.indexOf(allowlist, key));
        }
    }

}
