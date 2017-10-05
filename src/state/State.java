package state;

import game.Game;
import game.Move;

import java.util.ArrayList;

public class State {

    public State parent;

    public Boolean terminal;

    public ArrayList<Particle> particles = new ArrayList<>();

    public State(ArrayList<Particle> particles, Boolean terminal, State parent){
        this.particles = particles;
        this.terminal = terminal;
        this.parent = parent;
    }

    public State(State state){
        this.parent = state;
        this.terminal = false;
        this.particles = this.copyParticles(state.particles);
    }

    private ArrayList<Particle> copyParticles(ArrayList<Particle> particles) {
        ArrayList<Particle> particlesCopy = new ArrayList<>();
        for (Particle particle : particles) {
            particlesCopy.add(new Particle(particle.charge,particle.colour,new Position(particle.position)));
        }
        return particlesCopy;
    }

    public void update(ArrayList<Move> moves) {
        if(moves == null){
            // return exception, this should not happen
            this.particles.get(0).position = new Position(-3,2,1);
            return;
        }

        for (Move move : moves) {
            // Check validity; Only check validity of move when against human?
            // todo: this.checkMove(moves); // move outside for loop

            // Apply move
            for (Particle particle : this.particles) {
                if(particle == move.particle) {
                    if (move.destination == null) this.particles.remove(particle);
                    else particle.position = move.destination;
                }
            }
        }
    }

    private boolean checkMove(ArrayList<Move> moves) {
        // Apply game rules check:
        for (Move move : moves) {
            if (!isInBoard(move)) return false;
            if (move.destination != null && !isPositionOccupied(move)) return false;
            if (move.destination == null && !isOverallChargeZero(move)) return false;
        }


        return true;
    }

    private boolean isOverallChargeZero(Move move) {
        ArrayList<Particle> particlesInLineOfSight = getParticlesLineOfSight(move.destination);

        return true;
    }

    private ArrayList<Particle> getParticlesLineOfSight(Position destination) {
        for (Particle particle : particles) {

        }

        return null;
    }

    private boolean isPositionOccupied(Move move) {
        for (Particle particle : particles) {
            if(particle.position == move.destination) return false;
        }
        return true;
    }

    private boolean isInBoard(Move move) {
        return (move.destination.x >= -Game.gridSize && move.destination.x <= Game.gridSize) &&
                (move.destination.y >= -Game.gridSize && move.destination.y <= Game.gridSize) &&
                (move.destination.z >= -Game.gridSize && move.destination.z <= Game.gridSize) &&
                (move.destination.x + move.destination.y + move.destination.z == 0);
    }

    @Override
    public String toString() {
        return "State{" +
                ", terminal=" + terminal +
                ", particles=" + particles +
                '}' + '\n';
    }
}