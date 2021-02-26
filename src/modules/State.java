package modules;

import java.util.ArrayList;

public class State {
    private String bluePlayer;
    private String redPlayer;
    private String currentPlayer;
    private int size;
    private String board[][];

    int scoreBlue = 0;
    int scoreRed = 0;

    public State(String p1, String p2, int size) {
        this.board = new String[size][size];
        this.bluePlayer = p1;
        this.redPlayer = p2;
        this.currentPlayer = p1;
        this.size = size;
        this.board[0][0] = this.bluePlayer;
        this.board[size - 1][size - 1] = this.bluePlayer;
        this.board[size - 1][0] = this.redPlayer;
        this.board[0][size - 1] = this.redPlayer;
    }

    /* -------------------- INSTALL -------------------- */

    public State copyState() {
        State copyState = new State(this.bluePlayer, this.redPlayer, this.size);
        copyState.board = new String[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                copyState.board[i][j] = this.board[i][j];
            }
        }

        copyState.currentPlayer = this.currentPlayer;
        return copyState;
    }

    /* -------------------- DISPLAY -------------------- */

    public void display() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == null)
                    System.out.print(".\t");

                if (this.board[i][j] == this.bluePlayer)
                    System.out.print("▢\t");

                if (this.board[i][j] == this.redPlayer)
                    System.out.print("✿\t");
            }
            System.out.println();
        }
    }

    public void displayScore() {
        System.out.println("Score of " + this.bluePlayer + " " + getScore(this.bluePlayer));
        System.out.println("Score of " + this.redPlayer + " " + getScore(this.redPlayer));
    }

    /* -------------------- GET LOCAL VARIABLE -------------------- */

    public String getCurrentPlayer() {
        return this.currentPlayer;
    }

    /* -------------------- CONDITION -------------------- */

    public boolean isValidOnBoard(int x, int y) {
        return x >= 0 && y >= 0 && x <= this.size - 1 && y <= this.size - 1;
    }

    public boolean isDifferentColor(int x, int y) {
        return this.board[x][y] != this.currentPlayer && this.board[x][y] != null;
    }

    public boolean isValidToPlace(Coordinate coord) {
        return coord.x >= 0 && coord.y >= 0 && coord.x < this.size && coord.y < this.size
                && this.board[coord.x][coord.y] == null;
    }

    /* ---------- FUNCTION ---------- */

    public ArrayList<Move> getMove(String player) {
        ArrayList<Move> positions = new ArrayList<>();
        ArrayList<Move> getMove = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == player) {
                    Coordinate start = new Coordinate(i, j);

                    // Clone forward & backward positions
                    positions.add(new Move(start, new Coordinate(i + 1, j), 'C'));
                    positions.add(new Move(start, new Coordinate(i - 1, j), 'C'));

                    // Clone diagonal positions
                    positions.add(new Move(start, new Coordinate(i + 1, j + 1), 'C'));
                    positions.add(new Move(start, new Coordinate(i + 1, j - 1), 'C'));
                    positions.add(new Move(start, new Coordinate(i - 1, j + 1), 'C'));
                    positions.add(new Move(start, new Coordinate(i - 1, j - 1), 'C'));

                    // Clone sideways positions
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
            if (isValidToPlace(move.end))
                getMove.add(move);
        }

        /* Move to pass */
        // getMove.add(new Move(new Coordinate(0, 0), new Coordinate(0, 0), 'P'));

        return getMove;
    }

    public float getScore(String player) {
        float score = 0;

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == this.bluePlayer)
                    this.scoreBlue++;
                if (this.board[i][j] == this.redPlayer)
                    this.scoreRed++;
            }
        }

        if (player.equals(this.bluePlayer))
            score += (float) this.scoreBlue / (this.scoreBlue + this.scoreRed);
        else
            score += (float) this.scoreRed / (this.scoreBlue + this.scoreRed);

        return score;

    }

    public void changePlayer() {
        if (this.currentPlayer.equals(this.bluePlayer))
            this.currentPlayer = this.redPlayer;
        else
            this.currentPlayer = this.bluePlayer;
    }

    public void infection(Coordinate coord) {
        // forward & backward positions
        if (isValidOnBoard(coord.x + 1, coord.y) && isDifferentColor(coord.x + 1, coord.y))
            this.board[coord.x + 1][coord.y] = this.currentPlayer;
        if (isValidOnBoard(coord.x - 1, coord.y) && isDifferentColor(coord.x - 1, coord.y))
            this.board[coord.x - 1][coord.y] = this.currentPlayer;

        // diagonal positions
        if (isValidOnBoard(coord.x + 1, coord.y + 1) && isDifferentColor(coord.x + 1, coord.y + 1))
            this.board[coord.x + 1][coord.y + 1] = this.currentPlayer;
        if (isValidOnBoard(coord.x + 1, coord.y - 1) && isDifferentColor(coord.x + 1, coord.y - 1))
            this.board[coord.x + 1][coord.y - 1] = this.currentPlayer;
        if (isValidOnBoard(coord.x - 1, coord.y + 1) && isDifferentColor(coord.x - 1, coord.y + 1))
            this.board[coord.x - 1][coord.y + 1] = this.currentPlayer;
        if (isValidOnBoard(coord.x - 1, coord.y - 1) && isDifferentColor(coord.x - 1, coord.y - 1))
            this.board[coord.x - 1][coord.y - 1] = this.currentPlayer;

        // sideways positions
        if (isValidOnBoard(coord.x, coord.y + 1) && isDifferentColor(coord.x, coord.y + 1))
            this.board[coord.x][coord.y + 1] = this.currentPlayer;
        if (isValidOnBoard(coord.x, coord.y - 1) && isDifferentColor(coord.x, coord.y - 1))
            this.board[coord.x][coord.y - 1] = this.currentPlayer;
    }

    public State play(Move move) {
        State newState = new State(this.bluePlayer, this.redPlayer, this.size);
        Coordinate start = move.start;
        Coordinate end = move.end;

        if (move.type == 'C') {
            this.board[end.x][end.y] = this.currentPlayer;
            infection(end);
        }

        if (move.type == 'U' && isValidOnBoard(start.x - 1, start.y) && isDifferentColor(start.x - 1, start.y)) {
            this.board[start.x][start.y] = null;
            this.board[end.x][end.y] = this.currentPlayer;
        }

        if (move.type == 'D' && isValidOnBoard(start.x + 1, start.y) && isDifferentColor(start.x + 1, start.y)) {
            this.board[start.x][start.y] = null;
            this.board[end.x][end.y] = this.currentPlayer;
        }

        if (move.type == 'L' && isValidOnBoard(start.x, start.y - 1) && isDifferentColor(start.x, start.y - 1)) {
            this.board[start.x][start.y] = null;
            this.board[end.x][end.y] = this.currentPlayer;
        }

        if (move.type == 'R' && isValidOnBoard(start.x, start.y + 1) && isDifferentColor(start.x, start.y + 1)) {
            this.board[start.x][start.y] = null;
            this.board[end.x][end.y] = this.currentPlayer;
        }

        changePlayer();
        newState.copyState();

        return newState;

    }

    public boolean isFull() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] != null)
                    return false;
            }
        }
        return true;
    }

    public boolean isOver() {

        // 1. un des joueurs ne dispose plus de pion
        // 2. les deux joueurs doivent passer leur tour
        // 3. le plateau de jeu revient dans un état qui a déjà été joué
        if (getMove(this.currentPlayer).isEmpty())
            return true;

        if (isFull())
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
