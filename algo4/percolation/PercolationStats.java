/* *****************************************************************************
 *  Name:              Yuen
 *  Coursera User ID:  123456
 *  Last modified:     10/12/2020
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int trials;
    private final double shu = 1.96;
    private double meanVal;
    private double stddevVal;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.trials = trials;
        double[] threshold = new double[trials];
        for (int i = 1; i <= trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int r = StdRandom.uniform(n) + 1;
                int c = StdRandom.uniform(n) + 1;
                percolation.open(r, c);
            }
            threshold[i - 1] = (double) percolation.numberOfOpenSites() / (n
                    * n);
        }
        meanVal = StdStats.mean(threshold);
        stddevVal = StdStats.stddev(threshold);
    }

    public double mean() {
        return meanVal;
    }

    public double stddev() {
        return stddevVal;
    }

    public double confidenceLo() {
        return meanVal - shu * stddevVal / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return meanVal + shu * stddevVal / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]),
                                                                 Integer.parseInt(args[1]));
        System.out.println(percolationStats.mean());
        System.out.println(percolationStats.stddev());
        System.out.println(percolationStats.confidenceLo());
        System.out.println(percolationStats.confidenceHi());
    }
}
