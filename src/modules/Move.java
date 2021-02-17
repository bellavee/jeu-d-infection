package modules;

public class Move {
    Coordinate start;
    Coordinate end;
    char type;

    public Move(Coordinate start, Coordinate end, char move) {
        this.start = start;
        this.end = end;
        this.type = move;
    }

    public Coordinate getStart() {
        return this.start;
    }

    public Coordinate getEnd() {
        return this.end;
    }

    public char getAction() {
        return this.type;
    }

    @Override
    public String toString() {
        return "(" + this.start.toString() + ", " + this.end.toString() + ", " + this.type + ")";
    }

}
