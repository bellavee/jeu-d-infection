package modules;

import java.util.ArrayList;

public class AI implements Player {
    private int depth;
    private boolean option;

    protected int nodeCount = 0;

    public AI(int depth, boolean option) {
        this.depth = depth;
        this.option = option;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    @Override
    public String toString() {
        return "P" + this.hashCode();
    }

    /* ---------- FUNCTION ---------- */

    public float minmax(State state, int depth) {
        float b;
        float m;
        ArrayList<Move> moves = state.getMove();
        nodeCount++;

        if (depth == 0 || state.isOver())
            return state.getScore(state.getCurrentPlayer());

        else {

            /* Maximizing player */
            if (state.getCurrentPlayer() == state.getWinner()) {
                b = Float.NEGATIVE_INFINITY;
                for (Move move : moves) {
                    State nextState = state.copyState();
                    nextState.play(move);
                    m = minmax(nextState, depth - 1);
                    if (b < m)
                        b = m;
                }
            }

            /* Minimizing player */
            else {
                b = Float.POSITIVE_INFINITY;
                for (Move move : moves) {
                    State nextState = state.copyState();
                    nextState.play(move);
                    m = minmax(nextState, depth - 1);
                    if (b > m)
                        b = m;
                }
            }

            return b;
        }
    }

    public float alphabeta(State state, int depth, float alpha, float beta) {
        ArrayList<Move> moves = state.getMove();
        nodeCount++;

        if (depth == 0 || state.isOver())
            return state.getScore(state.getCurrentPlayer());

        else {

            /* Maximizing player */
            if (state.getWinner() == state.getCurrentPlayer()) {
                for (Move move : moves) {
                    State nextState = state.copyState();
                    nextState.play(move);
                    alpha = Float.max(alpha, alphabeta(nextState, depth - 1, alpha, beta));
                    if (alpha >= beta)
                        return alpha;

                }
                return alpha;
            }

            /* Minimizing player */
            else {
                for (Move move : moves) {
                    State nextState = state.copyState();
                    nextState.play(move);
                    beta = Float.min(beta, alphabeta(nextState, depth - 1, alpha, beta));

                    if (alpha >= beta)
                        return beta;
                }
                return beta;
            }
        }
    }

    public Move getBestMove(State state) {
        float value;
        float bestValue = Float.NEGATIVE_INFINITY;
        Move bestMove = new Move();
        ArrayList<Move> moves = state.getMove();

        for (Move move : moves) {
            State nextState = state.copyState();
            nextState.play(move);

            if (option)
                value = minmax(nextState, depth - 1);
            else
                value = alphabeta(nextState, depth - 1, 1, 0);

            if (value > bestValue) {
                bestValue = value;
                bestMove = move;
            }
        }

        return bestMove;
    }
}
