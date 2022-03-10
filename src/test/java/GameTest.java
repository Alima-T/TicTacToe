import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @BeforeAll
    void setUp() {

        final String X = "X";
        final String O = "O";
        final String FREE = "_";
        final int SIZE = 3;

        final String GREETING = "Welcome to the game. What is your name? \n";
        final String WRONG_INPUT = "Wrong input! Please enter number from 0 to " + (SIZE - 1) + ": \n";
        final String FIELD_IS_TAKEN_MESSAGE = "Error! This field is already taken. Please try again.";
        final String INPUT_VERTICAL_MESSAGE = ", please enter number for vertical position from 0 to to " + (SIZE - 1) + ": \n";
        final String INPUT_HORIZONTAL_MESSAGE = ", please enter number for horizontal position: from 0 to to " + (SIZE - 1) + ": \n";
        final String WINNER_MESSAGE = "! Congratulations!!! You won!!!";


        final InputReader inputReader;
        final Printer printer;
        String[][] board = Game.createBoard(SIZE,SIZE);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testStart() {
    }

    @Test
    void testPrint() {
    }

    @Test
    void testInputIsCorrect() {
        System.out.println("testInputIsCorrect()");
        int i = 2;
        boolean expectedResult = false;
        boolean result = Game.inputIsCorrect(i);
        assertEquals(expectedResult, result);
    }

    @Test
    void testFieldIsFree() {

    }

    @Test
    void testCheckWinner() {
    }

    @Test
    void testPrintBoard() {
    }

    @Test
    void testFillBoard() {
    }



    @Test
    void testReadInput() {
    }
}