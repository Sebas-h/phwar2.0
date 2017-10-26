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

public class NegaMax extends Algorithm{

    private Colour currentPlayer;

    private int depth;

    public NegaMax(Colour colour, int depth) {
        super(colour);
        this.currentPlayer = colour;
        this.depth = depth;
    }

    @Override
    public ArrayList<Move> getAction(State s) {
        Score result = negaMax(s, this.depth,
                Integer.MIN_VALUE + 1, Integer.MAX_VALUE,
                super.playerColour, 1);

        //System.out.println("[" + result.score + "]");

        return result.moves;
    }

    private Score negaMax(State s, int depth, int alpha, int beta, Colour povColour, int color) {
        Score score = new Score();
        Score value;
        if (s.terminal || depth == 0) {
            IEvaluation evaluation = new Evaluation();
            Score eval =  evaluation.evaluate(s, super.playerColour);
            System.out.println(eval.score);
            eval.score *= color;
            System.out.println(eval.score + "\n");
            return eval;
        }
        
        // children = getChildren();
        // children = orderMoves(children, color);
        //      if color==1 sort descending (index 0 is highest score), else ascending (index 0 is lowest score)

        score.score = Integer.MIN_VALUE + 1;
        for (State state : getChildren(s, povColour)) {
            value = negaMax(state, depth-1, -alpha, -beta, getOppositeColour(povColour), -color);
            value.score *= -1;
            if(value.score > score.score) score = value;
            if(score.score > alpha) alpha = score.score;
            if(score.score >= beta) break; // pruning
        }
        return score;
    }

    private Colour getOppositeColour(Colour povColour) {
        if(povColour == Colour.BLACK) return Colour.WHITE;
        return Colour.BLACK;
    }

    public static void main(String[] args) {
        Game game = new Game(
                new NegaMax(Colour.BLACK, 2),
                new RandomPlayer(Colour.WHITE)
        );
        game.createStartState();
        game.play();
    }
}
