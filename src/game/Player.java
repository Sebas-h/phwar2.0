package game;

import state.Colour;
import state.Particle;
import state.State;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    public ArrayList<Particle> proponent = new ArrayList<>();
    public ArrayList<Particle> opponent = new ArrayList<>();
    public Colour colour;
    public int capturedParticles = 0;

    public Player(ArrayList<Particle> particles, Colour colour){
        this.colour = colour;
        if (this.colour == Colour.BLACK){
            for (Particle particle : particles) {
                if(particle.colour == Colour.BLACK) this.proponent.add(particle);
                else this.opponent.add(particle);
            }
        } else {
            for (Particle particle : particles) {
                if(particle.colour == Colour.WHITE) this.proponent.add(particle);
                else this.opponent.add(particle);
            }
        }
    }

    public ArrayList<Particle> getBlackParticles(State state) {
        List<Particle> blackParticles = state.particles.subList(0, 8 - this.capturedParticles);
        List<Particle> whiteParticles = state.particles.subList(9 - this.capturedParticles, state.particles.size() - 1);

        return opponent;
    }

    public abstract ArrayList<Move> getAction(State s);

}
