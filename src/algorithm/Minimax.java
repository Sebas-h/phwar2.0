package algorithm;

import game.Move;
import state.Colour;
import state.Particle;
import state.State;

import java.util.ArrayList;

public class Minimax extends Algorithm {

    public Minimax(ArrayList<Particle> particles, Colour colour) {
        super(particles, colour);
    }

    @Override
    public ArrayList<Move> getMoves(State s) {
        int result = minimax(s, 3,1);
        return null;
    }

    @Override
    public int evaluate(State s) {
        return 0;
    }

    /**
     * @param state state to give
     * @param depth depth of search tree
     * @param type 1 = MAX player, 0 = MIN player
     * @return a score
     */
    public int minimax(State state, int depth, int type){
        Integer score;
        int value;
        if(state.terminal || depth == 0) return evaluate(state);
        if(type == 1){
            score = Integer.MIN_VALUE;

            long startTime = System.nanoTime();
            getChildren(state);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println(duration);
            System.out.println(duration / 1000000);

            for (State child : getChildren(state)) {
                value = minimax(child,depth-1, 0);
                if (value > score) score = value;
            }
        } else {
            score = Integer.MIN_VALUE;
            for (State child : getChildren(state)) {
                value = minimax(child, depth-1, 1);
                if (value < score) score = value;
            }
        }
        // if depth == 0 or 1? then set Move or state as well;
        return score;
    }

}
