package algorithm;

import game.*;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Algorithm {

    public Colour playerColour;
    public Colour opponentColour;

    Algorithm(Colour colour) {
        this.playerColour = colour;
        if (colour == Colour.BLACK) opponentColour = Colour.WHITE;
        else opponentColour = Colour.BLACK;
    }

    public abstract ArrayList<Move> getAction(State s);

}