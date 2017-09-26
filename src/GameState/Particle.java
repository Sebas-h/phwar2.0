package GameState;

import java.util.ArrayList;

public class Particle {
    public ParticleType pt;
    public ParticleColour c;
    public GridPosition gp;

    public Particle(ParticleType pt, ParticleColour c, GridPosition gp) {
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
