package Algorithm;

import GameState.*;

import java.util.ArrayList;

public class Main {

    static public void main (String args[]){

        State startState = new State();

        ArrayList<Particle> children = new ArrayList<>();

        for (Particle particle : startState.blackParticles) {
            particle.getChildren();
        }

        // Get children
            // check if end state, give evaluation score
            // stop building tree after n depth ??

        // Evaluate
            // simplistic, count numbers of friendly pieces, the more the higher the eval score
    }

    public void Hi(){
        System.out.println("hi");
    }
}
