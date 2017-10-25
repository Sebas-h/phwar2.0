package algorithm;

import game.Move;
import game.Colour;
import game.Particle;
import game.State;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static game.Game.boardGridSize;

public class Human extends Algorithm {

    HashMap<Character,Integer> labelToNum;

    public Human(Colour colour) {
        super(colour);
        // map label to x coord
        this.labelToNum = new HashMap<>();
        labelToNum.put('A',-5);
        labelToNum.put('B',-4);
        labelToNum.put('C',-3);
        labelToNum.put('D',-2);
        labelToNum.put('E',-1);
        labelToNum.put('F',0);
        labelToNum.put('G',1);
        labelToNum.put('H',2);
        labelToNum.put('I',3);
        labelToNum.put('J',4);
        labelToNum.put('K',5);
    }

    @Override
    public ArrayList<Move> getAction(State s) {
        ArrayList<Move> returnMoves;
        while(true) {
            ArrayList<Move> moves = new ArrayList<>();

            // Ask for a move through a textual or graphical interface
            String[] moveString = askMove();

            // Map label to coord:
            int[] src = getCoordFromLabel(moveString[0]);
            int[] dest = getCoordFromLabel(moveString[1]);

            // find src particle:
            Particle particle = new Particle();
            for (Particle p : s.particles) {
                if (Arrays.equals(p.coordinate, src)) {
                    particle = new Particle(p);
                    break;
                }
            }

            // add move to be executed:
            moves.add(new Move(particle, dest, false));

            // check captures:
            ArrayList<State> nStates = applyMoves(s, moves);
            if (getCaptureMoves(nStates.get(0), super.playerColour).size() == 0) {
                returnMoves = moves;
                break;
            }
        } 
        return returnMoves;
    }

    String[] askMove(){
        System.out.println("Src: ");
        Scanner scanner = new Scanner(System.in);
        String src = scanner.nextLine().toUpperCase();
        System.out.println("Dest: ");
        String dest = scanner.nextLine().toUpperCase();
        System.out.println("Move = " + src + " to " + dest + "\n");
        return new String[] {src,dest};
    }

    int[] getCoordFromLabel(String label){
        int[] coord = new int[3];
        coord[0] = labelToNum.get(label.charAt(0));

        String[] rowNumSplit = label.split("[a-zA-Z]");
        int row = Integer.parseInt(rowNumSplit[1])  - boardGridSize - 1;
        int z = row - ((coord[0] - (coord[0]&1)) / 2);
        coord[2] = z;

        coord[1] = -coord[0] - z;

        return coord;
    }

}
