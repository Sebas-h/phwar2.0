package state;

import game.Game;
import game.Move;

import java.util.ArrayList;

public class State {

    public ArrayList<Particle> particles = new ArrayList<>();
    public Boolean terminal;
    public State parent;
    public ArrayList<Move> priorMoves;

    public State(ArrayList<Particle> particles, Boolean terminal, State parent, ArrayList<Move> priorMoves){
        this.particles = particles;
        this.terminal = terminal;
        this.parent = parent;
        this.priorMoves = priorMoves;
    }

    public State(State state, ArrayList<Move> priorMoves){
        this.parent = state;
        this.terminal = false;
        this.particles = this.copyParticles(state.particles);
        this.priorMoves = priorMoves;
    }

    private ArrayList<Particle> copyParticles(ArrayList<Particle> particles) {
        ArrayList<Particle> particlesCopy = new ArrayList<>();
        for (Particle particle : particles) {
            particlesCopy.add(new Particle(particle.charge, particle.colour, new Position(particle.position)));
        }
        return particlesCopy;
    }

    public void update(ArrayList<Move> moves) {
        if(moves == null){
            // return exception, this should not happen
            return;
        }

        for (Move move : moves) {
            // Check validity; Only check validity of move when against human?
            // todo: this.checkMove(moves); // move outside for loop

            // Apply move
            for (Particle particle : this.particles) {
                if(particle.position.x == move.particle.position.x && particle.position.y == move.particle.position.y) {
                    if (move.destination == null) this.particles.remove(particle);
                    else particle.position = new Position(move.destination.x, move.destination.y, move.destination.z);
                }
            }
        }
    }

    private boolean checkMove(ArrayList<Move> moves) {
        // Apply game rules check:
        for (Move move : moves) {
            if (!isInBoard(move)) return false;
            if (move.destination != null && !isPositionOccupied(move)) return false;
            if (move.destination == null && !isOverallChargeZero(move)) return false;
        }

        return true;
    }

    private boolean isOverallChargeZero(Move move) {
        ArrayList<Particle> particlesInLineOfSight = getParticlesLineOfSight(move.destination);

        return true;
    }

    private ArrayList<Particle> getParticlesLineOfSight(Position destination) {
        for (Particle particle : particles) {

        }

        return null;
    }

    private boolean isPositionOccupied(Move move) {
        for (Particle particle : particles) {
            if(particle.position == move.destination) return false;
        }
        return true;
    }

    private boolean isInBoard(Move move) {
        return (move.destination.x >= -Game.gridSize && move.destination.x <= Game.gridSize) &&
                (move.destination.y >= -Game.gridSize && move.destination.y <= Game.gridSize) &&
                (move.destination.z >= -Game.gridSize && move.destination.z <= Game.gridSize) &&
                (move.destination.x + move.destination.y + move.destination.z == 0);
    }

    public Particle getParticleClosest(Particle particle, Direction direction){

        return null;
    }

    @Override
    public String toString() {
        return "State{" +
                ", terminal=" + terminal +
                ", particles=" + particles +
                '}' + '\n';
    }

    public ArrayList<Move> getPossibleMovesParticle(Particle particle){
        ArrayList<Move> moves = new ArrayList<>();
        for (int[] direction : Direction.directions) {
            for (int i = 1; i < numberOfUnobstructedMoves(particle, direction); i++) {
                moves.add(new Move(particle, new int[] {
                        particle.coordinate[0] + (direction[0] * i),
                        particle.coordinate[1] + (direction[1] * i),
                        particle.coordinate[2] + (direction[2] * i)}));
            }
        }
        return moves;
    }

    private int numberOfUnobstructedMoves(Particle particle, int[] direction) {
        /**
         *   O
         *   -  O
         *   - -
         *   O      <-- can move up twice and upright once
         *
         * loop over particles (instead of looping at every step to check if coordinate not occupied)
         * get the one with direction 1
         *
         */
        int indexZero = 3;
        int indexPosOne = 3;
        int indexNegOne = 3;
        for (int i = 0; i < direction.length; i++) {
            if(direction[i] == 0) indexZero = i;
            else if(direction[i] == 1) indexPosOne = i;
            else if(direction[i] == -1) indexNegOne = i;
        }
        if (indexZero == 3 || indexPosOne == 3 || indexNegOne == 3)
            throw new IllegalArgumentException("Parameter incorrect!");
        
        int min = 10;
        boolean particleEncountered = false;
        for (Particle otherParticle : this.particles) {
            if(particle != otherParticle && 
                    particle.coordinate[indexZero] == otherParticle.coordinate[indexZero] &&
                    particle.coordinate[indexPosOne] < otherParticle.coordinate[indexPosOne]) {
                particleEncountered = true;
                int dif = otherParticle.coordinate[indexPosOne] - particle.coordinate[indexPosOne];
                if(dif < min) min = dif;
            }
        }
        
        if(!particleEncountered) {
            int i = 1;
            do {
                i++;
            } while (Math.abs(particle.coordinate[indexPosOne] + i) < 6 ||
                    Math.abs(particle.coordinate[indexNegOne] - i) < 6);
            return i;
        }
        
        return min;
    }
}