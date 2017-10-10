package algorithm;

import game.Move;
import game.Colour;
import game.Particle;
import game.State;

import java.util.ArrayList;

public class Minimax extends Algorithm {

    public Minimax(ArrayList<Particle> particles, Colour colour) {
        super(particles, colour);
    }

    @Override
    public ArrayList<Move> getAction(State s) {
        Score result = minimax(s, 2,1);
        return result.moves;
    }

    @Override
    public Score evaluate(State s) {
        Score score = new Score();
        int nop = 0;
        for (Particle particle : s.particles) {
            if(particle.colour == super.playerColour) nop++;
        }

        score.score = nop;
        score.moves = new ArrayList<>();
        for (Move priorMove : s.priorMoves) {
            score.moves.add(new Move(priorMove.particle, priorMove.destination));
        }
        // score.moves = s.priorMoves;
        return score;
    }

    /**
     * @param state state to give
     * @param depth depth of search tree
     * @param type 1 = MAX player, 0 = MIN player
     * @return a score
     */
    public Score minimax(State state, int depth, int type){
        Score score = new Score();
        Score value;
        if(state.terminal || depth == 0) return evaluate(state);
        if(type == 1){
            score.score = Integer.MIN_VALUE;
//            long startTime = System.nanoTime();
//            getChildrenOld(state);
//            long endTime = System.nanoTime();
//            long duration = (endTime - startTime);
//            System.out.println(duration);
//            System.out.println(duration / 1000000);
            for (State child : getChildrenState(state)) {
                value = minimax(child,depth-1, 0);
                if (value.score > score.score) score = new Score(value);
            }
        } else {
            score.score = Integer.MAX_VALUE;
            for (State child : getChildrenState(state)) {
                value = minimax(child, depth-1, 1);
                if (value.score < score.score) score = new Score(value);
            }
        }
        // if depth == 0 or 1? then set Move or state as well;
        return score;
    }

}
