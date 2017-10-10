package state;

public class Direction {
    public static int[] up = new int[]{0,1,-1};
    public static int[] upRight = new int[]{1,0,-1};
    public static int[] downRight = new int[]{1,-1,0};
    public static int[] down = new int[]{0,-1,1};
    public static int[] downLeft = new int[]{-1,0,1};
    public static int[] upLeft = new int[]{-1,1,0};
    public static int[][] directions = new int[][]{up,upRight,upLeft,down,downRight,downLeft};
}
