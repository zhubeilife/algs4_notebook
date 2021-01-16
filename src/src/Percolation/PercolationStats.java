import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final int size;
    private final double[] thresholdSamples;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if ((n <= 0) || (trials <= 0)) {
            throw new IllegalArgumentException("Out of range man");
        }

        size = n;
        thresholdSamples = new double[n];

        for (int i = 0; i < size; i++) {
            thresholdSamples[i] = doPercolation(size);
        }
    }

    private double doPercolation(int n) {
        Percolation per = new Percolation(n);

        while (!per.percolates()) {
            int row = StdRandom.uniform(size) + 1;
            int col = StdRandom.uniform(size) + 1;
            per.open(row, col);
        }

        return (double) per.numberOfOpenSites() / (size * size);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholdSamples);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholdSamples);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() -  CONFIDENCE_95* stddev() / Math.sqrt(size);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(size);
    }

    // test client (see below)
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(size, trials);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = " + "[" + stats.confidenceLo() + ", " + stats.confidenceHi() +"]");
    }
}
