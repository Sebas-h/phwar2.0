package algorithm;

import algorithm.evaluation.Evaluation;
import algorithm.evaluation.IEvaluation;
import algorithm.evaluation.Score;
import game.*;

import java.util.ArrayList;
import java.util.Collections;

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
        Experiment.leafNodesEachTurn.add(Experiment.leafNodes);
        Experiment.nodesVisitedEachTurn.add(Experiment.nodesVisited);
        Experiment.leafNodes = 0;
        Experiment.nodesVisited = 0;

        return result.moves;
    }

    private Score negaMax(State s, int depth, int alpha, int beta, Colour povColour, int color) {
        Score score = new Score();
        Score value;
        if (s.terminal || depth == 0) {
            Experiment.leafNodes++;
            IEvaluation evaluation = new Evaluation();
            Score eval =  evaluation.evaluate(s, super.playerColour);
            eval.score *= color;
            return eval;
        }

        // Move ordering on children:
        ArrayList<State> children = AlgorithmUtil.getChildren(s, povColour);
        children = orderStates(children);

        score.score = Integer.MIN_VALUE + 1;
        for (State state : children) {
            Experiment.nodesVisited++;
            value = negaMax(state, depth-1, -alpha, -beta, AlgorithmUtil.getOppositeColour(povColour), -color);
            value.score *= -1;
            if(value.score > score.score) score = value;
            if(score.score > alpha) alpha = score.score;
            if(score.score >= beta) break; // pruning
        }
        return score;
    }

    private ArrayList<State> orderStates(ArrayList<State> children){
        // Order based on capture moves
        // Node/state/move with most captures will be investigated first

        // Insertion sort to order moves:
        for (int i = 1; i < children.size(); i++) {
            for (int j = i; j > 0 && children.get(j-1).priorMoves.size() > children.get(j).priorMoves.size(); j--) {
                // swap j with j-1:
                Collections.swap(children,j,j-1);
            }
        }


        return children;
    }

    public static void main(String[] args) {
        Game game = new Game(
                new NegaMax(Colour.BLACK, 3),
                new RandomPlayer(Colour.WHITE)
        );
        game.createStartState();
        game.play();
    }
}
