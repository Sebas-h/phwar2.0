package game;

import algorithm.Algorithm;
import algorithm.Minimax;

import java.util.ArrayList;

public class Game {
    public static int boardGridSize = 5;

    private State gameState;
    private Algorithm black;
    private Algorithm white;

    private int round = 1;
    private ArrayList<ArrayList<Move>> history = new ArrayList<>();

    private void createStartState(){
        ArrayList<Particle> particles = new ArrayList<>();
        particles.add(new Particle(0 , Colour.BLACK, new int[] {0,5,-5 }));
        particles.add(new Particle(-1, Colour.BLACK, new int[] {-2,5,-3}));
        particles.add(new Particle(-1, Colour.BLACK, new int[] {0,4,-4 }));
        particles.add(new Particle(-1, Colour.BLACK, new int[] {0,3,-3 }));
        particles.add(new Particle(-1, Colour.BLACK, new int[] {2,3,-5 }));
        particles.add(new Particle(1 , Colour.BLACK, new int[] {-1,5,-4}));
        particles.add(new Particle(1 , Colour.BLACK, new int[] {-1,4,-3}));
        particles.add(new Particle(1 , Colour.BLACK, new int[] {1,4,-5 }));
        particles.add(new Particle(1 , Colour.BLACK, new int[] {1,3,-4 }));
        particles.add(new Particle(0 , Colour.WHITE, new int[] {0,-5,5 }));
        particles.add(new Particle(-1, Colour.WHITE, new int[] {-2,-3,5}));
        particles.add(new Particle(-1, Colour.WHITE, new int[] {0,-4,4 }));
        particles.add(new Particle(-1, Colour.WHITE, new int[] {0,-3,3 }));
        particles.add(new Particle(-1, Colour.WHITE, new int[] {2,-5,3 }));
        particles.add(new Particle(1 , Colour.WHITE, new int[] {-1,-4,5}));
        particles.add(new Particle(1 , Colour.WHITE, new int[] {-1,-3,4}));
        particles.add(new Particle(1 , Colour.WHITE, new int[] {1,-5,4 }));
        particles.add(new Particle(1 , Colour.WHITE, new int[] {1,-4,3 }));
        this.gameState = new State(particles, false,null,null);
        this.black = new Minimax(particles,Colour.BLACK);
        this.white = new Minimax(particles,Colour.WHITE);
    }

    private State testStateForCapture(){
        ArrayList<Particle> particles = new ArrayList<>();
        particles.add(new Particle(0 , Colour.BLACK, new int[] {0,5,-5 }));
        particles.add(new Particle(-1, Colour.BLACK, new int[] {-1,1,0 }));
        particles.add(new Particle(1 , Colour.BLACK, new int[] {-1,-2,3}));
        particles.add(new Particle(0 , Colour.WHITE, new int[] {0,-5,5 }));
        particles.add(new Particle(-1, Colour.WHITE, new int[] {-1,4,-3}));
        particles.add(new Particle(-1, Colour.WHITE, new int[] {-4,1,3 }));
        particles.add(new Particle(1 , Colour.WHITE, new int[] {4,-4,0 }));
        return new State(particles,false,null,null);
    }

    private void play(){

        Algorithm currentPlayer = this.black;

        while(!this.gameState.terminal){
            // add a state to history; when 'undo' set state (var) to state from history;
            // this might be easier then remembering moves;
            // then you would have to calculate backwards; a state you can just apply straight up;
            //this.history.add(moves); // maybe keep it limited the last 10 moves

            ArrayList<Move> moves = currentPlayer.getAction(this.gameState);

            this.gameState.update(moves);

            if(currentPlayer == black) currentPlayer = white;
            else currentPlayer = black;

            this.round += 1;
        }
    }

    public static void main (String args[]){
        Game game = new Game();
        game.createStartState();
        game.play();
    }
}
