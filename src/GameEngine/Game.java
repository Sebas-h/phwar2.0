package GameEngine;

import GameState.*;

import java.util.Scanner;

public class Game {

    // create start state

    // play game

    //


    public static void main (String args[]){

        State startState = new State();
        startState.createStartState(ParticleColour.BLACK);

        startState.parent = new State();
        startState.blackParticles.get(1);

        // Input moves textually:
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a number: ");
        String unu = reader.next();
//        int n = reader.nextInt();
//        System.out.println(n);
        System.out.println(unu);


    }
}
