package algorithm;

import game.Move;
import state.Colour;
import state.Particle;
import state.State;

import java.util.ArrayList;

public class Minimax extends Algorithm {

    private final int maxDepth = 3;

    public Minimax(ArrayList<Particle> particles, Colour colour) {
        super(particles, colour);
    }


    public Score minimax(State state, int currentDepth, int maxDepth){

        if (state.terminal || currentDepth == this.maxDepth){ // add "|| terminal node" if the game is over
            // evaluate score, return that
        }

        if (currentDepth % 2 == 0){
            // Max player

        } else {
            // Min player

        }

        return new Score();
    }

    @Override
    public ArrayList<Move> getMove() {
        return null;
    }

    @Override
    public Score evaluateState(State s) {
        return null;
    }
}
