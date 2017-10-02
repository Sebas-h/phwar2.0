package state;

public class Particle {
    public Type type;
    public Colour colour;
    public Position position;

    public Particle(Type type, Colour colour, Position position) {
        this.type = type;
        this.colour = colour;
        this.position = position;
    }

    public Position[] getPossibleMoves() {
        this.position.x += 0;
        this.position.y -= 1;
        this.position.z += 1;

        Position gpgp = new Position(1,2,3);
        Position returnlist[] = {gpgp};
        return returnlist;
    }

}
