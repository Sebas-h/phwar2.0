package state;

import game.Move;

import java.util.ArrayList;
import java.util.HashMap;

public class State {

    public State parent;

    public Colour proponentColour;

    public Integer score;

    public ArrayList<GridPosition> priorActionSequence = new ArrayList<>();

    public Boolean terminalState;

    public ArrayList<Particle> blackParticles = new ArrayList<>();

    public ArrayList<Particle> whiteParticles = new ArrayList<>();

    public HashMap<Particle, Colour> particles = new HashMap<>();

    /*
    public void createStartState(Colour proponentColour){
        this.parent = null;
        this.terminalState = false;
        this.proponentColour = proponentColour;
        this.priorActionSequence = null;
        this.score = null;
    }

    public ArrayList<State> getChildrenProponent() {
        if(this.proponentColour.equals(Colour.BLACK)){
            return getChildrenBlackParticles();
        }
        return getChildrenWhiteParticles();
    }

    public ArrayList<State> getChildrenOpponent() {
        if(this.proponentColour.equals(Colour.BLACK)){
            return getChildrenWhiteParticles();
        }
        return getChildrenBlackParticles();
    }

    public ArrayList<State> getChildrenBlackParticles(){
        // Probably need to deep copy State right?
        ArrayList<State> children = new ArrayList<>();

        for (Particle p : this.blackParticles) {
            for (GridPosition newGp : p.getPossibleMoves()){
                p.gp = newGp; // does this actually work, replaces it?
                // copy this.state to child
                // change gp to newGp
                // add child
            }
        }

        throw new NotImplementedException();
    }

    public ArrayList<State> getChildrenWhiteParticles(){


        throw new NotImplementedException();
    }
    */

    public void update(Move move, Colour player) {
        // Check validity of move
        move.checkMove(this);

        // Apply move
        if (player == Colour.BLACK){
            for (Particle blackParticle : this.blackParticles) {
                if (blackParticle.gp == move.start){
                    blackParticle.gp = move.end;
                    if (move.capture != null){
                        for (Particle whiteParticle : whiteParticles) {
                            if(whiteParticle.gp == move.capture.start){
                                whiteParticle.gp = move.capture.end;
                            }
                        }
                    }
                }
            }
        } else {
            for (Particle blackParticle : this.blackParticles) {
                if (blackParticle.gp == move.start){
                    blackParticle.gp = move.end;
                    if (move.capture != null){
                        for (Particle whiteParticle : whiteParticles) {
                            if(whiteParticle.gp == move.capture.start){
                                whiteParticle.gp = move.capture.end;
                            }
                        }
                    }
                }
            }
        }
    }
}
