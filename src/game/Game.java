package game;

import algorithm.Minimax;
import state.*;

import java.util.ArrayList;

public class Game {

    private int round = 1;
    private ArrayList<ArrayList<Move>> history = new ArrayList<>();

    private State createStartState(){
        ArrayList<Particle> particles = new ArrayList<>();
        particles.add(new Particle(Type.NEUTRON, Colour.BLACK, new Position(0,5,-5)));
        particles.add(new Particle(Type.ELECTRON, Colour.BLACK, new Position(-2,5,-3)));
        particles.add(new Particle(Type.ELECTRON, Colour.BLACK, new Position(0,4,-4)));
        particles.add(new Particle(Type.ELECTRON, Colour.BLACK, new Position(0,3,-3)));
        particles.add(new Particle(Type.ELECTRON, Colour.BLACK, new Position(2,3,-5)));
        particles.add(new Particle(Type.PROTON, Colour.BLACK, new Position(-1,5,-4)));
        particles.add(new Particle(Type.PROTON, Colour.BLACK, new Position(-1,4,-3)));
        particles.add(new Particle(Type.PROTON, Colour.BLACK, new Position(1,4,-5)));
        particles.add(new Particle(Type.PROTON, Colour.BLACK, new Position(1,3,-4)));
        particles.add(new Particle(Type.NEUTRON, Colour.WHITE, new Position(0,-5,5)));
        particles.add(new Particle(Type.ELECTRON, Colour.WHITE, new Position(-2,-3,5)));
        particles.add(new Particle(Type.ELECTRON, Colour.WHITE, new Position(0,-4,4)));
        particles.add(new Particle(Type.ELECTRON, Colour.WHITE, new Position(0,-3,3)));
        particles.add(new Particle(Type.ELECTRON, Colour.WHITE, new Position(2,-5,3)));
        particles.add(new Particle(Type.PROTON, Colour.WHITE, new Position(-1,-4,5)));
        particles.add(new Particle(Type.PROTON, Colour.WHITE, new Position(-1,-3,4)));
        particles.add(new Particle(Type.PROTON, Colour.WHITE, new Position(1,-5,4)));
        particles.add(new Particle(Type.PROTON, Colour.WHITE, new Position(1,-4,3)));
        return new State(particles,false,null);
    }

    private void play(){
        State state = createStartState();

        Player black = new Minimax(state.particles, Colour.BLACK);
        Player white = new Minimax(state.particles, Colour.WHITE);

        Player currentPlayer = black;

        while(!state.terminal){
            ArrayList<Move> moves;
            moves = currentPlayer.getMove();

            state.update(moves);

            this.history.add(moves); // maybe keep it limited the last 10 moves

            if(currentPlayer == black) currentPlayer = white;
            else currentPlayer = black;

            this.round += 1;
        }
    }

    public static void main (String args[]){
        Game game = new Game();
        game.play();
    }
}
