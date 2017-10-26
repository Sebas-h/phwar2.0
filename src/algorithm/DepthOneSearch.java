package algorithm;

import algorithm.evaluation.Evaluation;
import algorithm.evaluation.IEvaluation;
import algorithm.evaluation.MCEvaluation;
import algorithm.evaluation.Score;
import game.Colour;
import game.Game;
import game.Move;
import game.State;

import java.util.ArrayList;

public class DepthOneSearch extends Algorithm {

    private Colour currentPlayer;

    public DepthOneSearch(Colour colour) {
        super(colour);
        this.currentPlayer = colour;
    }

    @Override
    public ArrayList<Move> getAction(State s) {
        Score result = depthOneSearchMCEval(s, super.playerColour,1);
        //System.out.println("[" + result.score + "]");
        return result.moves;
    }

    private Score depthOneSearchMCEval(State s, Colour povColour, int depth) {
        Score score = new Score();
        Score value;
        if (s.terminal || depth == 0) {
            IEvaluation evaluation = new MCEvaluation();
            //Score eval =  evaluation.evaluate(s, super.playerColour);
            return evaluation.evaluate(s, super.playerColour);
        }

        score.score = Integer.MIN_VALUE + 1;
        for (State state : getChildren(s, povColour)) {
            if (state.terminal){
                score.score = 1_000_000;
                score.moves = Evaluation.getFirstPlyMoves(state.priorMoves);
                return score;
            }
            value = depthOneSearchMCEval(state, getOppositeColour(povColour), 0);
            if (value.score > score.score) score = new Score(value);
        }
        return score;
    }

    private Colour getOppositeColour(Colour povColour) {
        if(povColour == Colour.BLACK) return Colour.WHITE;
        return Colour.BLACK;
    }

    public static void main(String[] args) {
        Game game = new Game(
                new DepthOneSearch(Colour.BLACK),
                new RandomPlayer(Colour.WHITE)
        );
        game.createStartState();
        game.play();
    }
}
