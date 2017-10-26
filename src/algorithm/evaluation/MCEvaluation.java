package algorithm.evaluation;

import algorithm.Algorithm;
import game.Colour;
import game.Game;
import game.Move;
import game.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static algorithm.evaluation.Evaluation.getFirstPlyMoves;

public class MCEvaluation implements IEvaluation {

    private static final int NUMSAMPLES = 100;

    @Override
    public Score evaluate(State state, Colour povColour) {
        Score score = new Score();
        score.moves = getFirstPlyMoves(state.priorMoves);
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < NUMSAMPLES; i++) {
            scores.add(play_and_score_random_game(state, povColour));
        }
        score.score = statistic(scores);

        String sb = String.valueOf(score.score) + "  move: " +
                Game.printMoves(score.moves);
        System.out.println(sb);

        return score;
    }

    /**
     * Takes in scores, returns the average of the scores
     * @param scores List of Ints
     * @return average as int
     */
    private int statistic(List<Integer> scores) {
        int sum = 0;
        for (Integer score : scores) {
            sum += score;
        }
        return (sum/ scores.size());
    }

    /**
     * Plays random moves for a state until terminal state (win/loss) has been reached
     * @param state state
     * @param povColour colour
     * @return 1 for win player, -1 for win opponent
     */
    private Integer play_and_score_random_game(State state, Colour povColour) {
        Colour colour = povColour;
        do {
            colour = getOppositeColour(colour);
            ArrayList<State> children = Algorithm.getChildren(state, colour);
            // todo: add mc move ordering (strategy)
            Random rand = new Random(System.nanoTime());
            if (children.size() < 1){
                System.out.println("not supposed to happen?");
            }
            state = children.get(rand.nextInt(children.size()));
        } while (!state.terminal);
        if(colour == povColour) return 100;
        return -100;
    }

    private Colour getOppositeColour(Colour povColour) {
        if(povColour == Colour.BLACK) return Colour.WHITE;
        return Colour.BLACK;
    }
}
