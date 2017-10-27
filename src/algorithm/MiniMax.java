package algorithm;

import algorithm.evaluation.Evaluation;
import algorithm.evaluation.IEvaluation;
import algorithm.evaluation.Score;
import game.Game;
import game.Move;
import game.Colour;
import game.State;

import java.util.ArrayList;

public class MiniMax extends Algorithm {

    int depth;

    public MiniMax(Colour colour, int depth) {
        super(colour);
        this.depth = depth;
    }

    @Override
    public ArrayList<Move> getAction(State s) {
        Score result = miniMax(s, this.depth,1);
        //System.out.println("[" + result.score + "]");
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
            IEvaluation evaluation = new Evaluation();
            return evaluation.evaluate(state, super.playerColour);
        }
        if(type == 1){
            score.score = Integer.MIN_VALUE;
            for (State child : getChildren(state, super.playerColour)) {
                if (depth == this.depth && child.terminal){
                    score.score = 1_000_000;
                    score.moves = Evaluation.getFirstPlyMoves(child.priorMoves);
                }
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

    public static void main(String[] args) {
        Game game = new Game(
                new MiniMax(Colour.BLACK, 3),
                new RandomPlayer(Colour.WHITE)
        );
        game.createStartState();
        game.play();
    }
}
