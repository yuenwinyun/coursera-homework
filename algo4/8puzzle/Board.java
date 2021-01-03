/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.LinkedList;
import java.util.List;

public class Board {
    private final int[][] innerBoards;
    private final int n;
    private int blankRow;
    private int blankCol;

    public Board(int[][] tiles) {
        n = tiles.length;
        blankRow = -1;
        blankCol = -1;
        this.innerBoards = copy(tiles);

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (innerBoards[row][col] == 0) {
                    blankRow = row;
                    blankCol = col;
                    return;
                }
            }
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(n).append("\n");
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                builder.append(String.format("%2d ", innerBoards[row][col]));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == blankRow && j == blankCol) {
                    continue;
                }
                if (manhattan(i, j) != 0) {
                    result++;
                }
            }
        }

        return result;
    }

    public int manhattan() {
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == blankRow && j == blankCol) {
                    continue;
                }
                result += manhattan(i, j);
            }
        }

        return result;
    }

    private int manhattan(int row, int col) {
        int target = innerBoards[row][col] - 1;
        int targetRow = target / n;
        int targetCol = target % n;
        return Math.abs(targetRow - row) + Math.abs(targetCol - col);
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }

        if (y == null) {
            return false;
        }

        if (this.getClass() != y.getClass()) {
            return false;
        }

        Board b = (Board) y;

        if (this.blankCol != b.blankCol) {
            return false;
        }

        if (this.blankRow != b.blankRow) {
            return false;
        }

        if (this.n != b.n) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.innerBoards[i][j] != b.innerBoards[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public Iterable<Board> neighbors() {
        List<Board> neighbors = new LinkedList<>();

        if (blankRow > 0) {
            int[][] north = copy(innerBoards);
            swap(north, blankRow, blankCol, blankRow - 1, blankCol);
            neighbors.add(new Board(north));
        }

        if (blankRow < n - 1) {
            int[][] south = copy(innerBoards);
            swap(south, blankRow, blankCol, blankRow + 1, blankCol);
            neighbors.add(new Board(south));
        }

        if (blankCol > 0) {
            int[][] west = copy(innerBoards);
            swap(west, blankRow, blankCol, blankRow, blankCol - 1);
            neighbors.add(new Board(west));
        }

        if (blankCol < n - 1) {
            int[][] east = copy(innerBoards);
            swap(east, blankRow, blankCol, blankRow, blankCol + 1);
            neighbors.add(new Board(east));
        }

        return neighbors;
    }

    public Board twin() {
        int[][] cloned = copy(innerBoards);

        if (blankRow != 0) {
            swap(cloned, 0, 0, 0, 1);
        }
        else {
            swap(cloned, 1, 0, 1, 1);
        }
        return new Board(cloned);
    }

    private void swap(int[][] b, int r1, int c1, int r2, int c2) {
        int temp = b[r1][c1];
        b[r1][c1] = b[r2][c2];
        b[r2][c2] = temp;
    }

    private int[][] copy(int[][] b) {
        int[][] clone = new int[b.length][];

        for (int i = 0; i < b.length; i++) {
            clone[i] = b[i].clone();
        }

        return clone;
    }
}
