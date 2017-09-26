package GameState;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class State {

    public State parent;

    public Boolean terminalState;

    public ParticleColour proponentColour;

    public ArrayList<Particle> blackParticles = new ArrayList<>();

    public ArrayList<Particle> whiteParticles = new ArrayList<>();

    public void createStartState(ParticleColour proponentColour){
        this.parent = null;
        this.terminalState = false;
        this.proponentColour = proponentColour;

        this.blackParticles.add(new Particle(ParticleType.NEUTRON, ParticleColour.BLACK, new GridPosition(0,5,-5)));

        this.blackParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(-2,5,-3)));
        this.blackParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(0,4,-4)));
        this.blackParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(0,3,-3)));
        this.blackParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(2,3,-5)));

        this.blackParticles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(-1,5,-4)));
        this.blackParticles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(-1,4,-3)));
        this.blackParticles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(1,4,-5)));
        this.blackParticles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(1,3,-4)));

        this.whiteParticles.add(new Particle(ParticleType.NEUTRON, ParticleColour.WHITE, new GridPosition(0,-5,5)));

        this.whiteParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(-2,-3,5)));
        this.whiteParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(0,-4,4)));
        this.whiteParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(0,-3,3)));
        this.whiteParticles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(2,-5,3)));

        this.whiteParticles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(-1,-4,5)));
        this.whiteParticles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(-1,-3,4)));
        this.whiteParticles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(1,-5,4)));
        this.whiteParticles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(1,-4,3)));
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
        // probs need to deep copy State right?
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
