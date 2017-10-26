package algorithm.evaluation;

import game.Colour;
import game.State;

public interface IEvaluation {
    Score evaluate(State state, Colour povColour);
}
