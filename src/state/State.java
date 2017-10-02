package state;

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

    public void update(ArrayList<Move> moves) {
        if(moves == null){
            // return exception, this should not happen
            this.particles.get(0).position = new Position(-3,2,1);
            return;
        }

        for (Move move : moves) {
            // Check validity
            move.checkMove(this);

            // Apply move
            for (Particle particle : this.particles) {
                if(particle == move.particle) particle.position = move.destination;
            }
        }
    }
}