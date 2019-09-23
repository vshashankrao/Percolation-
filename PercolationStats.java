/* *****************************************************************************
 *  Name:    Shashank Rao
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    // Instance variable anN which represents the size n of the grid
    private int anN;
    // Instance variable aTrials which represents the number of trials T
    private int aTrials;
    // Instance array values to represents the values obtained from each trial
    private double[] values;


    /**
     * The PercolationStats constructor takes in a size n and the number of trials for input, and
     * creates a PercolationStats object which will perform independent trials on an n-by-n grid
     *
     * @param Integer n, Integer trials.
     * @return A empty PercolationStats object.
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException("trials and n must be greater than 0");
        }
        anN = n;
        aTrials = trials;
        values = new double[trials];
        for (int i = 0; i < trials; i++) {
            values[i] = (double) monteSimulator(anN);
        }

    }

    /**
     * The mean method takes no input, and finds the mean of the calculated values using the
     * StdStats class
     *
     * @param None
     * @return double value representing the mean.
     */
    public double mean() {
        return StdStats.mean(values);
    }

    /**
     * The stddev method takes no input, and finds the standard deviation of the calculated values
     * using the StdStats class
     *
     * @param None
     * @return double value representing the standard deviation.
     */
    public double stddev() {
        if (aTrials == 1) {
            return Double.NaN;
        }
        else {
            return StdStats.stddev(values);
        }
    }

    /**
     * The confidenceLow method takes no input, and finds the low end of the confidence interval of
     * the calculated values using the StdStats class
     *
     * @param None
     * @return double value representing the low endpoint of 95% confidence interval
     */
    public double confidenceLow() {
        return (mean() - (1.96 * stddev()) / (Math.sqrt(aTrials)));
    }

    /**
     * The confidenceHigh method takes no input, and finds the high end of the confidence interval
     * of the calculated values using the StdStats class
     *
     * @param None
     * @return double value representing the high endpoint of 95% confidence interval
     */
    public double confidenceHigh() {
        return (mean() + (1.96 * stddev()) / (Math.sqrt(aTrials)));
    }

    private int monteSimulator(int n) {
        Percolation tester = new Percolation(n);
        int count = 0;
        for (int i = 0; i < n; i++) {
            int r = StdRandom.uniform(n);
            int c = StdRandom.uniform(n);
            if (!tester.isOpen(r, c)) {
                count += 1;
                tester.open(r, c);
            }
        }
        return count;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats test = new PercolationStats(n, trials);
        Stopwatch watch = new Stopwatch();
        StdOut.println("mean =                " + test.mean());
        StdOut.println("standard deviation =  " + test.stddev());
        StdOut.println("confidence low =      " + test.confidenceLow());
        StdOut.println("confidence high =     " + test.confidenceHigh());
        StdOut.println("elapsed time =        " + watch.elapsedTime());
    }
}
