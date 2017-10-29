package algorithm;

import game.Colour;
import game.Move;
import game.Particle;
import game.State;

import java.util.ArrayList;
import java.util.Arrays;

public class AlgorithmUtil {

    public static ArrayList<State> getChildren(State state, Colour povColour){
        ArrayList<State> children = new ArrayList<>();
        for (Particle particle : state.particles) {
            if(particle.colour == povColour) {
                // get possible moves
                ArrayList<Move> possibleMoves = state.getPossibleMovesParticle(particle);
                // apply moves
                ArrayList<State> nStates = applyMoves(state, possibleMoves);
                // apply captures
                nStates = applyCaptures(nStates, povColour);
                // check terminal
                checkTerminal(nStates, povColour);
                // add states
                children.addAll(nStates);
            }
        }
        return children;
    }

    public static ArrayList<Move> getCaptureMoves(State state , Colour povColour) {
        ArrayList<Move> moves = new ArrayList<>();
        for (Particle particle : state.particles) {
            if(particle.colour != povColour){
                int friendlyParticles = 0;
                int totalCharge = 0;
                ArrayList<Particle> losParticles = state.getLineOfSightParticles(particle);
                for (Particle losParticle : losParticles) {
                    if (losParticle.colour == povColour) friendlyParticles++;
                    totalCharge += losParticle.charge;
                }
                if (friendlyParticles > 1 && totalCharge == 0){
                    for (Particle losParticle : losParticles) {
                        if (losParticle.colour == povColour){
                            moves.add(new Move(losParticle, particle.coordinate,true));
                        }
                    }
                }
            }
        }
        return moves;
    }

    public static ArrayList<State> applyMoves(State state, ArrayList<Move> moves) {
        ArrayList<State> resultingStates = new ArrayList<>();
        for (Move move : moves) {
            State nState = new State(state);
            int indexToRemove = -1;
            for (Particle particle : nState.particles) {
                if(Arrays.equals(particle.coordinate, move.particle.coordinate)){
                    if (move.capture){
                        for (int i = 0; i < nState.particles.size(); i++) {
                            if (Arrays.equals(nState.particles.get(i).coordinate , move.destination)) indexToRemove = i;
                        }
                    }
                    particle.coordinate = Arrays.copyOf(move.destination, 3);
                    nState.priorMoves.add(move);
                    break;
                }
            }
            if (indexToRemove > -1) nState.particles.remove(indexToRemove);
            resultingStates.add(nState);
        }
        return resultingStates;
    }

    private static ArrayList<State> captures(State state, Colour povColour) {
        ArrayList<State> result = new ArrayList<>();
        ArrayList<Move> captureMoves = getCaptureMoves(state, povColour);
        if (captureMoves.isEmpty()) {result.add(state); return result;}
        for (State s : applyMoves(state, captureMoves)) {
            result.addAll(captures(s, povColour));
        }
        return result;
    }

    private static ArrayList<State> applyCaptures(ArrayList<State> states, Colour povColour) {
        ArrayList<State> nChildren = new ArrayList<>();
        for (State state : states) {
            nChildren.addAll(captures(state, povColour));
        }
        return nChildren;
    }

    private static void checkTerminal(ArrayList<State> states, Colour povColour) {
        for (State state : states) {
            state.checkTerminal(povColour);
        }
    }

    public static Colour getOppositeColour(Colour povColour) {
        if(povColour == Colour.BLACK) return Colour.WHITE;
        return Colour.BLACK;
    }
}
