import java.util.function.BiFunction;
import java.util.function.Function;

public class Game {

    final static String GREETING = "Welcome to the game. What is your name?\n";


    final static String FIELD_IS_TAKEN_MESSAGE = "This field is already taken. Please try again. \n";
    final static String WINNER_MESSAGE = "! Congratulations! You won!\n";
    final static String X = "X";
    final static String O = "O";
    final static String EMPTY = "_";

    private static final String INPUT_VERTICAL_MESSAGE = ". Please enter vertical position:\n";
    private static final String INPUT_HORIZONTAL_MESSAGE = ". Please enter horizontal position:\n";
    private static final String WRONG_INPUT = "Wrong input. Please enter numbers from 0 to %d\n";


    private final String[][] board;
    private final InputReader inputReader;
    private final Printer printer;
    private final int size;
    private final String wrongInput;

    public Game(InputReader inputReader, Printer printer, int size) {
        this.inputReader = inputReader;
        this.printer = printer;
        this.size = size;
        this.board = createBoard(size);
        this.wrongInput = String.format(WRONG_INPUT, size - 1);
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

            if (!isFree(inputVertical, inputHorizontal)) {
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

    private Integer readInput(String message) {
        printer.print(message);
        int input = -1;
        while (true) {
            try {
                input = Integer.parseInt(inputReader.readInput());
                break;
            } catch (Exception ex) {
                printer.print(wrongInput);
            }
        }

        if (isCorrectInput(input)) {
            return input;
        }
        printer.print(wrongInput);
        return null;
    }


    private boolean isFree(int v, int h) {
        return EMPTY.equals(board[v][h]);
    }

    private boolean isCorrectInput(int inputVertical) {
        return inputVertical >= 0 && inputVertical < size;
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
        if (hasWinningDiagonal(i -> s.equals(board[size - i - 1][i]))) {
            return true;
        }
        return false;
    }

    private boolean hasWinningDiagonal(Function<Integer, Boolean> check) {
        boolean hasDiagonal = true;
        for (int i = 0; i < size; i++) {
            if (!check.apply(i)) {
                hasDiagonal = false;
                break;
            }
        }
        return hasDiagonal;
    }

    private boolean hasWinningLine(BiFunction<Integer, Integer, Boolean> check) {
        d1_loop:
        for (int d1 = 0; d1 < size; d1++) {
            for (int d2 = 0; d2 < size; d2++) {
                if (!check.apply(d1, d2)) {
                    continue d1_loop;
                }
            }
            return true;
        }
        return false;
    }

    private void printBoard(String[][] matrix) {
        for (String[] row : matrix) {
            for (String symbol : row) {
                printer.print("  " + symbol + "  ");
            }
            printer.print("\n\n");
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
