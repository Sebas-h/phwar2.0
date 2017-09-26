package GameState;

import java.util.ArrayList;
import java.util.Arrays;

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

    static public void main(String args[]){
        GridPosition gp = new GridPosition(1,-4,3);
        GridPosition gp1 = new GridPosition(-2,-2,4);
        GridPosition gp2 = new GridPosition(-4,4,0);
        GridPosition gp3 = new GridPosition(5,0,-5);
        String name = gp.getName();
        System.out.println(name);
        System.out.println(gp1.getName());
        System.out.println(gp2.getName());
        System.out.println(gp3.getName());
    }
}
