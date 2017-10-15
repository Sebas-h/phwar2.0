package algorithm;

import game.Move;
import game.Colour;
import game.Particle;
import game.State;

import java.util.ArrayList;

public class Human extends Algorithm {

    public Human(ArrayList<Particle> particles, Colour colour) {
        super(colour);
    }

    @Override
    public ArrayList<Move> getAction(State s) {
        // Ask for a move through a textual or graphical interface

        return null;
    }
}
