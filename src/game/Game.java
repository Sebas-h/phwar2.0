package game;

import algorithm.Minimax;
import state.*;

import java.util.ArrayList;

public class Game {
    public static int gridSize = 5;

    private int round = 1;
    private ArrayList<ArrayList<Move>> history = new ArrayList<>();

    private State createStartState(){
        ArrayList<Particle> particles = new ArrayList<>();
        particles.add(new Particle(0 , Colour.BLACK, new Position(0,5,-5)));
        particles.add(new Particle(-1, Colour.BLACK, new Position(-2,5,-3)));
        particles.add(new Particle(-1, Colour.BLACK, new Position(0,4,-4)));
        particles.add(new Particle(-1, Colour.BLACK, new Position(0,3,-3)));
        particles.add(new Particle(-1, Colour.BLACK, new Position(2,3,-5)));
        particles.add(new Particle(1 , Colour.BLACK, new Position(-1,5,-4)));
        particles.add(new Particle(1 , Colour.BLACK, new Position(-1,4,-3)));
        particles.add(new Particle(1 , Colour.BLACK, new Position(1,4,-5)));
        particles.add(new Particle(1 , Colour.BLACK, new Position(1,3,-4)));
        particles.add(new Particle(0 , Colour.WHITE, new Position(0,-5,5)));
        particles.add(new Particle(-1, Colour.WHITE, new Position(-2,-3,5)));
        particles.add(new Particle(-1, Colour.WHITE, new Position(0,-4,4)));
        particles.add(new Particle(-1, Colour.WHITE, new Position(0,-3,3)));
        particles.add(new Particle(-1, Colour.WHITE, new Position(2,-5,3)));
        particles.add(new Particle(1 , Colour.WHITE, new Position(-1,-4,5)));
        particles.add(new Particle(1 , Colour.WHITE, new Position(-1,-3,4)));
        particles.add(new Particle(1 , Colour.WHITE, new Position(1,-5,4)));
        particles.add(new Particle(1 , Colour.WHITE, new Position(1,-4,3)));
        return new State(particles,false,null);
    }

    private void play(){
        State state = createStartState();

        Player black = new Minimax(state.particles, Colour.BLACK);
        Player white = new Minimax(state.particles, Colour.WHITE);

        Player currentPlayer = black;

        while(!state.terminal){
            // add a state to history; when 'undo' set state (var) to state from history;
            // this might be easier then remembering moves;
            // then you would have to calculate backwards; a state you can just apply straight up;
            //this.history.add(moves); // maybe keep it limited the last 10 moves



            ArrayList<Move> moves;
            moves = currentPlayer.getMoves(state);

            //state.update(moves);

            if(currentPlayer == black) currentPlayer = white;
            else currentPlayer = black;

            this.round += 1;
        }
    }

    public static void main (String args[]){
        Game game = new Game();
        game.play();
    }

    /*
    private static int row(int x, int z){
        return z + ((x - (x&1)) / 2);
    }

    {
        int x[] = {5, 5,-4, 4,-4,1};
        int z[] = {0,-5, 1,-3, 3,1};
        for (int i = 0; i < x.length; i++) {
            int row = row(x[i], z[i]);
            int beginRow = Math.abs(x[i])/2 + 1;
            System.out.println((row + gridSize +1) - beginRow);
        }
    }
    */
}
