package game;

import algorithm.Minimax;
import state.*;

import java.util.ArrayList;

public class Game {

    private int round = 0;
    private ArrayList<Move> history;

    private State createStartState(){
        State startState = new State();

        startState.particles.put(new Particle(Type.NEUTRON, Colour.BLACK, new GridPosition(0,5,-5)), Colour.BLACK);

        startState.blackParticles.add(new Particle(Type.NEUTRON, Colour.BLACK, new GridPosition(0,5,-5)));
        startState.blackParticles.add(new Particle(Type.ELECTRON, Colour.BLACK, new GridPosition(-2,5,-3)));
        startState.blackParticles.add(new Particle(Type.ELECTRON, Colour.BLACK, new GridPosition(0,4,-4)));
        startState.blackParticles.add(new Particle(Type.ELECTRON, Colour.BLACK, new GridPosition(0,3,-3)));
        startState.blackParticles.add(new Particle(Type.ELECTRON, Colour.BLACK, new GridPosition(2,3,-5)));
        startState.blackParticles.add(new Particle(Type.PROTON, Colour.BLACK, new GridPosition(-1,5,-4)));
        startState.blackParticles.add(new Particle(Type.PROTON, Colour.BLACK, new GridPosition(-1,4,-3)));
        startState.blackParticles.add(new Particle(Type.PROTON, Colour.BLACK, new GridPosition(1,4,-5)));
        startState.blackParticles.add(new Particle(Type.PROTON, Colour.BLACK, new GridPosition(1,3,-4)));
        startState.whiteParticles.add(new Particle(Type.NEUTRON, Colour.WHITE, new GridPosition(0,-5,5)));
        startState.whiteParticles.add(new Particle(Type.ELECTRON, Colour.WHITE, new GridPosition(-2,-3,5)));
        startState.whiteParticles.add(new Particle(Type.ELECTRON, Colour.WHITE, new GridPosition(0,-4,4)));
        startState.whiteParticles.add(new Particle(Type.ELECTRON, Colour.WHITE, new GridPosition(0,-3,3)));
        startState.whiteParticles.add(new Particle(Type.ELECTRON, Colour.WHITE, new GridPosition(2,-5,3)));
        startState.whiteParticles.add(new Particle(Type.PROTON, Colour.WHITE, new GridPosition(-1,-4,5)));
        startState.whiteParticles.add(new Particle(Type.PROTON, Colour.WHITE, new GridPosition(-1,-3,4)));
        startState.whiteParticles.add(new Particle(Type.PROTON, Colour.WHITE, new GridPosition(1,-5,4)));
        startState.whiteParticles.add(new Particle(Type.PROTON, Colour.WHITE, new GridPosition(1,-4,3)));
        return startState;
    }

    private void play(){
        State state = this.createStartState();

        Player black = new Minimax();
        Player white = new Minimax();

        Colour currentPlayer = Colour.BLACK;

        while(!state.terminalState){

            Move move;
            if (currentPlayer == Colour.BLACK) move = black.getMove();
            else move = white.getMove();

            state.update(move, currentPlayer);
            this.history.add(move); // maybe keep it limited the last 10 moves

            if(currentPlayer == Colour.BLACK) currentPlayer = Colour.WHITE;
            else currentPlayer = Colour.BLACK;

            this.round += 1;
        }

    }

    public static void main (String args[]){
        Game game = new Game();
        game.play();
    }
}
