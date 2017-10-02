package algorithm;

import game.Move;
import state.Colour;
import state.Particle;
import state.State;

import java.util.ArrayList;

public class Human extends Algorithm {

    public Human(ArrayList<Particle> particles, Colour colour) {
        super(particles, colour);
    }

    @Override
    public Score evaluateState(State s) {
        return null;
    }

    @Override
    public ArrayList<Move> getMove() {
        // Ask for a move through a textual or graphical interface

        return null;
    }
}
