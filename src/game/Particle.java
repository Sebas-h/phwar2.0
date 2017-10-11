package game;


import java.util.Arrays;

import static game.Game.boardGridSize;

public class Particle {
    public int charge;
    public Colour colour;
    public int[] coordinate;

    public Particle(int charge, Colour colour, int[] coordinate) {
        this.charge = charge;
        this.colour = colour;
        this.coordinate = coordinate;
    }

    public Particle(Particle p) {
        this.charge = p.charge;
        this.colour = p.colour;
        this.coordinate = Arrays.copyOf(p.coordinate, 3);
    }

    public String getCoordinateName(){
        String xAxisNames[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        return xAxisNames[this.coordinate[0] + boardGridSize] +
                String.valueOf((this.coordinate[2] + ((this.coordinate[0] - (this.coordinate[0]&1)) / 2) + boardGridSize + 1));
    }


}
