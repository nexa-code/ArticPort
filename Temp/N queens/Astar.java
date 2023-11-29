import java.util.*;

class State implements Comparable<State> {
    int[] board;
    int cost;

    public State(int[] board) {
        this.board = Arrays.copyOf(board, board.length);
        this.cost = calculateCost();
    }

    private int calculateCost() {
        int totalCost = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = i + 1; j < board.length; j++) {
                if (board[i] == board[j] || Math.abs(i - j) == Math.abs(board[i] - board[j])) {
                    totalCost++;
                }
            }
        }
        return totalCost;
    }

    @Override
    public int compareTo(State other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class Astar {

    public static void main(String[] args) {
        int n = 8; // Change this to the desired board size

        int[] initialState = new int[n];
        Arrays.fill(initialState, -1);

        List<State> solution = solveNQueens(n, initialState);

        if (solution != null) {
            System.out.println("Solution found!");
            printBoard(solution.get(0).board);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static List<State> solveNQueens(int n, int[] initialState) {
        PriorityQueue<State> openSet = new PriorityQueue<>();
        Set<String> closedSet = new HashSet<>();

        State startState = new State(initialState);
        openSet.add(startState);

        while (!openSet.isEmpty()) {
            State current = openSet.poll();

            if (current.cost == 0) {
                // Found a solution
                List<State> solution = new ArrayList<>();
                solution.add(current);
                return solution;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (current.board[i] != j) {
                        int[] nextStateBoard = Arrays.copyOf(current.board, n);
                        nextStateBoard[i] = j;
                        State nextState = new State(nextStateBoard);

                        if (!closedSet.contains(Arrays.toString(nextState.board))) {
                            openSet.add(nextState);
                            closedSet.add(Arrays.toString(nextState.board));
                        }
                    }
                }
            }
        }

        return null; // No solution found
    }

    private static void printBoard(int[] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
