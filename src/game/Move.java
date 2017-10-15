package game;

import java.util.Arrays;

public class Move {

    public Particle particle;
    public int[] destination;
    public boolean capture;

    public Move(Particle particle, int[] destination, boolean capture){
        this.particle = particle;
        this.destination = destination;
        this.capture = capture;
    }

    public Move(Move move){
        this.capture = move.capture;
        this.destination = Arrays.copyOf(move.destination,move.destination.length);
        this.particle = new Particle(move.particle);
    }

    @Override
    public String toString() {
        return "\nMove{" +
                "particle=" + particle +
                ", destination=" + Particle.getCoordinateName(destination) +
                ", capture=" + capture +
                '}' + '\n';
    }
}
