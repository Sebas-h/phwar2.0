package algorithm;

import game.Player;
import state.Colour;
import state.Particle;
import state.State;

import java.util.ArrayList;

public abstract class Algorithm extends Player {


    public Algorithm(ArrayList<Particle> particles, Colour colour) {
        super(particles, colour);
    }

    public abstract Score evaluateState(State s);
}
