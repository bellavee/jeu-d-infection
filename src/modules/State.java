package modules;

import java.util.ArrayList;

public class State {
    String bluePlayer; // bleu
    String redPlayer; // rouge
    String currentPlayer;
    String tab[][];
    int size;

    // 25 pions au début
    int nbOfBlue = 22;
    int nbOfRed = 22;

    float scoreOfBlue = 0;
    float scoreOfRed = 0;

    public State(String p1, String p2, int size) {
        this.tab = new String[size][size];
        this.bluePlayer = p1;
        this.redPlayer = p2;
        this.currentPlayer = p1;
        this.size = size;
        this.tab[0][0] = p1;
        this.tab[size - 1][size - 1] = p1;
        this.tab[size - 1][0] = p2;
        this.tab[0][size - 1] = p2;
    }

    public void display() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
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

    public String moveToString(int index) {
        return index + " = " + "(" + index / 7 + ", " + index % 7 + ")";
    }

    public void displayMove() {
        for (int move : getMove(getCurrentPlayer()))
            System.out.println(moveToString(move));
    }

    public String getCurrentPlayer() {
        return this.currentPlayer;
    }

    // public int indexToRow(int index) {
    // return index / 7;
    // }

    // public int indexToCol(int index) {
    // return index % 7;
    // }

    // public boolean isBlue(int index) {
    // if (this.tab[indexToRow(index)][indexToCol(index)] == this.bluePlayer)
    // return true;
    // return false;
    // }

    // public boolean isRed(int index) {
    // if (this.tab[indexToRow(index)][indexToCol(index)] == this.redPlayer)
    // return true;
    // return false;
    // }

    public int coorToInt(int x, int y) {
        return x * this.size + y;
    }

    public boolean isValid(int index) {
        if (index > 0 && index < this.size * this.size) {
            if (this.tab[index / 7][index % 7] == null)
                return true;
        }

        return false;
    }

    public ArrayList<Integer> getMove(String player) {
        ArrayList<Integer> positions = new ArrayList<>();
        ArrayList<Integer> getMove = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (this.currentPlayer.equals(player) && this.tab[i][j] == player) {
                    // possible forward & backward positions
                    positions.add(coorToInt(i + 1, j));
                    positions.add(coorToInt(i - 1, j));

                    // possible diagonal positions
                    positions.add(coorToInt(i + 1, j + 1));
                    positions.add(coorToInt(i + 1, j - 1));
                    positions.add(coorToInt(i - 1, j + 1));
                    positions.add(coorToInt(i - 1, j - 1));

                    // possible sideways positions
                    positions.add(coorToInt(i, j + 1));
                    positions.add(coorToInt(i, j - 1));
                }
            }
        }

        for (int move : positions) {
            if (isValid(move)) {
                getMove.add(move);
            }
        }
        return getMove;
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
        if (getMove(getCurrentPlayer()).isEmpty())
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
