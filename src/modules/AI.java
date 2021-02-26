package modules;

import java.util.ArrayList;

public class AI {
    String player;

    public AI(String player) {
        this.player = player;
    }

    public String getPlayer() {
        return this.player;
    }

    /* ---------- FUNCTION ---------- */

    public float minmax(State state, int depth) {
        float b;
        float m;
        ArrayList<Move> moves = state.getMove(this.player);
        if (depth == 0 || state.isOver())
            return state.getScore(this.player);

        else {

            /* Maximizing player */
            if (state.getCurrentPlayer().equals(this.player)) {
                b = Float.NEGATIVE_INFINITY;
                for (Move move : moves) {
                    State nextState = state.play(move);
                    m = minmax(nextState, depth - 1);
                    if (b < m)
                        b = m;
                }
            }

            /* Minimizing player */
            else {
                b = Float.POSITIVE_INFINITY;
                for (Move move : moves) {
                    State nextState = state.play(move);
                    m = minmax(nextState, depth - 1);
                    if (b > m)
                        b = m;
                }
            }

            return b;
        }
    }

    public float alphabeta(State state, int depth, float alpha, float beta) {
        ArrayList<Move> moves = state.getMove(this.player);
        if (depth == 0 || state.isOver())
            return state.getScore(this.player);

        else {
            /* Maximizing player */
            if (state.getCurrentPlayer().equals(this.player)) {
                for (Move move : moves) {
                    State nextState = state.play(move);
                    alpha = Float.max(alpha, alphabeta(nextState, depth - 1, alpha, beta));
                    if (alpha >= beta)
                        return alpha;
                }

                return alpha;
            }

            /* Minimizing player */
            else {
                for (Move move : moves) {
                    State nextState = state.play(move);
                    beta = Float.min(beta, alphabeta(nextState, depth - 1, alpha, beta));

                    if (alpha >= beta)
                        return beta;
                }

                return beta;
            }
        }
    }

    public Move getBestMove(State state, int depth) {
        float bestValue = Float.NEGATIVE_INFINITY;
        Move bestMove = new Move();

        ArrayList<Move> moves = state.getMove(this.player);
        for (Move move : moves) {
            State nextState = state.play(move);
            float value = minmax(nextState, depth);

            if (value > bestValue) {
                bestValue = value;
                bestMove = move;
            }
        }

        return bestMove;
    }
}
