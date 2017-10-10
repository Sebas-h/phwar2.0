package algorithm;

import game.Move;
import game.Player;
import state.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static game.Game.gridSize;

public abstract class Algorithm extends Player {

    public Algorithm(ArrayList<Particle> particles, Colour colour) {
        super(particles, colour);
    }

    public abstract Score evaluate(State s);

    public ArrayList<State> getChildren(State state) {

        ArrayList<State> children = new ArrayList<>();

        // todo: add move ordering on proponent list; that could work right?

        int index = 0;
        for (Particle toMove : state.particles) {
            if (toMove.colour == super.colour) {
                int upY = 11;
                int downZ = 11;
                int upRightX = 11;
                int downLeftZ = 11;
                int downRightX = 11;
                int upLeftY = 11;

                for (Particle particle : state.particles) {
                    if (toMove != particle) {
                        // Up
                        if (toMove.position.x == particle.position.x &&
                                (particle.position.y > toMove.position.y && particle.position.y < upY)) {
                            upY = particle.position.y;
                        }
                        // Down
                        else if (toMove.position.x == particle.position.x &&
                                (particle.position.z > toMove.position.z && particle.position.z < downZ)) {
                            downZ = particle.position.z;
                        }
                        // UpRight
                        else if (toMove.position.y == particle.position.y &&
                                (particle.position.x > toMove.position.x && particle.position.x < upRightX)) {
                            upRightX = particle.position.x;
                        }
                        // DownLeft
                        else if (toMove.position.y == particle.position.y &&
                                (particle.position.z > toMove.position.z && particle.position.z < downLeftZ)) {
                            downLeftZ = particle.position.z;
                        }
                        // DownRight
                        else if (toMove.position.z == particle.position.z &&
                                (particle.position.x > toMove.position.x && particle.position.x < downRightX)) {
                            downRightX = particle.position.x;
                        }
                        // UpLeft
                        else if (toMove.position.z == particle.position.z &&
                                (particle.position.y > toMove.position.y && particle.position.y < upLeftY)) {
                            upLeftY = particle.position.y;
                        }
                    }
                }

                // Apply "Have to stop at F6" rule:
                if (toMove.position.x == 0 && toMove.position.y < 0 && upY > 1) upY = 1;
                else if (toMove.position.x == 0 && toMove.position.z < 0 && downZ > 1) downZ = 1;
                else if (toMove.position.y == 0 && toMove.position.x < 0 && upRightX > 1) upRightX = 1;
                else if (toMove.position.y == 0 && toMove.position.z < 0 && downLeftZ > 1) downLeftZ = 1;
                else if (toMove.position.z == 0 && toMove.position.x < 0 && downRightX > 1) downRightX = 1;
                else if (toMove.position.z == 0 && toMove.position.y < 0 && upLeftY > 1) upLeftY = 1;

                // New states
                // UP:
                for (int y = toMove.position.y + 1, z = toMove.position.z - 1;
                     y < upY && Math.abs(y) < 6 && Math.abs(z) < 6; y++, z--) {
                    Position newPos = new Position(toMove.position.x, y, z);

                    ArrayList<Move> priorMoves = new ArrayList<>();
                    priorMoves.add(new Move(toMove, newPos));
                    State newState = new State(state, priorMoves);
                    newState.particles.get(index).position = newPos;
                    ArrayList<State> newStates = applyCaptureMoves(newState);
                    checkTerminal(state);
                    children.addAll(newStates);
                }
                // DOWN:
                for (int y = toMove.position.y - 1, z = toMove.position.z + 1;
                     z < downZ && Math.abs(y) < 6 && Math.abs(z) < 6; z++, y--) {
                    Position newPos = new Position(toMove.position.x, y, z);

                    ArrayList<Move> priorMoves = new ArrayList<>();
                    priorMoves.add(new Move(toMove, newPos));
                    State newState = new State(state, priorMoves);
                    newState.particles.get(index).position = newPos;
                    ArrayList<State> newStates = applyCaptureMoves(newState);
                    checkTerminal(state);
                    children.addAll(newStates);
                }
                // UPRIGHT:
                for (int x = toMove.position.x + 1, z = toMove.position.z - 1;
                     x < upRightX && Math.abs(x) < 6 && Math.abs(z) < 6; z--, x++) {
                    Position newPos = new Position(x, toMove.position.y, z);

                    ArrayList<Move> priorMoves = new ArrayList<>();
                    priorMoves.add(new Move(toMove, newPos));
                    State newState = new State(state, priorMoves);
                    newState.particles.get(index).position = newPos;
                    ArrayList<State> newStates = applyCaptureMoves(newState);
                    checkTerminal(state);
                    children.addAll(newStates);
                }
                // DOWNLEFT:
                for (int x = toMove.position.x - 1, z = toMove.position.z + 1;
                     z < downLeftZ && Math.abs(x) < 6 && Math.abs(z) < 6;
                     z++, x--) {
                    Position newPos = new Position(x, toMove.position.y, z);

                    ArrayList<Move> priorMoves = new ArrayList<>();
                    priorMoves.add(new Move(toMove, newPos));
                    State newState = new State(state, priorMoves);
                    newState.particles.get(index).position = newPos;
                    ArrayList<State> newStates = applyCaptureMoves(newState);
                    checkTerminal(state);
                    children.addAll(newStates);
                }
                // DOWNRIGHT:
                for (int x = toMove.position.x + 1, y = toMove.position.y - 1;
                     x < downRightX && Math.abs(x) < 6 && Math.abs(y) < 6; y--, x++) {
                    Position newPos = new Position(x, y, toMove.position.z);

                    ArrayList<Move> priorMoves = new ArrayList<>();
                    priorMoves.add(new Move(toMove, newPos));
                    State newState = new State(state, priorMoves);
                    newState.particles.get(index).position = newPos;
                    ArrayList<State> newStates = applyCaptureMoves(newState);
                    checkTerminal(state);
                    children.addAll(newStates);
                }
                // UPLEFT:
                for (int x = toMove.position.x - 1, y = toMove.position.y + 1;
                     y < upLeftY && Math.abs(x) < 6 && Math.abs(y) < 6; y++, x--) {
                    Position newPos = new Position(x, y, toMove.position.z);

                    ArrayList<Move> priorMoves = new ArrayList<>();
                    priorMoves.add(new Move(toMove, newPos));
                    State newState = new State(state, priorMoves);
                    newState.particles.get(index).position = newPos;
                    ArrayList<State> newStates = applyCaptureMoves(newState);
                    checkTerminal(state);
                    children.addAll(newStates);
                }
            }
            // Makes it easier to apply new pos in (deep) copied state object "newState":
            index++;
        }
        return children;
    }

