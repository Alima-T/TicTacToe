import java.util.Scanner;

public class MainGame {
    final static String X = " X ";
    final static String O = " O ";
    final static String FREE = " _ ";
    final static int SIZE = 3;

    final static String GREETING = "Welcome to the game. What is your name?";
    final static String WRONG_INPUT = "Wrong input! Please enter number from 0 to " + (SIZE - 1);
    final static String FIELD_IS_TAKEN_MESSAGE = "Error! This field is already taken. Please try again.";
    final static String INPUT_VERTICAL_MESSAGE = ", please enter number for vertical position from 0 to " + (SIZE - 1);
    final static String INPUT_HORIZONTAL_MESSAGE = ", please enter number for horizontal position: from 0 to " + (SIZE - 1);
    final static String WINNER_MESSAGE = "! Congratulations!!! You won!!!";

    public static Integer inputVertical;
    public static Integer inputHorizontal;
    public static String[][] board = new String[SIZE][SIZE];

    public static void main (String[] args) {
        createBoard(board);
        printBoard(board);

        Scanner scanner = new Scanner(System.in);

        print(GREETING);
        String player1Name = scanner.next();

        print(GREETING);
        String player2Name = scanner.next();

        while (true) {
            while (true) {
                do {
                    print(player1Name + INPUT_VERTICAL_MESSAGE);
                    inputVertical = scanner.nextInt();
                    if (!inputIsCorrect(inputVertical)) {
                        System.out.println(WRONG_INPUT);
                    }
                } while (inputVertical == null);

                do {
                    print(player1Name + INPUT_HORIZONTAL_MESSAGE);
                    inputHorizontal = scanner.nextInt();
                    if (!inputIsCorrect(inputHorizontal)) {
                        System.out.println(WRONG_INPUT);
                    }
                } while (inputHorizontal == null);

                if (!fieldIsFree(inputVertical, inputHorizontal)) {
                    System.out.println(FIELD_IS_TAKEN_MESSAGE);
                } else {
                    fillBoard(inputVertical, inputHorizontal, X);
                    printBoard(board);
                }

                if (checkWinner(X)) {
                    print(player1Name + WINNER_MESSAGE);
                    break;
                }
            }

            while (true) {
                do {
                    print(player2Name + INPUT_VERTICAL_MESSAGE);
                    inputVertical = scanner.nextInt();
                    if (!inputIsCorrect(inputVertical)) {
                        System.out.println(WRONG_INPUT);
                    }
                } while (inputVertical == null);

                do {
                    print(player2Name + INPUT_HORIZONTAL_MESSAGE);
                    inputHorizontal = scanner.nextInt();
                    if (!inputIsCorrect(inputHorizontal)) {
                        System.out.println(WRONG_INPUT);
                    }
                } while (inputHorizontal == null);

                if (!fieldIsFree(inputVertical, inputHorizontal)) {
                    System.out.println(FIELD_IS_TAKEN_MESSAGE);
                }
                fillBoard(inputVertical, inputHorizontal, O);
                printBoard(board);

                if (checkWinner(O)) {
                    print(player2Name + WINNER_MESSAGE);
                    break;
                }
            }
        }
    }

    static void print (String s) {
        System.out.println(s);
    }

    static boolean inputIsCorrect (int i) {
        return (i >= 0 & i < SIZE);
    }

    static boolean fieldIsFree (int v, int h) {
        return board[v][h] == FREE;
    }

    static boolean checkWinner (String s) {
        for (int i = 0; i < board.length; i++) {
            if ((board[i][0] == s & board[i][1] == s & board[i][2] == s) || (board[0][i] == s & board[1][i] == s & board[2][i] == s)) {
                return true;
            }
        }
        if ((board[0][0] == s & board[1][1] == s & board[2][2] == s) || (board[0][2] == s & board[1][1] == s & board[2][0] == s)) {
            return true;
        }
        return false;
    }

    static void printBoard (String[][] matrix) {
        for (String[] array: matrix) {
            for (String s: array) {
                System.out.print(" " + s + " ");
            }
            System.out.println("\n");
        }
    }

    static void fillBoard (int v, int h, String sign) {
        try {
            board[v][h] = sign;
        } catch (Exception ex) {
            System.out.println();
        }
    }

    static String[][] createBoard (String[][] board) {
        for (int v = 0; v < board.length; v++) {
            final String[] row = board[v];
            for (int h = 0; h < row.length; h++) {
                board[v][h] = FREE;
            }
        }
        return board;
    }
}





