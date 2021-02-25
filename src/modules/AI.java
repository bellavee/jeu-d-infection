package modules;

import java.util.ArrayList;

public class AI {
    String player;
    int depth;
    int node = 0;
    boolean choice;

    public AI(String player, int depth, boolean choice) {
        this.player = player;
        this.depth = depth;
        this.choice = choice;
    }

    public String getPlayer() {
        return this.player;
    }

    /* ---------- FUNCTION ---------- */

    public float minmax(State state, int depth) {
        float n;
        float m;
        ArrayList<Move> moves = state.getMove(this.player);
        if (depth == 0 || state.isOver()) {
            return state.getScore(this.player);
        } else {
            if (state.getCurrentPlayer() != this.player) {
                n = Float.POSITIVE_INFINITY;
                for (Move move : moves) {
                    State stateClone = state.play(move);
                    m = minmax(stateClone, depth - 1);
                    if (n > m) {
                        n = m;
                    }

                }
            } else {
                n = Float.NEGATIVE_INFINITY;
                for (Move move : moves) {
                    State stateClone = state.play(move);
                    m = minmax(stateClone, depth - 1);
                    if (n < m) {
                        n = m;
                    }
                }
            }
            node++;
            return n;
        }
    }

    public float alphabeta(State state, int depth, float alpha, float beta) {
        ArrayList<Move> moves = state.getMove(this.player);
        if (depth == 0 || state.isOver()) {
            return state.getScore(this.player);
        } else {
            if (state.getCurrentPlayer() != this.player) {
                for (Move move : moves) {
                    State stateClone = state.play(move);
                    beta = Float.min(alpha, alphabeta(stateClone, depth - 1, alpha, beta));
                    if (alpha >= beta) {
                        return beta;
                    }
                }
                return beta;
            } else {
                for (Move move : moves) {
                    State stateClone = state.play(move);
                    alpha = Float.max(alpha, alphabeta(stateClone, depth - 1, alpha, beta));
                    if (alpha >= beta) {
                        return alpha;
                    }
                }
                return alpha;
            }
        }
        node++;
    }

    public Move getBestMove(State state, int depth) {
        float x;
        float y = Float.NEGATIVE_INFINITY;
        Move bestMove = new Move();
        ArrayList<Move> moves = state.getMove(this.player);
        for (Move move : moves) {
            State stateClone = state.play(move);
            if (this.choice == true)
                x = alphabeta(stateClone, depth - 1, 1, 0);
            else
                x = minmax(stateClone, depth - 1);

            if (x > y) {
                y = x;
                bestMove = move; // meilleure coup a jouer
            }
        }
        return bestMove;
    }

}
