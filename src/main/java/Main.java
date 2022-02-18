import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        new Game(scanner::next, System.out::print, 3).start();
    }


}





