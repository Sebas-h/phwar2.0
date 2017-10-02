package state;

import static java.lang.Math.abs;

public class GridPosition {

    final int gridSize = 5;

    public int x;
    public int y;
    public int z;

    public GridPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public GridPosition(GridPosition gp){
        this.x = gp.x;
        this.y = gp.y;
        this.z = gp.z;
    }

    public String getName(){
        String xAxisNames[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
        String xName = xAxisNames[this.x + this.gridSize];
        // Should be able to do this without if statement!
        int startOffset = (abs(this.x)/2)+1;
        int pos;
        if (this.x <= 0){
            pos = this.gridSize - Math.abs(this.x) + this.z + startOffset;
        } else {
            pos = this.gridSize - Math.abs(this.x) - this.y + startOffset;
        }
        return xName+String.valueOf(pos);
    }

}
