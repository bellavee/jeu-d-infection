package modules;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static Move randomPlayer(ArrayList<Move> getMove) {
        Random rand = new Random();
        return getMove.get(rand.nextInt(getMove.size()));
    }

    public static void main(String[] args) {
        int SIZE = 7;
        String first = "blue";
        String second = "red";
        State game = new State(first, second, SIZE);

        while (!game.isOver()) {
            String player = game.getCurrentPlayer();
            Move move = randomPlayer(game.getMove(player));
            System.out.println("This is " + game.getCurrentPlayer() + "'s turn: " + move.type + " (no play -> pass)");

            game.play(move);
            game.display();

            // System.out.println("Score of " + game.getCurrentPlayer() + " " +
            // game.getScore(player));

        }
        // System.out.println("Winner is: " + game.getWinner());

    }
}
