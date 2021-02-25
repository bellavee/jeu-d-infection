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
        String first = "Blue";
        String second = "Red";
        State game = new State(first, second, SIZE);

        AI ai = new AI("AI", 0, false);

        while (!game.isOver()) {
            String player = game.getCurrentPlayer();
            // Move move = randomPlayer(game.getMove(player));
            Move move = ai.getBestMove(game, 4);
            game.display();

            System.out.println("This is " + game.getCurrentPlayer() + "'s turn: " + move.type + " (no play -> pass)");
            System.out.println("Score of " + game.getCurrentPlayer() + " " + game.getScore(player));

            game.play(move);
        }

        // System.out.println("Winner is: " + game.getWinner());

    }
}
