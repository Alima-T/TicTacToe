import java.util.Scanner;

public class MainGame {
    final static String GREETING = "Welcome to the game. What is your name?";
    final static String INPUT_MESSAGE = ", please enter numbers from 0 to 2"+" to choose the coordinates of the field horizontally and vertically: ";

    final static String ERROR_MESSAGE = "Please enter the correct coordinates: ";
    final static String FIELD_IS_TAKEN_MESSAGE = "This field is already taken. Please try again: ";
    final static String WINNER_MESSAGE = "! Congratulations! You won!";
    final static String NEW_START_MESSAGE = "To start a new game write \"Y\" and press enter";
    final static String X = " X ";
    final static String O = " O ";
    final static String FREE = " _ ";
    public static String[][] board = new String[3][3];
    public static int inputVertical;
    public static int inputHorizontal;

    public static void main (String[] args) {

        fillMatrix(board);
        printBoard();

        Scanner player1 = new Scanner(System.in);
        Scanner player2 = new Scanner(System.in);

        print(GREETING);

        String namePlayer1 = player1.next();
        String namePlayer2 = player2.next();

        while (true) {
            print(namePlayer1+INPUT_MESSAGE);
            inputVertical = player1.nextInt();
            inputHorizontal = player1.nextInt();

            if (checkInput(inputVertical, inputHorizontal)) {
                board[inputVertical][inputHorizontal] = X;
                printBoard();
            }
            if (checkWinner(X)) {
                print(namePlayer1+WINNER_MESSAGE);
                break;
            }

            print(namePlayer2+INPUT_MESSAGE);
            inputVertical = player2.nextInt();
            inputHorizontal = player2.nextInt();

            if (checkInput(inputVertical, inputHorizontal)) {
                board[inputVertical][inputHorizontal] = O;
                printBoard();
            }
            if (checkWinner(O)) {
                print(namePlayer2+WINNER_MESSAGE);
                break;
            }
        }
    }

    static void fillMatrix(String[][] matrix){
        for (int i=0; i<matrix.length;i++)
            for (int j=0; j<matrix.length;j++)
                matrix[i][j]= FREE;
    }


    static void print (String s) {
        System.out.println(s);
    }

    static boolean checkInput (int v, int h) {
        if (!checkInputNums(v, h)) {
            System.out.println(ERROR_MESSAGE);
        } else {
            if (!checkIfFieldIsFree()) {
                System.out.println(FIELD_IS_TAKEN_MESSAGE);
            }
        }
        return true;
    }

    static boolean checkInputNums (int v, int h) {
        return (v>=0 & v<=2) || (h>=0 & h<=2);
    }

    static boolean checkIfFieldIsFree () {
        for (int v = 0; v<board.length; v++) {
            for (int h = 0; h<board.length; h++) {
                if (board[v][h] == FREE) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean checkWinner (String s) {
        for (int i = 0; i<board.length; i++) {
            if ((board[i][0] == s & board[i][1] == s & board[i][2] == s) || (board[0][i] == s & board[1][i] == s & board[2][i] == s)) {
                return true;
            }
            if ((board[0][0] == s & board[1][1] == s & board[2][2] == s) || (board[0][2] == s & board[1][1] == s & board[2][0] == s)) {
                return true;
            }
        }
        return false;
    }

    static void printBoard () {
        for (String[] array: board) {
            for (String s: array) {
                System.out.print(" "+ s +" ");
            }
            System.out.println("\n");
        }
    }
}





