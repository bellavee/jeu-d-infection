package modules;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /* -------------------- VARIABLE -------------------- */

        Scanner scanner = new Scanner(System.in);
        Player blue;
        Player red;

        int depthBlue;
        int depthRed;
        int option;

        /* -------------------- SETUP BOARD SIZE -------------------- */

        System.out.print("Enter the square board size: ");
        int size = scanner.nextInt();

        /* -------------------- SETUP PLAYER -------------------- */

        System.out.print("Enter the depth of Blue Player (x): ");
        depthBlue = scanner.nextInt();

        System.out.print("Enter the depth of Red Player (o): ");
        depthRed = scanner.nextInt();

        System.out.print("1-minmax or 2-alphabeta: ");
        do {

            option = scanner.nextInt();
            if (option == 1) {
                blue = new Player(depthBlue, true);
                red = new Player(depthRed, true);
            }

            else {
                blue = new Player(depthBlue, false);
                red = new Player(depthRed, false);
            }

        } while (option != 1 && option != 2);

        /* true: minmax false: alphabeta */

        /* -------------------- START GAME -------------------- */

        System.out.println("\n---------- START ----------\n");
        State game = new State(blue, red, size);

        game.display();
        System.out.println("Blue - " + blue.toString() + " go first");
        System.out.println("red - " + red.toString() + " go after");
        System.out.println("\n-------------------------\n");

        while (!game.isOver()) {
            Player player = game.getCurrentPlayer();
            Move move = player.getBestMove(game);
            System.out.println("This is " + game.getCurrentPlayer() + "'s turn, action: " + move.getType());
            game.play(move);
            game.display();
            game.displayScore();
            System.out.println("\n-------------------------\n");

        }

        /* -------------------- END GAME -------------------- */

        System.out.println("Winner is: " + game.getWinner() + "\n");
        System.out.println("---------- DETAIL ----------");
        game.displayScore();
        System.out.println(blue.toString() + " has visited: " + blue.getNodeCount() + " nodes.");
        System.out.println(red.toString() + " has visited: " + red.getNodeCount() + " nodes.");
        System.out.println("Total nodes: " + (blue.getNodeCount() + red.getNodeCount()) + "\n");

        scanner.close();
    }
}
