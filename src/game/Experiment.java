package game;

import algorithm.MiniMax;
import algorithm.RandomPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Experiment {

    public static void main (String args[]) throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(new File("test.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("sep=,\n");
        sb.append("Winner,Time,Depth\n");

        pw.write(sb.toString());
        for (int i = 3; i < 4; i++) {
            StringBuilder sb2 = new StringBuilder();
            for (int j = 0; j < 10; j++) {

                Game game = new Game(
                        new MiniMax(Colour.BLACK, i),
                        new RandomPlayer(Colour.WHITE)
                );
                game.createStartState();
                long start = System.nanoTime();
                sb2.append(game.play().toString()).append(',');
                //System.out.println("game time (in ms) = " + (System.nanoTime() - start) / 1_000_000);
                sb2.append((System.nanoTime() - start) / 1_000_000).append(',').append(Integer.toString(i)).append('\n');
            }
            pw.write(sb2.toString());
        }

        //pw.write(sb.toString());
        pw.close();
    }
}
