/* *****************************************************************************
 *  Name:              Yuen
 *  Coursera User ID:  123456
 *  Last modified:     10/12/2020
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private boolean[][] openedSites;
    private final WeightedQuickUnionUF uf;
    private final int N;
    private int openedCount;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        N = n;
        uf = new WeightedQuickUnionUF(n * n
                                              + 2);
        openedSites = new boolean[n][n];
        for (int i = 0; i <= n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                openedSites[i][j] = false;
            }
        }
        openedCount = 0;
    }

    private int getCurrentIndex(int row, int col) {
        return row * N + col;
    }

    public void open(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N) throw new IllegalArgumentException();
        int currentRow = row - 1;
        int currentCol = col - 1;
        if (!openedSites[currentRow][currentCol]) {
            int currentIndex = getCurrentIndex(currentRow, currentCol);
            openedCount++;
            openedSites[currentRow][currentCol] = true;
            if (row == 1) {
                uf.union(currentCol, N * N);
            }
            if (row == N) {
                uf.union(getCurrentIndex(currentRow, currentCol), N * N + 1);
            }
            if (row > 1 && openedSites[currentRow - 1][currentCol]) {
                uf.union(getCurrentIndex(currentRow - 1, currentCol), currentIndex);
            }
            if (col > 1 && openedSites[currentRow][currentCol - 1]) {
                uf.union(getCurrentIndex(currentRow, currentCol - 1), currentIndex);
            }
            if (col < N && openedSites[currentRow][currentCol + 1]) {
                uf.union(getCurrentIndex(currentRow, currentCol + 1), currentIndex);
            }
            if (row < N && openedSites[currentRow + 1][currentCol]) {
                uf.union(getCurrentIndex(currentRow + 1, currentCol), currentIndex);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N) throw new IllegalArgumentException();
        return openedSites[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N) throw new IllegalArgumentException();
        return uf.find(N * N) == uf.find(getCurrentIndex(row - 1, col - 1));
    }

    public int numberOfOpenSites() {
        return openedCount;
    }

    public boolean percolates() {
        return uf.find(N * N) == uf.find(N * N + 1);
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(6);
        percolation.open(1, 6);
        percolation.open(2, 6);
        percolation.open(3, 6);
        percolation.open(4, 6);
        percolation.open(5, 6);
        percolation.open(5, 5);
        percolation.open(4, 4);
        percolation.open(3, 4);
        percolation.open(2, 4);
        percolation.open(2, 3);
        percolation.open(2, 2);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 1);
        percolation.open(5, 1);
        percolation.open(5, 2);
        percolation.open(6, 2);
        percolation.open(5, 4);
        System.out.println(percolation.percolates());
    }
}
