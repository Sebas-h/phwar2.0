package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class State {

    public ArrayList<Particle> particles = new ArrayList<>();
    public Boolean terminal;
    private State parent;
    public ArrayList<Move> priorMoves;

    public State(ArrayList<Particle> particles, Boolean terminal){
        this.particles = particles;
        this.terminal = terminal;
        this.parent = null;
        this.priorMoves = new ArrayList<>();
    }

    public State(State state){
        this.terminal = state.terminal;
        this.parent = null;
        this.priorMoves = new ArrayList<>();
        for (Move priorMove : state.priorMoves) {
            this.priorMoves.add(new Move(priorMove));
        }
        this.particles = new ArrayList<>();
        for (Particle particle : state.particles) {
            this.particles.add(new Particle(particle));
        }
    }

    void update(ArrayList<Move> moves) {
        this.priorMoves = new ArrayList<>();
        for (Move move : moves) {
            int indexToRemove = -1;
            for (Particle particle : this.particles) {
                if(Arrays.equals(particle.coordinate, move.particle.coordinate)){
                    if (move.capture){
                        for (int i = 0; i < this.particles.size(); i++) {
                            if (Arrays.equals(this.particles.get(i).coordinate , move.destination)) indexToRemove = i;
                        }
                    }
                    particle.coordinate = Arrays.copyOf(move.destination, 3);
                    //this.priorMoves.add(move);
                    break;
                }
            }
            if (indexToRemove > -1) this.particles.remove(indexToRemove);
        }
    }

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

    public ArrayList<Particle> getLineOfSightParticles(Particle particle) {
        ArrayList<Particle> losParticles = new ArrayList<>();
        for (int[] direction : Direction.directions) {
            int[] indicesPosZeroNeg = Direction.getPosZeroNeg(direction);
            Particle closest = null;
            for (Particle otherParticle : this.particles) {
                if (particle != otherParticle &&
                        particle.coordinate[indicesPosZeroNeg[1]] == otherParticle.coordinate[indicesPosZeroNeg[1]] &&
                        particle.coordinate[indicesPosZeroNeg[0]] < otherParticle.coordinate[indicesPosZeroNeg[0]]) {
                    if (closest == null) closest = otherParticle;
                    else if (otherParticle.coordinate[indicesPosZeroNeg[0]] < closest.coordinate[indicesPosZeroNeg[0]])
                        closest = otherParticle;
                }
            }
            if(closest != null) losParticles.add(closest);
        }
        return losParticles;
    }

    public void checkTerminal(Colour playerColour) {
        int zeroCount = 0;
        int posCount = 0;
        int negCount = 0;
        for (Particle particle : this.particles) {
            if (particle.charge == 0 && Arrays.equals(particle.coordinate, new int[]{0, 0, 0})) {
                this.terminal = true;
                return;
            }
            if(particle.colour != playerColour) {
                if (particle.charge == 0) zeroCount++;
                else if (particle.charge == 1) posCount++;
                else if (particle.charge == -1) negCount--;
            }
        }
        if (zeroCount == 0 || posCount == 0 || negCount == 0) this.terminal = true;
    }
}