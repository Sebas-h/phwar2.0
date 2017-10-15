package algorithm;

import game.Colour;
import game.Move;
import game.State;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends Algorithm {
    public RandomPlayer(Colour colour) {
        super(colour);
    }

    @Override
    public ArrayList<Move> getAction(State s) {
        ArrayList<State> children = getChildren(s, super.playerColour);
        // seed guarantees different pseudo random sequence for every Random instance:
        Random rand = new Random(System.nanoTime());
        return children.get(rand.nextInt(children.size())).priorMoves;
    }
}
