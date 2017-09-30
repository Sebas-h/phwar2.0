package GameState;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class State {

    public State parent;

    public Boolean terminalState;

    public ParticleColour proponentColour;

    public Integer score;

    public ArrayList<GridPosition> priorActionSequence = new ArrayList<>();

    public ArrayList<Particle> blackParticles = new ArrayList<>();

    public ArrayList<Particle> whiteParticles = new ArrayList<>();

    public void createStartState(ParticleColour proponentColour){
        this.parent = null;
        this.terminalState = false;
        this.proponentColour = proponentColour;
        this.priorActionSequence = null;
        this.score = null;
    }

    public ArrayList<State> getChildrenProponent() {
        if(this.proponentColour.equals(ParticleColour.BLACK)){
            return getChildrenBlackParticles();
        }
        return getChildrenWhiteParticles();
    }

    public ArrayList<State> getChildrenOpponent() {
        if(this.proponentColour.equals(ParticleColour.BLACK)){
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

    public static void main(String args[]){
        GridPosition x = new GridPosition(3,4,4);
        GridPosition y = new GridPosition(x);

        ParticleColour z = ParticleColour.WHITE;

        if(z.equals(ParticleColour.BLACK)){
            System.out.println("nee niet goed");
        }

        if(z.equals(ParticleColour.WHITE)){
            System.out.println("goodie :D :D :D");
        }
    }

}
