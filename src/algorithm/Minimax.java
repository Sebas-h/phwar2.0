package algorithm;

import game.Move;
import state.State;

public class Minimax extends Algorithm {

    private final int maxDepth = 3;

    public Score minimax(State state, int currentDepth, int maxDepth){

        if (state.terminalState || currentDepth == this.maxDepth){ // add "|| terminal node" if the game is over
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
    public Move getMove() {
        return null;
    }

    @Override
    public Score evaluateState(State s) {
        return null;
    }
}
