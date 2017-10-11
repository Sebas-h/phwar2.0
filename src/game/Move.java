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

    @Override
    public String toString() {
        return "Move{" +
                "particle=" + particle +
                ", destination=" + Arrays.toString(destination) +
                ", capture=" + capture +
                '}' + '\n';
    }
}
