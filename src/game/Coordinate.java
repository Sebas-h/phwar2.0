package game;

import static game.Game.boardGridSize;

public class Coordinate {
    public int x;
    public int y;
    public int z;

    public Coordinate(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate(Coordinate c){
        this.x = c.x;
        this.y = c.y;
        this.z = c.z;
    }

    public String getCoordinateName(){
        String xAxisNames[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        return xAxisNames[this.x + boardGridSize] +
                String.valueOf((this.z + ((this.x - (this.x&1)) / 2) + boardGridSize + 1));
    }
}
