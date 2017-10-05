package algorithm;

import game.Player;
import state.Colour;
import state.Particle;
import state.Position;
import state.State;

import java.util.ArrayList;

import static game.Game.gridSize;

public abstract class Algorithm extends Player {


    public Algorithm(ArrayList<Particle> particles, Colour colour) {
        super(particles, colour);
    }

    public abstract int evaluate(State s);

    public ArrayList<State> getChildren(State s) {
        ArrayList<State> children = new ArrayList<>();

        // todo: add move ordering on proponent list; that could work right?

        // todo: change proponent property to simple colour property on player and looping here only over
        // todo: player.colour == particle.colour

        int index = 0;
        for (Particle toMove : s.particles) {
            if (toMove.colour == super.colour) {
                int upY = 11;
                int downZ = 11;
                int upRightX = 11;
                int downLeftZ = 11;
                int downRightX = 11;
                int upLeftY = 11;

                for (Particle particle : s.particles) {
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

                    State newState = new State(s);
                    newState.particles.get(index).position = newPos;

                    // todo: check and apply capture moves

                    children.add(newState);
                }
                // DOWN:
                for (int y = toMove.position.y - 1, z = toMove.position.z + 1;
                     z < downZ && Math.abs(y) < 6 && Math.abs(z) < 6; z++, y--) {
                    Position newPos = new Position(toMove.position.x, y, z);

                    State newState = new State(s);
                    newState.particles.get(index).position = newPos;

                    // todo: check and apply capture moves

                    children.add(newState);
                }
                // UPRIGHT:
                for (int x = toMove.position.x + 1, z = toMove.position.z - 1;
                     x < upRightX && Math.abs(x) < 6 && Math.abs(z) < 6; z--, x++) {
                    Position newPos = new Position(x, toMove.position.y, z);

                    State newState = new State(s);
                    newState.particles.get(index).position = newPos;

                    // todo: check and apply capture moves

                    children.add(newState);
                }
                // DOWNLEFT:
                for (int x = toMove.position.x - 1, z = toMove.position.z + 1;
                     z < downLeftZ && Math.abs(x) < 6 && Math.abs(z) < 6;
                     z++, x--) {
                    Position newPos = new Position(x, toMove.position.y, z);

                    State newState = new State(s);
                    newState.particles.get(index).position = newPos;

                    // todo: check and apply capture moves

                    children.add(newState);
                }
                // DOWNRIGHT:
                for (int x = toMove.position.x + 1, y = toMove.position.y - 1;
                     x < downRightX && Math.abs(x) < 6 && Math.abs(y) < 6; y--, x++) {
                    Position newPos = new Position(x, y, toMove.position.z);

                    State newState = new State(s);
                    newState.particles.get(index).position = newPos;

                    // todo: check and apply capture moves

                    children.add(newState);
                }
                // UPLEFT:
                for (int x = toMove.position.x - 1, y = toMove.position.y + 1;
                     y < upLeftY && Math.abs(x) < 6 && Math.abs(y) < 6; y++, x--) {
                    Position newPos = new Position(x, y, toMove.position.z);

                    State newState = new State(s);
                    newState.particles.get(index).position = newPos;

                    // todo: check and apply capture moves

                    children.add(newState);
                }
            }

            // Need to make new states for each child
            // probably need to deep copy; can't use this object
            index++;
        }
        System.out.println(children.size());
//        System.out.println(children);
        return children;
    }

    private int tilesToTopEdge(int x, int z){
        return ((z + ((x - (x&1)) / 2) + gridSize + 1) -
                ((Math.abs(x)/2) + 1));
    }
}
