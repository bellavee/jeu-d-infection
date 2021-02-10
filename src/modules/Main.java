package modules;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String first = "blue";
        String second = "red";
        State play = new State(first, second);
        play.display();

        Scanner scan = new Scanner(System.in);

        while (!play.isOver()) {
            int move = scan.nextInt();

            play.execute(move);
            play.updateScore();
            play.getMove();

            play.display();
            play.displayNumberOf();
            play.displayScore();
        }
        scan.close();
    }
}
