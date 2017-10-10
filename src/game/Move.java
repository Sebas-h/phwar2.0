package game;

public class Move {

    public Particle particle;
    public int[] destination;

    public Move(){}

    public Move(Particle particle, int[] destination){
        this.particle = particle;
        this.destination = destination;
    }
}
