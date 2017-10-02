package game;

import state.Colour;
import state.Particle;

import java.util.ArrayList;

public abstract class Player {

    ArrayList<Particle> proponent = new ArrayList<>();
    ArrayList<Particle> opponent = new ArrayList<>();

    public Player(ArrayList<Particle> particles, Colour colour){
        if (colour == Colour.BLACK){
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

    public abstract ArrayList<Move> getMove();
}
