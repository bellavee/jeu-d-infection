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

        game.display();

        while (!game.isOver()) {
            String player = game.getCurrentPlayer();
            AI ai = new AI(player);

            // Move move = randomPlayer(game.getMove(player));

            /* true: minmax false: alphabeta */
            Move move = ai.getBestMove(game, 4, true);

            System.out.println("\nThis is " + game.getCurrentPlayer() + "'s turn, action: " + move.getAction());

            game.play(move);
            game.display();
            game.displayScore();
        }

        System.out.println("Winner is: " + game.getWinner());

    }
}
