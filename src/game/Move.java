package game;

import state.Particle;
import state.Position;
import state.State;

public class Move {

    public Particle particle;
    public Position destination;

    public Move(){}

    public Move(Particle particle, Position destination){
        this.particle = new Particle(particle);
        this.destination = new Position(destination);
    }
}