    private ArrayList<State> applyCaptureMoves(State state) {
        ArrayList<State> newStates = new ArrayList<>();
        int index = 0;
        for (Particle toCapture : state.particles) {
            Particle up = null;
            Particle down = null;
            Particle upRight = null;
            Particle downLeft = null;
            Particle downRight = null;
            Particle upLeft = null;

            ArrayList<Particle> lineOfSightParticles = new ArrayList<>();

            if(toCapture.colour != super.colour) {

                for (Particle particle : state.particles) {
                    if (toCapture != particle) {
                        //up
                        if (toCapture.position.x == particle.position.x && toCapture.position.y < particle.position.y) {
                            if (up == null || up.position.y > particle.position.y) up = particle;
                        }
                        //down
                        else if (toCapture.position.x == particle.position.x && toCapture.position.z < particle.position.z) {
                            if (down == null || down.position.z > particle.position.z) down = particle;
                        }
                        //upRight
                        else if (toCapture.position.y == particle.position.y && toCapture.position.x < particle.position.x) {
                            if (upRight == null || upRight.position.x > particle.position.x) upRight = particle;
                        }
                        //downLeft
                        else if (toCapture.position.y == particle.position.y && toCapture.position.z < particle.position.z) {
                            if (downLeft == null || downLeft.position.z > particle.position.z) downLeft = particle;
                        }
                        //downRight
                        else if (toCapture.position.z == particle.position.z && toCapture.position.x < particle.position.x) {
                            if (downRight == null || downRight.position.z > particle.position.z) downRight = particle;
                        }
                        //upLeft
                        else if (toCapture.position.z == particle.position.z && toCapture.position.y < particle.position.y) {
                            if (upLeft == null || upLeft.position.z > particle.position.z) upLeft = particle;
                        }
                    }
                }

                lineOfSightParticles.add(up);
                lineOfSightParticles.add(down);
                lineOfSightParticles.add(upRight);
                lineOfSightParticles.add(downLeft);
                lineOfSightParticles.add(downRight);
                lineOfSightParticles.add(upLeft);

                // check particles:
                int numberOfProponentParticles = 0;
                int totalCharge = 0;

                for (Particle particle : lineOfSightParticles) {
                    if (particle != null) {
                        if (particle.colour == super.colour) numberOfProponentParticles++;
                        totalCharge += particle.charge;
                    }
                }

                if (totalCharge == 0 &&numberOfProponentParticles > 1) {
                    for (Particle particle : lineOfSightParticles) {
                        if (particle != null) {
                            if (particle.colour == super.colour) {
                                Position newPos = new Position(toCapture.position.x, toCapture.position.y, toCapture.position.z);
                                ArrayList<Move> priorMoves = new ArrayList<>();
                                priorMoves.add(new Move(toCapture, newPos));
                                State newState = new State(state, priorMoves);
                                for (Particle newp : newState.particles) {
                                    if (particle.position.x == newp.position.x && particle.position.y == newp.position.y) {
                                        newp.position = newPos;
                                    }
                                }
                                newState.particles.remove(index);
                                newStates.add(newState);
                            }
                        }
                    }
                }
            }
            index++;
        }
        // No captures possible:
        if (newStates.size() == 0) newStates.add(state);
        return newStates;
    }

