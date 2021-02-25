package modules;

public class Move {
    protected Coordinate start;
    protected Coordinate end;
    protected char type;

    public Move(Coordinate start, Coordinate end, char type) {
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public Move() {
        this.start = new Coordinate(0, 0);
        this.end = new Coordinate(0, 0);
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
