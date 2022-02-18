import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MainGame {
    final static String GREETING = "Welcome to the game. What is your name?";


    final static String FIELD_IS_TAKEN_MESSAGE = "This field is already taken. Please try again. ";
    final static String WINNER_MESSAGE = "! Congratulations! You won!";
    final static String X = "X";
    final static String O = "O";
    final static String EMPTY = "_";
    final static int SIZE = 3;
    final static String WRONG_INPUT = "Wrong input. Please enter numbers from 0 to " + (SIZE - 1);
    private static final String INPUT_VERTICAL_MESSAGE = ". Please enter vertical position:";
    private static final String INPUT_HORIZONTAL_MESSAGE = ". Please enter horizontal position:";

    private final String[][] board = createBoard();

    public static void main(String[] args) {
        new MainGame().start();
    }

    public void start() {
        printBoard(board);

        final Scanner scanner = new Scanner(System.in);

        print(GREETING);
        final String player1Name = scanner.next();

        print(GREETING);
        final String player2Name = scanner.next();

        String currentPlayer = player1Name;
        String currentSign = X;


        Integer inputVertical;
        Integer inputHorizontal;

        while (true) {
            do {
                inputVertical = readInput(scanner, currentPlayer + INPUT_VERTICAL_MESSAGE);
            } while(inputVertical == null);
            do {
                inputHorizontal = readInput(scanner, currentPlayer + INPUT_HORIZONTAL_MESSAGE);
            } while(inputHorizontal == null);

            if (!isFree(inputVertical, inputHorizontal)) {
                print(FIELD_IS_TAKEN_MESSAGE);
                continue;
            }
            board[inputVertical][inputHorizontal] = currentSign;
            printBoard(board);

            if (checkWinner(currentSign)) {
                print(currentPlayer + WINNER_MESSAGE);
                break;
            }

            currentPlayer = currentPlayer.equals(player1Name) ? player2Name : player1Name;
            currentSign = currentSign.equals(X) ? O : X;
        }
    }

    private static Integer readInput(Scanner scanner, String message) {
        print(message);
        final int input  = scanner.nextInt();
        if (isCorrectInput(input)) {
            return input;
        }
        print(WRONG_INPUT);
        return null;
    }

    private static boolean isCorrectInput(int inputVertical) {
        return inputVertical >= 0 && inputVertical < SIZE;
    }

    private boolean isFree(int v, int h) {
        return EMPTY.equals(board[v][h]);
    }

    static void print(String s) {
        System.out.println(s);
    }

    private boolean checkWinner(String s) {
        if (hasWinningLine((v, h) -> s.equals(board[v][h]))) {
            return true;
        }
        if (hasWinningLine((h, v) -> s.equals(board[v][h]))) {
            return true;
        }
        if (hasWinningDiagonal(i -> s.equals(board[i][i]))) {
            return true;
        }
        if (hasWinningDiagonal(i -> s.equals(board[SIZE - i - 1][i]))) {
            return true;
        }
        return false;
    }

    static boolean hasWinningLine(BiFunction<Integer, Integer, Boolean> check) {
        d1_loop:
        for (int d1 = 0; d1 < SIZE; d1++) {
            for (int d2 = 0; d2 < SIZE; d2++) {
                if (!check.apply(d1, d2)) {
                    continue d1_loop;
                }
            }
            return true;
        }
        return false;
    }

    static boolean hasWinningDiagonal(Function<Integer, Boolean> check) {
        boolean hasDiagonal = true;
        for (int i = 0; i < SIZE; i++) {
            if (!check.apply(i)) {
                hasDiagonal = false;
                break;
            }
        }
        return hasDiagonal;
    }

    static void printBoard(String[][] matrix) {
        for (String[] row : matrix) {
            for (String symbol : row) {
                System.out.print("  " + symbol + "  ");
            }
            System.out.println("\n");
        }
    }

    private static String[][] createBoard() {
        final String[][] board = new String[SIZE][SIZE];
        for (int v = 0; v < SIZE; v++) {
            for (int h = 0; h < SIZE; h++) {
                board[v][h] = EMPTY;
            }
        }
        return board;
    }
}





