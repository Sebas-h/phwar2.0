package game;

import algorithm.*;
import gui.BoardPanel;
import gui.BoardUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static game.Particle.getCoordinateName;


public class Game {
    public static int boardGridSize = 5;

    private State gameState;
    private Algorithm black;
    private Algorithm white;

    private int turn = 1;
    private ArrayList<ArrayList<Move>> history = new ArrayList<>();

    public Game(Algorithm playerBlack, Algorithm playerWhite){
        createStartState();
        this.black = playerBlack;
        this.white = playerWhite;
    }

    public void createStartState(){
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
        for (int i = particles.size()-1; i > -1; i--) {
            particles.add(new Particle(particles.get(i).charge, Colour.WHITE, new int[] {
                    particles.get(i).coordinate[0] * -1, particles.get(i).coordinate[1] * -1,
                    particles.get(i).coordinate[2] * -1}));
        }
        this.gameState = new State(particles, false);
    }

    private void testStateForCapture(){
        ArrayList<Particle> particles = new ArrayList<>(Arrays.asList(
            new Particle(0 , Colour.BLACK, new int[] {0,5,-5 }),
            new Particle(-1, Colour.BLACK, new int[] {-1,1,0 }),
            new Particle(1 , Colour.BLACK, new int[] {-1,-2,3}),
            new Particle(0 , Colour.WHITE, new int[] {0,-5,5 }),
            new Particle(-1, Colour.WHITE, new int[] {-1,4,-3}),
            new Particle(-1, Colour.WHITE, new int[] {-4,1,3 }),
            new Particle(1 , Colour.WHITE, new int[] {2,-2,0 })
        ));
        this.gameState = new State(particles, false);
    }

    public void play(){
        Algorithm currentPlayer = this.black;
        while(!this.gameState.terminal){
            // add a state to history; when 'undo' set state (var) to state from history;
            // this might be easier then remembering moves;
            // then you would have to calculate backwards; a state you can just apply straight up;
            //this.history.add(moves); // maybe keep it limited the last 10 moves

            long startTime = System.nanoTime();

            ArrayList<Move> moves = currentPlayer.getAction(this.gameState);

            long duration = (System.nanoTime() - startTime);
            System.out.println("turn " + this.turn + ": " + duration / 1000000 + " ms"); // milliseconds
            System.out.println(moves.get(0).particle.colour + ": " + printMoves(moves) + "\n");

            this.gameState.update(moves);
            this.gameState.checkTerminal(currentPlayer.playerColour);

            if(currentPlayer == black) currentPlayer = white;
            else currentPlayer = black;

            this.turn += 1;
        }
        System.out.println("\nWinner = " + currentPlayer.opponentColour); // because of the switch in current player;
    }

    private String printMoves(List<Move> moves){
        StringBuilder res = new StringBuilder();
        for (Move move : moves) {
            res.append(getCoordinateName(move.particle.coordinate))
                    .append(" -> ")
                    .append(getCoordinateName(move.destination))
                    .append("; ");
        }
        return res.toString();
    }

    public static void main (String args[]){
        //BoardUI boardUI = new BoardUI();
        //javax.swing.SwingUtilities.invokeLater(boardUI::createAndShowGUI);

        Game game = new Game(
                new Human(Colour.BLACK),
                new RandomPlayer(Colour.WHITE)
        );
        game.createStartState();
        game.play();
    }
}
