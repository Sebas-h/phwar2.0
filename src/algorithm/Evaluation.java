package algorithm;

import game.Colour;
import game.Move;
import game.Particle;
import game.State;

import java.util.*;

public class Evaluation {

    private State state;
    private Colour povColour;

    public Evaluation(State state, Colour povColour){
        this.state = state;
        this.povColour = povColour;
    }

    public Score evaluate(){
        Score score = new Score();
        score.moves = getFirstPlyMoves(this.state.priorMoves);

//        if (this.state.terminal) {
//            score.score = 100000; // maximum score, game needs to end here
//            return score;
//        }

        int electronDifference          = differenceParticle(-1);
        int positronsDifference         = differenceParticle(1);
        int neutronInCenter               = neutronInCenter() ? 1 : 0;
        int diffCircle1                 = differenceCenterControl(1);
        int diffCircle2                 = differenceCenterControl(2);
//        int diffCircle3                 = differenceCenterControl(3);
//        int diffCircle4                 = differenceCenterControl(4);
//        int diffCircle5                 = differenceCenterControl(5);
        int possibleMovesDiff           = diffPossibleMoves();
//        int movesWithDirectCaptureDiff  = diffMovesWithDirectCapture();
//        int movesWithCaptureDiff        = diffMovesWithCapture();

        List<Feature> features = new ArrayList<>();
        features.add(new Feature(electronDifference,            5));
        features.add(new Feature(positronsDifference ,          5));
        features.add(new Feature(neutronInCenter,                 10));
        features.add(new Feature(diffCircle1,                   4));
        features.add(new Feature(diffCircle2,                   2));
//        features.add(new Feature(diffCircle3,                   1));
//        features.add(new Feature(diffCircle4,                   1));
//        features.add(new Feature(diffCircle5,                   1));
        features.add(new Feature(possibleMovesDiff,             5));
//        features.add(new Feature(movesWithDirectCaptureDiff,    1));
//        features.add(new Feature(movesWithCaptureDiff, 1));

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

    /**
     * @return difference between pro- and opponent direct captures (without moving first)
     */
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

    private boolean neutronInCenter() {
        for (Particle particle : this.state.particles) {
            if(particle.colour == this.povColour && particle.charge == 0 &&
                    Arrays.equals(particle.coordinate, new int[]{0,0,0})) return true;
        }
        return false;
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

    public static void main(String[] args) {
//        List<Integer> a = Arrays.asList(1, 2);
//        List<Integer> b = Arrays.asList(3, 4);
//        int[] d = new int[] {1,2,3};

        ArrayList<Particle> particles = new ArrayList<>();
        particles.add(new Particle(0 , Colour.BLACK, new int[] {0,5,-5 }));
        particles.add(new Particle(-1, Colour.BLACK, new int[] {-1,1,0 }));
        particles.add(new Particle(1 , Colour.BLACK, new int[] {-1,-2,3}));
        particles.add(new Particle(0 , Colour.WHITE, new int[] {0,-5,5 }));
        particles.add(new Particle(-1, Colour.WHITE, new int[] {-1,4,-3}));
        particles.add(new Particle(-1, Colour.WHITE, new int[] {-4,1,3 }));
        particles.add(new Particle(1 , Colour.WHITE, new int[] {4,-4,0 }));

//        particles.add(new Particle(0 , Colour.BLACK, new int[] {0,5,-5 }));
//        particles.add(new Particle(-1, Colour.BLACK, new int[] {-2,5,-3}));
//        particles.add(new Particle(-1, Colour.BLACK, new int[] {0,4,-4 }));
//        particles.add(new Particle(-1, Colour.BLACK, new int[] {0,3,-3 }));
//        particles.add(new Particle(-1, Colour.BLACK, new int[] {2,3,-5 }));
//        particles.add(new Particle(1 , Colour.BLACK, new int[] {-1,5,-4}));
//        particles.add(new Particle(1 , Colour.BLACK, new int[] {-1,4,-3}));
//        particles.add(new Particle(1 , Colour.BLACK, new int[] {1,4,-5 }));
//        particles.add(new Particle(1 , Colour.BLACK, new int[] {1,3,-4 }));
//
//        for (int i = particles.size()-1; i > -1; i--) {
//            particles.add(new Particle(particles.get(i).charge, Colour.WHITE, new int[] {
//                    particles.get(i).coordinate[0] * -1, particles.get(i).coordinate[1] * -1, particles.get(i).coordinate[2] * -1}));
//        }

        State state = new State(particles, false);

        Evaluation evaluation = new Evaluation(state, Colour.WHITE);
        Score eval = evaluation.evaluate();
        return;
    }
}
