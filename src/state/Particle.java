package state;

public class Particle {
    public Type pt;
    public Colour c;
    public GridPosition gp;

    public Particle(Type pt, Colour c, GridPosition gp) {
        this.pt = pt;
        this.c = c;
        this.gp = gp;
    }

    public GridPosition[] getPossibleMoves() {

        this.gp.x += 0;
        this.gp.y -= 1;
        this.gp.z += 1;

        GridPosition gpgp = new GridPosition(1,2,3);
        GridPosition returnlist[] = {gpgp};
        return returnlist;
    }


//    public ArrayList<Particle> getChildren(){
//        ArrayList<Particle> children = new ArrayList<>();
//
//        for (int i = 0; i < 6; i++) {
//
//            while (true) {
//                int k = 1;
//
//                this.gp.x += k;
//                this.gp.y -= k;
//
//                break;
//            }
//
//        }
//
//        return children;
//    }

}
