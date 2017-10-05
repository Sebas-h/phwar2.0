package state;

import static game.Game.gridSize;

public class Position {

    public int x;
    public int y;
    public int z;

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position(Position p){
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public String getName(){
        String xAxisNames[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        String xName = xAxisNames[this.x + gridSize];
        return xName + String.valueOf((z + ((x - (x&1)) / 2) + gridSize + 1));
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
