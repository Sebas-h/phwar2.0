package algorithm;

import game.Player;
import state.State;

public abstract class Algorithm extends Player {

    public abstract Score evaluateState(State s);
}
