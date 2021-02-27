package modules;

public class Main {

    public static void main(String[] args) {
        int SIZE = 7;

        /* true: minmax false: alphabeta */
        Player ai1 = new AI(3, true);
        Player ai2 = new AI(3, false);

        State game = new State(ai1, ai2, SIZE);

        game.display();

        while (!game.isOver()) {
            Player player = game.getCurrentPlayer();
            Move move = player.getBestMove(game);

            System.out.println("\nThis is " + game.getCurrentPlayer() + "'s turn, action: " + move.getAction());

            game.play(move);
            game.display();
            game.displayScore();
        }

        System.out.println("Winner is: " + game.getWinner());

    }
}
