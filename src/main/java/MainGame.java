import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MainGame {
    final static String GREETING = "Welcome to the game. What is your name?";
    final static String INPUT_MESSAGE = ", please enter numbers from 0 to 2" + " to choose the coordinates of the field horizontally and vertically: ";

    final static String ERROR_MESSAGE = "Please enter the correct coordinates: ";
    final static String FIELD_IS_TAKEN_MESSAGE = "This field is already taken. Please try again: ";
    final static String WINNER_MESSAGE = "! Congratulations! You won!";
    final static String NEW_START_MESSAGE = "To start a new game write \"Y\" and press enter";
    final static String X = "X";
    final static String O = "O";
    final static String EMPTY = "_";
    final static int SIZE = 3;
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

        while (true) {
            print(player1Name + INPUT_MESSAGE);
            inputVertical = scanner.nextInt();
            inputHorizontal = scanner.nextInt();

            if (checkInput(inputVertical, inputHorizontal)) {
                board[inputVertical][inputHorizontal] = X;
                printBoard(board);
            }
            if (checkWinner(X)) {
                print(player1Name + WINNER_MESSAGE);
                break;
            }

            print(player2Name + INPUT_MESSAGE);
            inputVertical = scanner.nextInt();
            inputHorizontal = scanner.nextInt();

            if (checkInput(inputVertical, inputHorizontal)) {
                board[inputVertical][inputHorizontal] = O;
                printBoard(board);
            }
            if (checkWinner(O)) {
                print(player2Name + WINNER_MESSAGE);
                break;
            }
        }
    }


    static void print(String s) {
        System.out.println(s);
    }

    static boolean checkInput(int v, int h) {
        if (!checkInputNums(v, h)) {
            System.out.println(ERROR_MESSAGE);
        } else {
            if (!checkIfFieldIsFree(v, h)) {
                System.out.println(FIELD_IS_TAKEN_MESSAGE);
            }
        }
        return true;
    }

    static boolean checkInputNums(int v, int h) {
        return (v >= 0 & v < SIZE) || (h >= 0 & h < SIZE);
    }

    static boolean checkIfFieldIsFree(int v, int h) {
        return EMPTY.equals(board[v][h]);
    }

    static boolean checkWinner(String s) {
        if (hasWinningLine(SIZE, (v, h) -> s.equals(board[v][h]))) {
            return true;
        }
        if (hasWinningLine(SIZE, (h, v) -> s.equals(board[v][h]))) {
            return true;
        }
        if (hasWinningDiagonal(SIZE, i -> s.equals(board[i][i]))) {
            return true;
        }
        if (hasWinningDiagonal(SIZE, i -> s.equals(board[SIZE - 1 - i][i]))) {
            return true;
        }
        return false;
    }

    static boolean hasWinningLine(int size, BiFunction<Integer, Integer, Boolean> check) {
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

    static boolean hasWinningDiagonal(int size, Function<Integer, Boolean> check) {
        boolean hasDiagonale1 = true;
        for (int i = 0; i < size; i++) {
            if (!check.apply(i)) {
                hasDiagonale1 = false;
                break;
            }
        }
        return hasDiagonale1;
    }

    static void printBoard(String[][] matrix) {
        for (String[] array : matrix) {
            for (String s : array) {
                System.out.print("  " + s + "  ");
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





