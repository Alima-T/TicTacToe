import java.util.Scanner;

public class Game {
    final static String X = "X";
    final static String O = "O";
    final static String FREE = "_";
    final static int SIZE = 3;

    final static String GREETING = "Welcome to the game. What is your name? \n";
    final static String WRONG_INPUT = "Wrong input! Please enter number from 0 to " + (SIZE - 1) + ": \n";
    final static String FIELD_IS_TAKEN_MESSAGE = "Error! This field is already taken. Please try again.";
    final static String INPUT_VERTICAL_MESSAGE = ", please enter number for vertical position from 0 to to " + (SIZE - 1) + ": \n";
    final static String INPUT_HORIZONTAL_MESSAGE = ", please enter number for horizontal position: from 0 to to " + (SIZE - 1) + ": \n";
    final static String WINNER_MESSAGE = "! Congratulations!!! You won!!!";

    public static String[][] board;
    private final InputReader inputReader;
    private final Printer printer;

    public Game(InputReader inputReader, Printer printer) {
        this.inputReader = inputReader;
        this.printer = printer;
        this.board = createBoard(SIZE, SIZE, FREE);
    }

    public void start() {
        printBoard(board);

        printer.print(GREETING);
        final String player1Name = inputReader.readInput();

        printer.print(GREETING);
        final String player2Name = inputReader.readInput();

        String currentPlayer = player1Name;
        String currentSign = X;


        Integer inputVertical;
        Integer inputHorizontal;

        while (true) {
            do {
                inputVertical = readInput(currentPlayer + INPUT_VERTICAL_MESSAGE);
            } while (inputVertical == null);
            do {
                inputHorizontal = readInput(currentPlayer + INPUT_HORIZONTAL_MESSAGE);
            } while (inputHorizontal == null);

            if (!fieldIsFree(inputVertical, inputHorizontal)) {
                printer.print(FIELD_IS_TAKEN_MESSAGE);
                continue;
            }
            board[inputVertical][inputHorizontal] = currentSign;
            printBoard(board);

            if (checkWinner(currentSign)) {
                printer.print(currentPlayer + WINNER_MESSAGE);
                break;
            }

            currentPlayer = currentPlayer.equals(player1Name) ? player2Name : player1Name;
            currentSign = currentSign.equals(X) ? O : X;
        }
    }

    static boolean inputIsCorrect(int i) {
        return (i >= 0 & i < SIZE);
    }

    static boolean fieldIsFree(int v, int h) {
        return board[v][h] == FREE;
    }

    static boolean checkWinner(String s) {
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

    static void printBoard(String[][] matrix) {
        for (String[] array : matrix) {
            for (String s : array) {
                System.out.print("  " + s + "  ");
            }
            System.out.println("\n");
        }
    }

    static String[][] createBoard(int size1, int size2, String s) {
        final String[][] board = new String[size1][size2];
        for (int v = 0; v < board.length; v++) {
            final String[] row = board[v];
            for (int h = 0; h < row.length; h++) {
                board[v][h] = s;
            }
        }
        return board;
    }

    private Integer readInput(String text) {
        printer.print(text);
        int input = -1;
        while (true) {
            try {
                input = Integer.parseInt(inputReader.readInput());
                break;
            } catch (Exception ex) {
                printer.print(WRONG_INPUT);
            }
        }
        if (inputIsCorrect(input)) {
            return input;
        }
        printer.print(WRONG_INPUT);
        return null;
    }
}





