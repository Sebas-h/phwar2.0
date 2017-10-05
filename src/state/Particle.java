package state;

import java.util.ArrayList;

public class Particle {
    public int charge;
    public Colour colour;
    public Position position;

    public ArrayList<Particle> lineOfSight = new ArrayList<Particle>();

    public Particle(int charge, Colour colour, Position position) {
        this.charge = charge;
        this.colour = colour;
        this.position = position;
    }

    public void updatePosition(Position newPosition){
        // change position with newPosition;

        // update lineOfSight;

    }

    public Position[] getPossibleMoves() {
        this.position.x += 0;
        this.position.y -= 1;
        this.position.z += 1;

        Position gpgp = new Position(1,2,3);
        Position returnlist[] = {gpgp};
        return returnlist;
    }

    @Override
    public String toString() {
        return "Particle{" +
                "charge=" + charge +
                ", colour=" + colour +
                ", position=" + position.toString() +
                '}' + '\n';
    }
}
