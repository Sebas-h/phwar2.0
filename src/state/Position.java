package state;

import static java.lang.Math.abs;

public class Position {

    public int x;
    public int y;
    public int z;

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position(Position gp){
        this.x = gp.x;
        this.y = gp.y;
        this.z = gp.z;
    }

    public String getName(){
        String xAxisNames[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        int gridSize = 5;
        String xName = xAxisNames[this.x + gridSize];
        // Should be able to do this without if statement!
        int startOffset = (abs(this.x)/2)+1;
        int pos;
        if (this.x <= 0){
            pos = gridSize - Math.abs(this.x) + this.z + startOffset;
        } else {
            pos = gridSize - Math.abs(this.x) - this.y + startOffset;
        }
        return xName+String.valueOf(pos);
    }

}
