package game;


import java.util.Arrays;

import static game.Game.boardGridSize;

public class Particle {
    public int charge;
    public Colour colour;
    public int[] coordinate;

    public Particle(){}

    public Particle(int charge, Colour colour, int[] coordinate) {
        this.charge = charge;
        this.colour = colour;
        this.coordinate = coordinate;
    }

    public Particle(Particle p) {
        this.charge = p.charge;
        this.colour = p.colour;
        this.coordinate = Arrays.copyOf(p.coordinate, p.coordinate.length);
    }

    public static String getCoordinateName(int[] coordinate){
        String xAxisNames[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        return xAxisNames[coordinate[0] + boardGridSize] +
                String.valueOf((coordinate[2] + ((coordinate[0] - (coordinate[0]&1)) / 2) + boardGridSize + 1));
    }

    @Override
    public String toString() {
        return "Particle{" +
                "charge=" + charge +
                ", colour=" + colour +
                ", coordinate=" + getCoordinateName(coordinate) +
                '}' + '\n';
    }
}
