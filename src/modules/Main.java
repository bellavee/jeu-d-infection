package modules;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /* -------------------- VARIABLE -------------------- */

        Scanner scanner = new Scanner(System.in);
        Player player1;
        Player player2;

        int depth;
        int option;

        /* -------------------- BOARD SIZE -------------------- */

        System.out.print("Enter the square board size: ");
        int size = scanner.nextInt();

        /* -------------------- PLAYER 1 -------------------- */

        System.out.print("Enter the depth of player 1: ");
        depth = scanner.nextInt();

        System.out.print("1-MinMax or 2-AlphaBeta: ");
        do {
            option = scanner.nextInt();
            if (option == 1)
                player1 = new AI(depth, true);
            else
                player1 = new AI(depth, false);
        } while (option != 1 && option != 2);

        /* true: minmax false: alphabeta */

        /* -------------------- PLAYER 2 -------------------- */

        System.out.print("Enter the depth of player 2: ");
        depth = scanner.nextInt();

        System.out.print("1-MinMax or 2-AlphaBeta: ");
        do {
            option = scanner.nextInt();
            if (option == 1)
                player2 = new AI(depth, true);
            else
                player2 = new AI(depth, false);
        } while (option != 1 && option != 2);

        /* true: minmax false: alphabeta */

        /* -------------------- START GAME -------------------- */

        System.out.println("\n---------- START ----------\n");
        State game = new State(player1, player2, size);

        game.display();
        System.out.println("\n-------------------------\n");

        while (!game.isOver()) {
            Player player = game.getCurrentPlayer();
            Move move = player.getBestMove(game);
            System.out.println("This is " + game.getCurrentPlayer() + "'s turn, action: " + move.getAction());
            game.play(move);
            game.display();
            game.displayScore();
            System.out.println("\n-------------------------\n");

        }

        /* -------------------- END GAME -------------------- */

        System.out.println("Winner is: " + game.getWinner() + "\n");
        System.out.println("---------- DETAIL ----------");
        game.displayScore();
        System.out.println(player1.toString() + " has visited: " + player1.getNodeCount() + " nodes.");
        System.out.println(player2.toString() + " has visited: " + player2.getNodeCount() + " nodes.");
        System.out.println("Total nodes: " + (player1.getNodeCount() + player2.getNodeCount()) + "\n");

        scanner.close();
    }
}
