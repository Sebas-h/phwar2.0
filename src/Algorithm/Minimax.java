package Algorithm;

import GameState.ParticleColour;
import GameState.State;

public class Minimax {

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

    public static void main(String args[]){
        Minimax mm = new Minimax();

        State s = new State();
        s.createStartState(ParticleColour.BLACK);


    }
}
