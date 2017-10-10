package game;


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
        System.arraycopy(p.coordinate,0,this.coordinate,0, 3); // wrong, no array init?
    }

    public String getCoordinateName(){
        String xAxisNames[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        return xAxisNames[this.coordinate[0] + boardGridSize] +
                String.valueOf((this.coordinate[2] + ((this.coordinate[0] - (this.coordinate[0]&1)) / 2) + boardGridSize + 1));
    }


}
