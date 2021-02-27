package modules;

import java.util.ArrayList;

public class State {
    private Player firstPlayer;
    private Player secondPlayer;
    private Player currentPlayer;
    private int size;
    private Player board[][];

    int scoreFirst = 0;
    int scoreSecond = 0;

    public State(Player p1, Player p2, int size) {
        this.board = new Player[size][size];
        this.firstPlayer = p1;
        this.secondPlayer = p2;
        this.currentPlayer = p1;
        this.size = size;
        this.board[0][0] = this.firstPlayer;
        this.board[size - 1][size - 1] = this.firstPlayer;
        this.board[size - 1][0] = this.secondPlayer;
        this.board[0][size - 1] = this.secondPlayer;
    }

    /* -------------------- INSTALL -------------------- */

    public State copyState() {
        State copyState = new State(this.firstPlayer, this.secondPlayer, this.size);
        copyState.board = new Player[this.size][this.size];
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

                if (this.board[i][j] == this.firstPlayer)
                    System.out.print("▢\t");

                if (this.board[i][j] == this.secondPlayer)
                    System.out.print("✿\t");
            }
            System.out.println();
        }
    }

    public void displayScore() {
        System.out.println("Score of " + this.firstPlayer + " " + getScore(this.firstPlayer));
        System.out.println("Score of " + this.secondPlayer + " " + getScore(this.secondPlayer));
    }

    /* -------------------- GET LOCAL VARIABLE -------------------- */

    public Player getCurrentPlayer() {
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

    public ArrayList<Move> getMove() {
        ArrayList<Move> positions = new ArrayList<>();
        ArrayList<Move> getMove = new ArrayList<>();

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == this.currentPlayer) {
                    Coordinate start = new Coordinate(i, j);

                    /* Clone forward & backward positions */
                    positions.add(new Move(start, new Coordinate(i + 1, j), 'C'));
                    positions.add(new Move(start, new Coordinate(i - 1, j), 'C'));

                    /* Clone diagonal positions */
                    positions.add(new Move(start, new Coordinate(i + 1, j + 1), 'C'));
                    positions.add(new Move(start, new Coordinate(i + 1, j - 1), 'C'));
                    positions.add(new Move(start, new Coordinate(i - 1, j + 1), 'C'));
                    positions.add(new Move(start, new Coordinate(i - 1, j - 1), 'C'));

                    /* Clone sideways positions */
                    positions.add(new Move(start, new Coordinate(i, j + 1), 'C'));
                    positions.add(new Move(start, new Coordinate(i, j - 1), 'C'));

                    /* Shifting: U - UP, D - DOWN, L - LEFT, R - RIGHT */
                    if (isValidOnBoard(start.x - 1, start.y) && isDifferentColor(start.x - 1, start.y))
                        positions.add(new Move(start, new Coordinate(i - 2, j), 'U'));

                    if (isValidOnBoard(start.x + 1, start.y) && isDifferentColor(start.x + 1, start.y))
                        positions.add(new Move(start, new Coordinate(i + 2, j), 'D'));

                    if (isValidOnBoard(start.x, start.y - 1) && isDifferentColor(start.x, start.y - 1))
                        positions.add(new Move(start, new Coordinate(i, j - 2), 'L'));

                    if (isValidOnBoard(start.x, start.y + 1) && isDifferentColor(start.x, start.y + 1))
                        positions.add(new Move(start, new Coordinate(i, j + 2), 'R'));
                }
            }
        }

        for (Move move : positions) {
            if (isValidToPlace(move.end))
                getMove.add(move);
        }

        return getMove;
    }

    public float getScore(Player player) {
        float score = 0;

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == this.firstPlayer)
                    this.scoreFirst++;
                if (this.board[i][j] == this.secondPlayer)
                    this.scoreSecond++;
            }
        }

        if (player.equals(this.firstPlayer))
            score += (float) this.scoreFirst / (this.scoreFirst + this.scoreSecond);
        else
            score += (float) this.scoreSecond / (this.scoreFirst + this.scoreSecond);

        return score;

    }

    public void changePlayer() {
        if (this.currentPlayer.equals(this.firstPlayer))
            this.currentPlayer = this.secondPlayer;
        else
            this.currentPlayer = this.firstPlayer;
    }

    public void infection(Coordinate coord) {
        /* forward & backward positions */
        if (isValidOnBoard(coord.x + 1, coord.y) && isDifferentColor(coord.x + 1, coord.y))
            this.board[coord.x + 1][coord.y] = this.currentPlayer;
        if (isValidOnBoard(coord.x - 1, coord.y) && isDifferentColor(coord.x - 1, coord.y))
            this.board[coord.x - 1][coord.y] = this.currentPlayer;

        /* diagonal positions */
        if (isValidOnBoard(coord.x + 1, coord.y + 1) && isDifferentColor(coord.x + 1, coord.y + 1))
            this.board[coord.x + 1][coord.y + 1] = this.currentPlayer;
        if (isValidOnBoard(coord.x + 1, coord.y - 1) && isDifferentColor(coord.x + 1, coord.y - 1))
            this.board[coord.x + 1][coord.y - 1] = this.currentPlayer;
        if (isValidOnBoard(coord.x - 1, coord.y + 1) && isDifferentColor(coord.x - 1, coord.y + 1))
            this.board[coord.x - 1][coord.y + 1] = this.currentPlayer;
        if (isValidOnBoard(coord.x - 1, coord.y - 1) && isDifferentColor(coord.x - 1, coord.y - 1))
            this.board[coord.x - 1][coord.y - 1] = this.currentPlayer;

        /* sideways positions */
        if (isValidOnBoard(coord.x, coord.y + 1) && isDifferentColor(coord.x, coord.y + 1))
            this.board[coord.x][coord.y + 1] = this.currentPlayer;
        if (isValidOnBoard(coord.x, coord.y - 1) && isDifferentColor(coord.x, coord.y - 1))
            this.board[coord.x][coord.y - 1] = this.currentPlayer;
    }

    public void play(Move move) {
        Coordinate start = move.start;
        Coordinate end = move.end;

        if (move.type == 'C') {
            this.board[end.x][end.y] = this.currentPlayer;
            infection(end);
        }

        else {
            this.board[start.x][start.y] = null;
            this.board[end.x][end.y] = this.currentPlayer;
        }

        changePlayer();

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

        /*
         * 1. un des joueurs ne dispose plus de pion. 2. les deux joueurs doivent passer
         * leur tour. 3. le plateau de jeu revient dans un état qui a déjà été joué.
         */

        if (getMove().isEmpty())
            return true;

        if (isFull())
            return true;

        return false;
    }

    public Player getWinner() {
        if (getScore(this.firstPlayer) > getScore(this.secondPlayer))
            return this.firstPlayer;
        else if (getScore(this.secondPlayer) > getScore(this.firstPlayer))
            return this.secondPlayer;
        else
            return null;
    }
}
