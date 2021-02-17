package modules;

import java.util.ArrayList;

public class State {
    String bluePlayer; // bleu
    String redPlayer; // rouge
    String currentPlayer;
    String board[][];
    int size;

    // 25 pions au début
    int piecesOfBlue = 0;
    int piecesOfRed = 0;

    public State(String p1, String p2, int size) {
        this.board = new String[size][size];
        this.bluePlayer = p1;
        this.redPlayer = p2;
        this.currentPlayer = p1;

        this.size = size;
        this.board[0][0] = p1;
        this.board[size - 1][size - 1] = p1;
        this.board[size - 1][0] = p2;
        this.board[0][size - 1] = p2;
    }

    public void display() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == null)
                    System.out.print(".\t");
                if (this.board[i][j] == this.bluePlayer) {
                    System.out.print("▢\t");
                    this.piecesOfBlue++;
                }
                if (this.board[i][j] == this.redPlayer) {
                    System.out.print("✿\t");
                    this.piecesOfRed++;
                }
            }
            System.out.println();
        }
    }

    public void displayNumberOf() {
        System.out.println("Number of Blue: " + this.piecesOfBlue);
        System.out.println("Number of Red: " + this.piecesOfRed);
    }

    public void displayMove() {
        for (Move move : getMove(getCurrentPlayer()))
            System.out.println(move.end.toString());
    }

    public String getCurrentPlayer() {
        return this.currentPlayer;
    }

    public int getCurrentPieces() {
        if (this.currentPlayer.equals(this.bluePlayer))
            return this.piecesOfBlue;
        else
            return this.piecesOfRed;
    }

    public boolean isValid(Coordinate coord) {
        if (coord.x >= 0 && coord.y >= 0 && coord.x < this.size && coord.y < this.size
                && this.board[coord.x][coord.y] == null)
            return true;

        return false;
    }

    public ArrayList<Move> getMove(String player) {
        ArrayList<Move> positions = new ArrayList<>();
        ArrayList<Move> getMove = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == player) {
                    Coordinate start = new Coordinate(i, j);

                    // clone forward & backward positions
                    positions.add(new Move(start, new Coordinate(i + 1, j), 'C'));
                    positions.add(new Move(start, new Coordinate(i - 1, j), 'C'));

                    // clone diagonal positions
                    positions.add(new Move(start, new Coordinate(i + 1, j + 1), 'C'));
                    positions.add(new Move(start, new Coordinate(i + 1, j - 1), 'C'));
                    positions.add(new Move(start, new Coordinate(i - 1, j + 1), 'C'));
                    positions.add(new Move(start, new Coordinate(i - 1, j - 1), 'C'));

                    // clone sideways positions
                    positions.add(new Move(start, new Coordinate(i, j + 1), 'C'));
                    positions.add(new Move(start, new Coordinate(i, j - 1), 'C'));

                    // U: UP, D: DOWN, L: LEFT, R: RIGHT
                    positions.add(new Move(start, new Coordinate(i - 2, j), 'U'));
                    positions.add(new Move(start, new Coordinate(i + 2, j), 'D'));
                    positions.add(new Move(start, new Coordinate(i, j - 2), 'L'));
                    positions.add(new Move(start, new Coordinate(i, j + 2), 'R'));
                }
            }
        }

        for (Move move : positions) {
            if (isValid(move.end)) {
                getMove.add(move);
                // System.out.println(move.end.toString());
            }
        }

        return getMove;
    }

    public float getScore(String player) {
        int score = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == player)
                    score += getCurrentPieces() % (this.piecesOfBlue + this.piecesOfRed);
            }
        }
        return score;
    }

    public void changePlayer() {
        if (this.currentPlayer.equals(this.bluePlayer))
            this.currentPlayer = this.redPlayer;
        else
            this.currentPlayer = this.bluePlayer;
    }

    public void transform(Coordinate coord) {

        // forward & backward positions
        if (coord.x < this.size - 1 && this.board[coord.x + 1][coord.y] != this.currentPlayer
                && this.board[coord.x + 1][coord.y] != null)
            this.board[coord.x + 1][coord.y] = this.currentPlayer;

        if (coord.x > 0 && this.board[coord.x - 1][coord.y] != this.currentPlayer
                && this.board[coord.x - 1][coord.y] != null)
            this.board[coord.x - 1][coord.y] = this.currentPlayer;

        // diagonal positions
        if (coord.x < this.size - 1 && coord.y < this.size - 1
                && this.board[coord.x + 1][coord.y + 1] != this.currentPlayer
                && this.board[coord.x + 1][coord.y + 1] != null)
            this.board[coord.x + 1][coord.y + 1] = this.currentPlayer;

        if (coord.x < this.size - 1 && coord.y > 0 && this.board[coord.x + 1][coord.y - 1] != this.currentPlayer
                && this.board[coord.x + 1][coord.y - 1] != null)
            this.board[coord.x + 1][coord.y - 1] = this.currentPlayer;

        if (coord.x > 0 && coord.y < this.size - 1 && this.board[coord.x - 1][coord.y + 1] != this.currentPlayer
                && this.board[coord.x - 1][coord.y + 1] != null)
            this.board[coord.x - 1][coord.y + 1] = this.currentPlayer;

        if (coord.x > 0 && coord.y > 0 && this.board[coord.x - 1][coord.y - 1] != this.currentPlayer
                && this.board[coord.x - 1][coord.y - 1] != null)
            this.board[coord.x - 1][coord.y - 1] = this.currentPlayer;

        // sideways positions
        if (coord.y < this.size - 1 && this.board[coord.x][coord.y + 1] != this.currentPlayer
                && this.board[coord.x][coord.y + 1] != null)
            this.board[coord.x][coord.y + 1] = this.currentPlayer;

        if (coord.y > 0 && this.board[coord.x][coord.y - 1] != this.currentPlayer
                && this.board[coord.x][coord.y - 1] != null)
            this.board[coord.x][coord.y - 1] = this.currentPlayer;
    }

    public void play(Move move) {
        Coordinate start = move.start;
        Coordinate end = move.end;

        if (move.type == 'C') {
            this.board[end.x][end.y] = this.currentPlayer;
            transform(end);
        }

        if (move.type == 'U' && start.x > 0 && this.board[start.x - 1][start.y] != this.currentPlayer
                && this.board[start.x - 1][start.y] != null) {
            this.board[start.x][start.y] = null;
            this.board[end.x][end.y] = this.currentPlayer;
        }

        if (move.type == 'D' && start.y < this.size - 1 && this.board[start.x + 1][start.y] != this.currentPlayer
                && this.board[start.x + 1][start.y] != null) {
            this.board[start.x][start.y] = null;
            this.board[end.x][end.y] = this.currentPlayer;
        }

        if (move.type == 'L' && start.y > 0 && this.board[start.x][start.y - 1] != this.currentPlayer
                && this.board[start.x][start.y - 1] != null) {
            this.board[start.x][start.y] = null;
            this.board[end.x][end.y] = this.currentPlayer;
        }

        if (move.type == 'R' && start.y < this.size - 1 && this.board[start.x][start.y + 1] != this.currentPlayer
                && this.board[start.x][start.y + 1] != null) {
            this.board[start.x][start.y] = null;
            this.board[end.x][end.y] = this.currentPlayer;
        }

        changePlayer();
    }

    public boolean isOver() {

        // 1. un des joueurs ne dispose plus de pion
        // if (this.piecesOfBlue == 0)
        // return true;

        // if (this.piecesOfRed == 0)
        // return true;

        // 2. les deux joueurs doivent passer leur tour
        // 3. le plateau de jeu revient dans un état qui a déjà été joué
        if (getMove(this.currentPlayer).isEmpty())
            return true;

        return false;
    }

    public String getWinner() {
        if (getScore(this.bluePlayer) > getScore(this.redPlayer))
            return this.bluePlayer;
        else if (getScore(this.redPlayer) > getScore(this.bluePlayer))
            return this.redPlayer;
        else
            return "Tie!";
    }

}