    private void checkTerminal(State state) {
        int protons = 0;
        int electrons = 0;
        int neutron = 0;
        // if neutron in f6; state.terminal = true and return;
        for (Particle particle : state.particles) {
            if(particle.charge == 0 && particle.position.x == 0 && particle.position.y == 0){
                state.terminal = true;
                return;
            }
            if (particle.colour != super.colour){
                if(particle.charge == 0) neutron++;
                else if(particle.charge == -1) electrons++;
                else if(particle.charge == 1) protons++;
            }
        }
        if(neutron == 0 || electrons == 0 || protons == 0) state.terminal = true;
    }

    private static int tilesToTopEdge(int x, int z){
        int i = ((z + ((x - (x&1)) / 2) + gridSize + 1));
        int j = ((Math.abs(x)/2) + 1);

        return ((z + ((x - (x&1)) / 2) + gridSize + 1) -
                ((Math.abs(x)/2) + 1));
    }



    /**
     * get possible moves for a particle: check each of six directions how far you can move;
     * make move object for all the cell you can move to.
     *
     * apply move? because you will have to do this anyway to check for (consecutive captures)
     *
     * check new state for captures: every sequence of captures has their own state
     * which means that a move for a particle (which is already a new state) can evolve to
     * 2 or more new-new states on the same ply as the new state (which gets removed, as capturing is mandatory)
     *
     * give back state objects (these are the moves the expanded particle can do)
     */


    /*
    function cube_to_oddq(cube):
        col = cube.x
        row = cube.z + (cube.x - (cube.x&1)) / 2
        return Hex(col, row)
          ____
         /    \
    ____/ 3,0  \____
   /    \      /    \
  /      \____/      \
  \      /    \      /
   \____/      \____/
   /    \      /    \
  /      \____/      \
  \      /    \      /
   \____/      \____/
        \      /
         \____/
    */
    private static int[] cube_to_oddq(int x, int z){
        return new int[] {x, (z + (x - (x&1)) / 2)};
    }

    public static void main(String args[]){
        System.out.println(tilesToTopEdge(-5, 1));
        System.out.println(Arrays.toString(cube_to_oddq(5,-5)));

        int x = -2;
        int y = 4;
        int z = -2;
        int dis = ((x + ((y - (y&1)) / 2) + gridSize + 1) -
                ((Math.abs(y)/2) + 1));
        System.out.println(dis);
    }
}