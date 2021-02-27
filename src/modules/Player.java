package modules;

public interface Player {

    public Move getBestMove(State state);

    public int getNodeCount();

}
