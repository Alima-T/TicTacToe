import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MainGame {
    final static String GREETING = "Welcome to the game. What is your name?\n";


    final static String FIELD_IS_TAKEN_MESSAGE = "This field is already taken. Please try again. \n";
    final static String WINNER_MESSAGE = "! Congratulations! You won!\n";
    final static String X = "X";
    final static String O = "O";
    final static String EMPTY = "_";
    final static int SIZE = 3;
    final static String WRONG_INPUT = "Wrong input. Please enter numbers from 0 to " + (SIZE - 1) + "\n";
    private static final String INPUT_VERTICAL_MESSAGE = ". Please enter vertical position:\n";
    private static final String INPUT_HORIZONTAL_MESSAGE = ". Please enter horizontal position:\n";

    private final String[][] board;
    private final InputReader inputReader;
    private final Printer printer;

    public MainGame(InputReader reader, Printer printer) {
        this.board = createBoard();
        this.inputReader = reader;
        this.printer = printer;
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        new MainGame(scanner::next, System.out::print).start();
    }

    public void start() {
        printBoard(board);


        print(GREETING);
        final String player1Name = inputReader.readInput();

        print(GREETING);
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

    private Integer readInput(String message) {
        print(message);
        int input = -1;
        while (true) {
            try {
                input = Integer.parseInt(inputReader.readInput());
                break;
            } catch (Exception ex) {
                print(WRONG_INPUT);
            }
        }

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

    void print(String s) {
        printer.print(s);
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

    void printBoard(String[][] matrix) {
        for (String[] row : matrix) {
            for (String symbol : row) {
                printer.print("  " + symbol + "  ");
            }
            printer.print("\n\n");
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





