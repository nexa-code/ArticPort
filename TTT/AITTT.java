package Test;

import java.util.Scanner;

public class AITTT {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get player names
        System.out.print("Enter Player 1 name: ");
        String player1 = scanner.nextLine();
        System.out.println("Player 2 (AI) will play with 'O'");

        char[][] board = {
                { ' ', ' ', ' ' },
                { ' ', ' ', ' ' },
                { ' ', ' ', ' ' }
        };

        char currentPlayer = 'X';
        int moves = 0;

        while (true) {
            printBoard(board);

            System.out.println("It's " + (currentPlayer == 'X' ? player1 : "AI") + "'s turn.");

            if (currentPlayer == 'X') {
                System.out.print("Enter a position (1-9): ");
                int position = scanner.nextInt();

                int row = (position - 1) / 3;
                int col = (position - 1) % 3;

                if (board[row][col] == ' ') {
                    board[row][col] = currentPlayer;
                    moves++;

                    if (checkWinner(board, currentPlayer)) {
                        printBoard(board);
                        System.out.println((currentPlayer == 'X' ? player1 : "AI") + " wins!");
                        break;
                    }

                    if (moves == 9) {
                        printBoard(board);
                        System.out.println("It's a draw!");
                        break;
                    }

                    currentPlayer = 'O'; // Switch to AI's turn
                } else {
                    System.out.println("Cell already occupied. Try again.");
                }
            } else {
                makeComputerMove(board);
                moves++;

                if (checkWinner(board, 'O')) {
                    printBoard(board);
                    System.out.println("AI wins!");
                    break;
                }

                if (moves == 9) {
                    printBoard(board);
                    System.out.println("It's a draw!");
                    break;
                }

                currentPlayer = 'X'; // Switch back to player's turn
            }
        }

        scanner.close();
    }

    private static void makeComputerMove(char[][] board) {
        int[] bestMove = minimax(board, 'O');
        board[bestMove[0]][bestMove[1]] = 'O';
    }

    private static int[] minimax(char[][] board, char player) {
        int[] bestMove = new int[]{-1, -1};
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = player;
                    int score = minimaxScore(board, player, false);
                    board[i][j] = ' '; // undo the move

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }

        return bestMove;
    }

    private static int minimaxScore(char[][] board, char player, boolean isMaximizing) {
        char opponent = (player == 'X') ? 'O' : 'X';

        if (checkWinner(board, 'O')) {
            return 1;
        } else if (checkWinner(board, 'X')) {
            return -1;
        } else if (isBoardFull(board)) {
            return 0;
        }

        int bestScore = (isMaximizing) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = (isMaximizing) ? player : opponent;
                    int score = minimaxScore(board, player, !isMaximizing);
                    board[i][j] = ' '; // undo the move

                    if ((isMaximizing && score > bestScore) || (!isMaximizing && score < bestScore)) {
                        bestScore = score;
                    }
                }
            }
        }

        return bestScore;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard(char[][] board){
        System.out.println("|---|---|---|");
            System.out.println("| " + board[0][0] + " | "
                    + board[0][1] + " | " + board[0][2]
                    + " |");
            System.out.println("|-----------|");
            System.out.println("| " + board[1][0] + " | "
                    + board[1][1] + " | " + board[1][2]
                    + " |");
            System.out.println("|-----------|");
            System.out.println("| " + board[2][0] + " | "
                    + board[2][1] + " | " + board[2][2]
                    + " |");
            System.out.println("|---|---|---|");
    }

    private static boolean checkWinner(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; 
            }
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; 
        }

        return board[0][2] == player && board[1][1] == player && board[2][0] == player; 
    }
}
