package game;

class Direction {
    private static int[] up = new int[]{0,1,-1};
    private static int[] upRight = new int[]{1,0,-1};
    private static int[] downRight = new int[]{1,-1,0};
    private static int[] down = new int[]{0,-1,1};
    private static int[] downLeft = new int[]{-1,0,1};
    private static int[] upLeft = new int[]{-1,1,0};
    static int[][] directions = new int[][]{up,upRight,upLeft,down,downRight,downLeft};

    static int[] getPosZeroNeg(int[] direction){
        int[] res = new int[3];
        for (int i = 0; i < direction.length; i++) {
            if(direction[i] == 1) res[0] = i;
            else if(direction[i] == 0) res[1] = i;
            else if(direction[i] == -1) res[2] = i;
        }
        return res;
    }
}
