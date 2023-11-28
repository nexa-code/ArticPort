package Test;

import java.util.Scanner;

public class NonAiTTT {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get player names
        System.out.print("Enter Player 1 name: ");
        String player1 = scanner.nextLine();
        System.out.print("Enter Player 2 name: ");
        String player2 = scanner.nextLine();

        char[][] board = {
                { ' ', ' ', ' ' },
                { ' ', ' ', ' ' },
                { ' ', ' ', ' ' }
        };

        char currentPlayer = 'X';
        int moves = 0;

        while (true) {
            printBoard(board);

            System.out.println("It's " + (currentPlayer == 'X' ? player1 : player2) + "'s turn.");
            System.out.print("Enter a position (1-9): ");
            int position = scanner.nextInt();

            int row = (position - 1) / 3;
            int col = (position - 1) % 3;

            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                moves++;

                if (checkWinner(board, currentPlayer)) {
                    printBoard(board);
                    System.out.println((currentPlayer == 'X' ? player1 : player2) + " wins!");
                    break;
                }

                if (moves == 9) {
                    printBoard(board);
                    System.out.println("It's a draw!");
                    break;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Cell already occupied. Try again.");
            }
        }

        scanner.close();
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char symbol = board[i][j];
                if (symbol == ' ') {
                    System.out.print((i * 3) + j + 1);
                } else {
                    System.out.print(symbol);
                }
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
        System.out.println();
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