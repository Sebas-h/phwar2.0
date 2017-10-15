package algorithm;

import game.Move;
import game.Colour;
import game.State;

import java.util.ArrayList;

public class MiniMax extends Algorithm {

    public MiniMax(Colour colour) {
        super(colour);
    }

    @Override
    public ArrayList<Move> getAction(State s) {
        Score result = miniMax(s, 2,1);
        return result.moves;
    }

    /**
     * @param state state to give
     * @param depth depth of search tree
     * @param type 1 = MAX player, 0 = MIN player
     * @return a score
     */
    private Score miniMax(State state, int depth, int type){
        Score score = new Score();
        Score value;
        if(state.terminal || depth == 0) {
            Evaluation evaluation = new Evaluation(state, super.playerColour);
            return evaluation.evaluate();
        }
        if(type == 1){
            score.score = Integer.MIN_VALUE;
            for (State child : getChildren(state, super.playerColour)) {
                value = miniMax(child,depth-1, 0);
                if (value.score > score.score) score = new Score(value);
            }
        } else {
            score.score = Integer.MAX_VALUE;
            for (State child : getChildren(state, super.opponentColour)) {
                value = miniMax(child, depth-1, 1);
                if (value.score < score.score) score = new Score(value);
            }
        }
        return score;
    }
}
