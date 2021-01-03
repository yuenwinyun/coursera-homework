/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Deque;
import java.util.LinkedList;

public class Solver {
    private boolean solvable;
    private SearchNode solution;

    public Solver(Board originalBoard) {
        solution = null;
        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(originalBoard, 0, null));

        while (true) {
            SearchNode current = pq.delMin();
            Board currentBoard = current.getBoard();

            if (currentBoard.isGoal()) {
                solvable = true;
                solution = current;
                break;
            }

            if (currentBoard.hamming() == 2 && currentBoard.twin().isGoal()) {
                solvable = true;
                break;
            }

            int moves = current.getMoves();
            Board prevBoard = moves > 0 ? current.prev().getBoard() : null;

            for (Board nextBoard : currentBoard.neighbors()) {
                if (nextBoard.equals(prevBoard)) {
                    continue;
                }

                pq.insert(new SearchNode(nextBoard, moves + 1, current));
            }
        }
    }


    private class SearchNode implements Comparable<SearchNode> {
        private final SearchNode prev;
        private final Board board;
        private final int moves;

        public SearchNode(Board board, int moves, SearchNode prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
        }

        @Override
        public int compareTo(SearchNode node) {
            return this.priority() - node.priority();
        }

        public int priority() {
            return board.manhattan() + moves;
        }

        public Board getBoard() {
            return board;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode prev() {
            return prev;
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return solvable ? solution.getMoves() : -1;
    }

    public Iterable<Board> solution() {
        if (!solvable) {
            return null;
        }

        Deque<Board> result = new LinkedList<>();
        SearchNode node = solution;

        while (node != null) {
            result.add(node.getBoard());
            node = node.prev();
        }

        return result;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
