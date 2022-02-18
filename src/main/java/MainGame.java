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
    public static String[][] board = createBoard(SIZE);
    public static int inputVertical;
    public static int inputHorizontal;

    public static void main(String[] args) {
        printBoard(board);

        Scanner scanner = new Scanner(System.in);

        print(GREETING);
        String player1Name = scanner.next();

        print(GREETING);
        String player2Name = scanner.next();

        String currentPlayer = player1Name;
        String currentSign = X;
        while (true) {
            while (true) {
                print(currentPlayer + INPUT_VERTICAL_MESSAGE);
                inputVertical = scanner.nextInt();
                if (isCorrectInput(inputVertical)) {
                    break;
                }
                print(WRONG_INPUT);
            }
            while (true) {
                print(currentPlayer + INPUT_HORIZONTAL_MESSAGE);
                inputHorizontal = scanner.nextInt();
                if (isCorrectInput(inputHorizontal)) {
                    break;
                }
                print(WRONG_INPUT);
            }
            if (!isFree(inputVertical, inputHorizontal)) {
                print(FIELD_IS_TAKEN_MESSAGE);
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

    private static boolean isCorrectInput(int inputVertical) {
        return inputVertical >= 0 && inputVertical < SIZE;
    }

    private static boolean isFree(int v, int h) {
        return EMPTY.equals(board[v][h]);
    }

    static void print(String s) {
        System.out.println(s);
    }

    static boolean checkWinner(String s) {
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

    private static String[][] createBoard(int size) {
        final String[][] board = new String[size][size];
        for (int v = 0; v < size; v++) {
            for (int h = 0; h < size; h++) {
                board[v][h] = EMPTY;
            }
        }
        return board;
    }
}





