package modules;

import java.util.ArrayList;

public class State {
    private String bluePlayer; // bleu
    private String redPlayer; // rouge
    private String currentPlayer;
    private String tab[][] = new String[7][7];

    // 25 pions au début
    int nbOfBlue = 25;
    int nbOfRed = 25;

    float scoreOfBlue = 0;
    float scoreOfRed = 0;

    public State(String p1, String p2) {
        this.bluePlayer = p1;
        this.redPlayer = p2;
        this.currentPlayer = p1;
        this.tab[0][6] = p1;
        this.tab[6][0] = p1;
        this.tab[0][0] = p2;
        this.tab[6][6] = p2;
    }

    public void display() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (this.tab[i][j] == null)
                    System.out.print(".\t");
                if (this.tab[i][j] == this.bluePlayer)
                    System.out.print("〇\t");
                if (this.tab[i][j] == this.redPlayer)
                    System.out.print("⬤\t");
            }
            System.out.println();
        }
    }

    public void displayNumberOf() {
        System.out.println("Number of Blue: " + this.nbOfBlue);
        System.out.println("Number of Red: " + this.nbOfRed);
    }

    public void displayScore() {
        System.out.println("Blue's score: " + this.scoreOfBlue);
        System.out.println("Red's score: " + this.scoreOfRed);
    }

    public void displayMove() {
        for (int move : getMove())
            System.out.println(moveToString(move));
    }

    public String getCurrentPlayer() {
        return this.currentPlayer;
    }

    public String moveToString(int index) {
        return index + " = " + "(" + index / 7 + ", " + index % 7 + ")";
    }

    public ArrayList<Integer> getMove() {
        ArrayList<Integer> getMove = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (this.tab[i][j] == null)
                    getMove.add(i * 7 + j);
            }
        }
        return getMove;
    }

    // pas necessaire maintenant
    public boolean isValid(int index) {
        ArrayList<Integer> move = getMove();
        return move.contains(index);
    }

    // pas marche
    public float getScore(String player) {
        if (player.equals(this.bluePlayer))
            return this.scoreOfBlue += this.nbOfBlue / (this.nbOfBlue + this.nbOfRed);
        else
            return this.scoreOfRed += this.nbOfRed / (this.nbOfBlue + this.nbOfRed);
    }

    public void changePlayer() {
        if (this.currentPlayer.equals(this.bluePlayer))
            this.currentPlayer = this.redPlayer;
        else
            this.currentPlayer = this.bluePlayer;
    }

    public void play(int index) {
        int row = index / 7;
        int col = index % 7;

        if (this.currentPlayer.equals(this.bluePlayer)) {
            this.tab[row][col] = this.bluePlayer;
            this.nbOfBlue -= 1;

        } else {
            this.tab[row][col] = this.redPlayer;
            this.nbOfRed -= 1;
        }

        changePlayer();

    }

    public boolean isOver() {

        // 1. un des joueurs ne dispose plus de pion
        if (this.nbOfBlue == 0)
            return true;

        if (this.nbOfRed == 0)
            return true;

        // 2. les deux joueurs doivent passer leur tour

        // 3. le plateau de jeu revient dans un état qui a déjà été joué
        if (getMove().isEmpty())
            return true;

        return false;
    }

    // public int getBestMove() {
    // int bestVal = -9999;
    // int bestMove = 0;

    // for (int move : getMove()) {
    // if (bestVal == -9999 || )
    // }

    // return bestMove;
    // }

}
