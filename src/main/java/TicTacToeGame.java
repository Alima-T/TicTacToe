import java.util.Scanner;

public class TicTacToeGame {
    final static String GREETING = "Welcome to the game. What is your name?";
    final static String INPUT_MESSAGE = ", please enter numbers from 0 to 2 to choose the coordinates of the field horizontally and vertically: ";
    final static String ERROR_NOT_NUMBER = "Error! You entered not a number.";
    final static String ERROR_OUT_OF_BOARD = "Error! The data is out of the board.";
    final static String FIELD_IS_TAKEN_MESSAGE = "Error! This field is already taken. Please try again.";
    final static String WINNER_MESSAGE = "! Congratulations! You won!";
    final static String NEW_START_MESSAGE = "To start a new game write \"Y\" and press enter";
    final static String X = " X ";
    final static String O = " O ";
    public static String[][] board = new String[][]{{" _ ", " _ ", " _ "}, {" _ ", " _ ", " _ "}, {" _ ", " _ ", " _ "}};
    public static int inputVertical;
    public static int inputHorizontal;

    public static void main (String[] args) {
        printBoard(board);

        Scanner scanner = new Scanner(System.in);

        print(GREETING);
        String player1Name = scanner.next();

        print(GREETING);
        String player2Name = scanner.next();

        while (true) {
            do {
                print(player1Name + INPUT_MESSAGE);
                inputVertical = scanner.nextInt();
                inputHorizontal = scanner.nextInt();

                if (!checkIfInputAreNums(inputVertical, inputHorizontal)) {
                    System.out.println(ERROR_OUT_OF_BOARD);
                }

                if (!checkIfFieldIsFree(inputVertical, inputHorizontal, board)) {
                    System.out.println(FIELD_IS_TAKEN_MESSAGE);
                }
            } while (!checkIfInputIsCorrect(inputVertical, inputHorizontal));

            fillBoard(inputVertical, inputHorizontal, X);
            printBoard(board);

            if (checkWinner(X)) {
                print(player1Name + WINNER_MESSAGE);
                break;
            }

            do {
                print(player2Name + INPUT_MESSAGE);
                inputVertical = scanner.nextInt();
                inputHorizontal = scanner.nextInt();

                if (!checkIfInputAreNums(inputVertical, inputHorizontal)) {
                    System.out.println(ERROR_OUT_OF_BOARD);
                }

                if (!checkIfFieldIsFree(inputVertical, inputHorizontal, board)) {
                    System.out.println(FIELD_IS_TAKEN_MESSAGE);
                }
            } while (!checkIfInputIsCorrect(inputVertical, inputHorizontal));

            fillBoard(inputVertical, inputHorizontal, O);
            printBoard(board);

            if (checkWinner(O)) {
                print(player2Name + WINNER_MESSAGE);
                break;
            }
        }
    }

    static void print (String s) {
        System.out.println(s);
    }

    static boolean checkIfInputIsCorrect (int v, int h) {
        if (!checkIfInputAreNums(v, h)) {
            return false;
        } else {
            if (!checkIfFieldIsFree(inputVertical, inputHorizontal, board)) {
                return false;
            }
        }
        return true;
    }

    static boolean checkIfInputAreNums (int v, int h) {
        if (!(v >= 0 & v <= 2) || !(h >= 0 & h <= 2)) return false;
        return true;
    }

    static boolean checkIfFieldIsFree (int v, int h, String[][] board) {
        if (board[v][h] != " _ ") return false;
        return true;
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
            System.out.println(ERROR_OUT_OF_BOARD);
        }
    }
}





