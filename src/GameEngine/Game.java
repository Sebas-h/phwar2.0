package GameEngine;

import GameState.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private int Round = 0;
    private boolean GameEnd;

    private State createStartState(){
        State startState = new State();
        startState.blackParticles.add(new Particle(ParticleType.NEUTRON, ParticleColour.BLACK, new GridPosition(0,5,-5)));
        startState.blackParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(-2,5,-3)));
        startState.blackParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(0,4,-4)));
        startState.blackParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(0,3,-3)));
        startState.blackParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(2,3,-5)));
        startState.blackParticles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(-1,5,-4)));
        startState.blackParticles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(-1,4,-3)));
        startState.blackParticles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(1,4,-5)));
        startState.blackParticles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(1,3,-4)));
        startState.whiteParticles.add(new Particle(ParticleType.NEUTRON, ParticleColour.WHITE, new GridPosition(0,-5,5)));
        startState.whiteParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(-2,-3,5)));
        startState.whiteParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(0,-4,4)));
        startState.whiteParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(0,-3,3)));
        startState.whiteParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(2,-5,3)));
        startState.whiteParticles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(-1,-4,5)));
        startState.whiteParticles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(-1,-3,4)));
        startState.whiteParticles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(1,-5,4)));
        startState.whiteParticles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(1,-4,3)));
        return startState;
    }

    public Boolean gameEnd(State s){
        return true;
    }

    private void play(){
        Player player1 = new Player();
        Player player2 = new Player();
        State state = this.createStartState();

        while(!state.terminalState){


            this.Round += 1;
        }
    }

    public static void main (String args[]){
        Game game = new Game();
        game.play();
    }
}
