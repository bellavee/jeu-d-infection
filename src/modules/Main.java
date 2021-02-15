package modules;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int randomPlayer(ArrayList<Integer> getMove) {
        Random rand = new Random();
        int move = getMove.get(rand.nextInt(getMove.size()));
        return move;
    }

    public static void main(String[] args) {
        int SIZE = 7;
        String first = "blue";
        String second = "red";
        State game = new State(first, second, SIZE);
        game.display();

        Scanner scan = new Scanner(System.in);

        while (!game.isOver()) {
            String player = game.getCurrentPlayer();
            int move = randomPlayer(game.getMove(player));
            game.play(move);
            game.getMove(player);
            game.displayMove();
            game.display();
            game.displayNumberOf();
            game.displayScore();
        }
        scan.close();
    }
}
