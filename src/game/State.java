package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class State {

    public ArrayList<Particle> particles = new ArrayList<>();
    public Boolean terminal;
    public State parent;
    public ArrayList<Move> priorMoves;

    public State(ArrayList<Particle> particles, Boolean terminal, State parent, ArrayList<Move> priorMoves){
        this.particles = particles;
        this.terminal = terminal;
        this.parent = parent;
        this.priorMoves = priorMoves;
    }

    public State(State state){
        this.terminal = state.terminal;
        this.parent = null;
        this.priorMoves = new ArrayList<>();
        this.particles = new ArrayList<>();
        for (Particle particle : state.particles) {
            this.particles.add(new Particle(particle));
        }
    }

    public void update(ArrayList<Move> moves) {
        if(moves == null){
            // return exception, this should not happen
            return;
        }

        for (Move move : moves) {
             // Apply move
            for (Particle particle : this.particles) {
                if(particle.coordinate[0] == move.particle.coordinate[0] && particle.coordinate[1] == move.particle.coordinate[1]) {
                    if (move.destination == null) this.particles.remove(particle);
                    else particle.coordinate = Arrays.copyOf(move.destination, 3);
                }
            }
        }
    }

    /**
     *
     * -----------------------------------------------------------------------------------------------------------------
     *
     */

    public ArrayList<Move> getPossibleMovesParticle(Particle particle){
        ArrayList<Move> moves = new ArrayList<>();
        for (int[] direction : Direction.directions) {
            for (int i = 1, n = numberOfUnobstructedMoves(particle, direction) + 1; i < n; i++) {
                moves.add(new Move(particle, new int[] {
                        particle.coordinate[0] + (direction[0] * i),
                        particle.coordinate[1] + (direction[1] * i),
                        particle.coordinate[2] + (direction[2] * i)}, false));
            }
        }
        return moves;
    }

    private int numberOfUnobstructedMoves(Particle particle, int[] direction) {
        int[] indicesPosZeroNeg = Direction.getPosZeroNeg(direction);
        int minValue = 0;
        do {
            minValue++;
            if(particle.coordinate[indicesPosZeroNeg[0]] + minValue == 0 &&
                    particle.coordinate[indicesPosZeroNeg[2]] - minValue == 0) {minValue++; break;}
        } while (Math.abs(particle.coordinate[indicesPosZeroNeg[0]] + minValue) < 6 &&
                Math.abs(particle.coordinate[indicesPosZeroNeg[2]] - minValue) < 6);
        minValue--;
        if (minValue != 0) {
            for (Particle otherParticle : this.particles) {
                if (particle != otherParticle &&
                        particle.coordinate[indicesPosZeroNeg[1]] == otherParticle.coordinate[indicesPosZeroNeg[1]] &&
                        particle.coordinate[indicesPosZeroNeg[0]] < otherParticle.coordinate[indicesPosZeroNeg[0]]) {
                    int dif = otherParticle.coordinate[indicesPosZeroNeg[0]] - particle.coordinate[indicesPosZeroNeg[0]] - 1;
                    if (dif < minValue) minValue = dif;
                }
            }
        }
        return minValue;
    }

    public static void main(String args[]){
        ArrayList<Particle> particles = new ArrayList<>();
        particles.add(new Particle(0 , Colour.BLACK, new int[] {0,5,-5 }));
        particles.add(new Particle(-1, Colour.BLACK, new int[] {-1,1,0 }));
        particles.add(new Particle(1 , Colour.BLACK, new int[] {-1,-2,3}));
        particles.add(new Particle(0 , Colour.WHITE, new int[] {0,-5,5 }));
        particles.add(new Particle(-1, Colour.WHITE, new int[] {-1,4,-3}));
        particles.add(new Particle(-1, Colour.WHITE, new int[] {-4,1,3 }));
        particles.add(new Particle(1 , Colour.WHITE, new int[] {4,-4,0 }));
        State state = new State(particles,false,null,null);
        ArrayList<Move> moves = state.getPossibleMovesParticle(state.particles.get(5));
        System.out.println(moves.size());
        System.out.println(moves);
        return;
    }

    public Collection<State> getCaptures(State state) {
        ArrayList<State> captureStates = new ArrayList<>();

        return null;
    }

    public void deleteParticle(Object p0) {
    }
}