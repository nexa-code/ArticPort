import java.util.*;

class PuzzleState implements Comparable<PuzzleState> {
    int[][] board;
    int zeroRow, zeroCol;
    int cost; // Total cost = cost + heuristic

    public PuzzleState(int[][] board, int cost) {
        this.board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = board[i][j];
                if (board[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }
        this.cost = cost;
    }

    public boolean isGoal() {
        int value = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != value % 9) {
                    return false;
                }
                value++;
            }
        }
        return true;
    }

    public List<PuzzleState> getNeighbors() {
        List<PuzzleState> neighbors = new ArrayList<>();
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int newRow = zeroRow + dr[i];
            int newCol = zeroCol + dc[i];

            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                int[][] newBoard = new int[3][3];
                for (int j = 0; j < 3; j++) {
                    newBoard[j] = Arrays.copyOf(board[j], 3);
                }

                // Swap the empty space with the neighboring tile
                newBoard[zeroRow][zeroCol] = newBoard[newRow][newCol];
                newBoard[newRow][newCol] = 0;

                PuzzleState neighbor = new PuzzleState(newBoard, 0); // Cost is set to 0 for neighbors
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    // Manhattan distance heuristic
    private int calculateHeuristic() {
        int heuristic = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = board[i][j];
                if (value != 0) {
                    int goalRow = (value - 1) / 3;
                    int goalCol = (value - 1) % 3;
                    heuristic += Math.abs(i - goalRow) + Math.abs(j - goalCol);
                }
            }
        }
        return heuristic;
    }

    @Override
    public int compareTo(PuzzleState other) {
        return Integer.compare(this.cost + this.calculateHeuristic(), other.cost + other.calculateHeuristic());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PuzzleState that = (PuzzleState) obj;
        return Arrays.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] initialBoard = {
                {1, 2, 3},
                {4, 0, 5},
                {6, 7, 8}
        };

        PuzzleState initialState = new PuzzleState(initialBoard, 0);
        bestFirstSearch(initialState);
    }

    public static void bestFirstSearch(PuzzleState initialState) {
        PriorityQueue<PuzzleState> priorityQueue = new PriorityQueue<>();
        Set<PuzzleState> visited = new HashSet<>();

        priorityQueue.add(initialState);
        visited.add(initialState);

        while (!priorityQueue.isEmpty()) {
            PuzzleState current = priorityQueue.poll();
            System.out.println("Current State (Cost: " + current.cost + "):");
            printBoard(current.board);

            if (current.isGoal()) {
                System.out.println("Goal State Reached!");
                return;
            }

            List<PuzzleState> neighbors = current.getNeighbors();
            for (PuzzleState neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    priorityQueue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        System.out.println("Goal State not reachable!");
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
