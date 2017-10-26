package algorithm.evaluation;

import algorithm.Algorithm;
import game.Colour;
import game.Move;
import game.Particle;
import game.State;

import java.util.*;

public class Evaluation implements IEvaluation {

    private State state;
    private Colour povColour;

    public Evaluation(){
    }

    @Override
    public Score evaluate(State state, Colour povColour){
        this.state = state;
        this.povColour = povColour;

        Score score = new Score();
        score.moves = getFirstPlyMoves(this.state.priorMoves);

        int electronDifference          = differenceParticle(-1);
        int positronsDifference         = differenceParticle(1);
        int possibleMovesDiff           = diffPossibleMoves();
        int pieceInCenter               = pieceInCenter() ? 1 : 0;
        int diffCircle1                 = differenceCenterControl(1);

        List<Feature> features = new ArrayList<>();
        features.add(new Feature(electronDifference,        1));
        features.add(new Feature(positronsDifference ,      1));
        features.add(new Feature(possibleMovesDiff,         1));
        features.add(new Feature(pieceInCenter,             1));
        features.add(new Feature(diffCircle1,               1));

        int evalScore = 0;
        // Summing the features*weights to get evaluation score:
        for (Feature feature : features) {
            evalScore += (feature.getValue()*feature.getWeight());
        }

        score.score = evalScore;
        return score;
    }

    public static ArrayList<Move> getFirstPlyMoves(List<Move> moves){
        ArrayList<Move> result = new ArrayList<>();
        Colour povColour = moves.get(0).particle.colour;
        for (Move move : moves) {
            if (move.particle.colour == povColour){
                result.add(new Move(move));
            }
            else break;
        }
        return result;
    }

    private int diffMovesWithCapture() {
        int numCapMoves = 0;
        for (Particle p : this.state.particles) {
            if (p.colour == this.povColour){
                for (State s : Algorithm.applyMoves(this.state, this.state.getPossibleMovesParticle(p))) {
                    numCapMoves += Algorithm.getCaptureMoves(s, this.povColour).size();
                }
            }
        }
        return numCapMoves;
    }

    private int diffMovesWithDirectCapture() {
        int numCapMovesProponent = 0;
        int numCapMovesOpponent = 0;
        Colour opponentColour;
        if (this.povColour == Colour.BLACK) opponentColour = Colour.WHITE;
        else opponentColour = Colour.BLACK;
        numCapMovesProponent = Algorithm.getCaptureMoves(this.state, this.povColour).size();
        numCapMovesOpponent = Algorithm.getCaptureMoves(this.state, opponentColour).size();
        return numCapMovesProponent - numCapMovesOpponent;
    }

    private int differenceParticle(int charge) {
        int numProponent = 0;
        int numOpponent = 0;
        for (Particle particle : this.state.particles) {
            if(particle.charge == charge && particle.colour == this.povColour) numProponent++;
            else if(particle.charge == charge) numOpponent++;
        }
        return numProponent - numOpponent;
    }

    private int diffPossibleMoves() {
        int countProponent = 0;
        int countOpponent = 0;
        for (Particle p : this.state.particles) {
            if(p.colour == this.povColour) countProponent += this.state.getPossibleMovesParticle(p).size();
            else countOpponent += this.state.getPossibleMovesParticle(p).size();
        }
        return countProponent - countOpponent;
    }

    private int differenceCenterControl(int circle) {
        int countProponent = 0;
        int countOpponent = 0;
        for (Particle p : this.state.particles) {
            if(p.colour == this.povColour &&
                    (Math.abs(p.coordinate[0]) + Math.abs(p.coordinate[1]) + Math.abs(p.coordinate[2]) == circle*2))
                countProponent++;
            else if (Math.abs(p.coordinate[0]) + Math.abs(p.coordinate[1]) + Math.abs(p.coordinate[2]) == circle*2)
                countOpponent++;
        }
        return countProponent - countOpponent;
    }

    private boolean pieceInCenter() {
        for (Particle particle : this.state.particles) {
            if(particle.colour == this.povColour &&
                    Arrays.equals(particle.coordinate, new int[]{0,0,0})) return true;
        }
        return false;
    }
}
