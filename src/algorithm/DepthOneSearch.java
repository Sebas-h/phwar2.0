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
    private int numSamplesMCEval;

    public DepthOneSearch(Colour colour, int numSamplesMCEval) {
        super(colour);
        this.currentPlayer = colour;
        this.numSamplesMCEval = numSamplesMCEval;
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
            MCEvaluation evaluation = new MCEvaluation();
            //Score eval =  evaluation.evaluate(s, super.playerColour);
            return evaluation.nEvaluate( this.numSamplesMCEval, s, super.playerColour);
        }

        score.score = Integer.MIN_VALUE + 1;
        for (State state : AlgorithmUtil.getChildren(s, povColour)) {
            if (state.terminal){
                score.score = 1_000_000;
                score.moves = Evaluation.getFirstPlyMoves(state.priorMoves);
                return score;
            }
            value = depthOneSearchMCEval(state, AlgorithmUtil.getOppositeColour(povColour), 0);
            if (value.score > score.score) score = new Score(value);
        }
        return score;
    }

    public static void main(String[] args) {
        Game game = new Game(
                new DepthOneSearch(Colour.BLACK,10),
                new RandomPlayer(Colour.WHITE)
        );
        game.createStartState();
        game.play();
    }
}
