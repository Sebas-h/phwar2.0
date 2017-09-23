package GameState;

public class Particle {
    public ParticleType pt;
    public Colour c;
    public GridPosition gp;

    public Particle(ParticleType pt, Colour c, GridPosition gp) {
        this.pt = pt;
        this.c = c;
        this.gp = gp;
    }
}
