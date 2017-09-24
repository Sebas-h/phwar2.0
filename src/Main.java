import GameState.*;

import java.util.Scanner;

public class Main {

    public static void main (String args[]){

        /*
        State startState = new State();

        startState.particles.add(new Particle(ParticleType.NEUTRON, ParticleColour.BLACK, new GridPosition(0,5,-5)));

        startState.particles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(-2,5,-3)));
        startState.particles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(0,4,-4)));
        startState.particles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(0,3,-3)));
        startState.particles.add(new Particle(ParticleType.ELECTRON, ParticleColour.BLACK, new GridPosition(2,3,-5)));

        startState.particles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(-1,5,-4)));
        startState.particles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(-1,4,-3)));
        startState.particles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(1,4,-5)));
        startState.particles.add(new Particle(ParticleType.PROTON, ParticleColour.BLACK, new GridPosition(1,3,-4)));

        startState.particles.add(new Particle(ParticleType.NEUTRON, ParticleColour.WHITE, new GridPosition(0,-5,5)));

        startState.particles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(-2,-3,5)));
        startState.particles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(0,-4,4)));
        startState.particles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(0,-3,3)));
        startState.particles.add(new Particle(ParticleType.ELECTRON, ParticleColour.WHITE, new GridPosition(2,-5,3)));

        startState.particles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(-1,-4,5)));
        startState.particles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(-1,-3,4)));
        startState.particles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(1,-5,4)));
        startState.particles.add(new Particle(ParticleType.PROTON, ParticleColour.WHITE, new GridPosition(1,-4,3)));
        */



        /*
        -5 = a
        -4 = b
        .
        .
        .
        5 = k
         */

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a number: ");
        String unu = reader.next();
//        int n = reader.nextInt();
//        System.out.println(n);
        System.out.println(unu);


    }
}
