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
        ArrayList<State> children = AlgorithmUtil.getChildren(s, super.playerColour);
        Random rand = new Random(); // gives different seed every time it's called (using System.nanoTime())
        return children.get(rand.nextInt(children.size())).priorMoves;
    }
}
