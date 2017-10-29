package game;

import algorithm.*;
import algorithm.evaluation.Evaluation;
import algorithm.evaluation.IEvaluation;
import algorithm.evaluation.MCEvaluation;
import algorithm.evaluation.Score;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Experiment {

    public static StringBuilder experimentSb = new StringBuilder();

    public static int nodesVisited = 0;
    public static int leafNodes = 0;
    public static List<Integer> nodesVisitedEachTurn = new ArrayList<>();
    public static List<Integer> leafNodesEachTurn = new ArrayList<>();

    public static void main (String args[]) throws FileNotFoundException {
        // Experiment minimax or negamax depth time:
        //algorithmTimePerDepth();

        // nodes visited with(out) move ordering:
        //algorithmNodesVisited();

        // mc eval on start state, how big n (number of samples before converged)
        //for (int i = 0; i < 5; i++) {
        //    numOfSamplesMCEval();
        //}

        // mc eval depht one search
        depthOneSearch();
    }

    private static void depthOneSearch() throws FileNotFoundException{
        PrintWriter pw = getCSVFile();

        int numOfSamples = 100;

        experimentSb.append("sep=,\n")
                .append("one depth search with num samples =").append(numOfSamples).append('\n')
                .append("Winner,Time\n");
        pw.write(experimentSb.toString());
        experimentSb.delete(0,experimentSb.length());


        for (int i = 0; i < 3; i++) {
            Game game = new Game(
                    new DepthOneSearch(Colour.BLACK, numOfSamples),
                    new RandomPlayer(Colour.WHITE)
            );
            game.createStartState();
            long start = System.nanoTime();
            Colour winner = game.play();
            long diff = (System.nanoTime() - start) / 1_000_000;

            experimentSb.append(winner)
                    .append(',')
                    .append(diff)
                    .append('\n');

        }
        pw.write(experimentSb.toString());
        pw.close();
    }

    private static void numOfSamplesMCEval() throws FileNotFoundException {
        PrintWriter pw = getCSVFile();

        int numOfIterations = 1;

        experimentSb.append("sep=,\n")
                .append("mc eval (random moves) num of samples start state\n")
                .append("num of samples, move, avg score (iters:")
                .append(numOfIterations)
                .append("),avg time per iter(ms)\n");
        pw.write(experimentSb.toString());
        experimentSb.delete(0,experimentSb.length());

        MCEvaluation evaluation = new MCEvaluation();

        State startState = createStartState();
        ArrayList<State> childrenOfSS = AlgorithmUtil.getChildren(startState, Colour.BLACK);

        ArrayList<Integer> listOfScores = new ArrayList<>();

        int numSamples = 10_000;

        for (State child : childrenOfSS) {
            int sum = 0;
            long timeSum = 0;
            for (int i = 0; i < numOfIterations; i++) {
                long startT = System.nanoTime();
                Score score = evaluation.nEvaluate(numSamples, child, Colour.BLACK);
                timeSum += (System.nanoTime()-startT)/1_000_000;
                sum += score.score;
            }
            //System.out.println(sum);
            experimentSb.append(numSamples).append(',')
                    .append(Game.printMoves(child.priorMoves)).append(',')
                    .append(sum/numOfIterations).append(',')
                    .append(timeSum/numOfIterations).append('\n');
        }

        pw.write(experimentSb.toString());
        pw.close();
    }

    private static State createStartState(){
        ArrayList<Particle> particles = new ArrayList<>();
        particles.add(new Particle(0 , Colour.BLACK, new int[] {0,5,-5 }));
        particles.add(new Particle(-1, Colour.BLACK, new int[] {-2,5,-3}));
        particles.add(new Particle(-1, Colour.BLACK, new int[] {0,4,-4 }));
        particles.add(new Particle(-1, Colour.BLACK, new int[] {0,3,-3 }));
        particles.add(new Particle(-1, Colour.BLACK, new int[] {2,3,-5 }));
        particles.add(new Particle(1 , Colour.BLACK, new int[] {-1,5,-4}));
        particles.add(new Particle(1 , Colour.BLACK, new int[] {-1,4,-3}));
        particles.add(new Particle(1 , Colour.BLACK, new int[] {1,4,-5 }));
        particles.add(new Particle(1 , Colour.BLACK, new int[] {1,3,-4 }));
        for (int i = particles.size()-1; i > -1; i--) {
            particles.add(new Particle(particles.get(i).charge, Colour.WHITE, new int[] {
                    particles.get(i).coordinate[0] * -1, particles.get(i).coordinate[1] * -1,
                    particles.get(i).coordinate[2] * -1}));
        }
        return new State(particles, false);
    }

    private static void algorithmNodesVisited() throws FileNotFoundException{
        PrintWriter pw = getCSVFile();

        experimentSb.append("sep=,\n")
                .append("negamax WITH move ordering (based on num of captures, sorted), nodes visited\n")
                .append("Winner,Time,Depth,AvgNodesVisited,AvgLeafNodes\n");
        pw.write(experimentSb.toString());
        experimentSb.delete(0,experimentSb.length());

        int depth = 3;

        for (int i = 0; i < 50; i++) {
            Game game = new Game(
                    new NegaMax(Colour.BLACK, depth),
                    new RandomPlayer(Colour.WHITE)
            );
            game.createStartState();
            long start = System.nanoTime();
            experimentSb.append(game.play().toString()).append(',')
                    .append((System.nanoTime() - start) / 1_000_000).append(',')
                    .append(depth).append(',')
                    .append(calculateAverage(nodesVisitedEachTurn)).append(',')
                    .append(calculateAverage(leafNodesEachTurn)).append('\n');

            nodesVisitedEachTurn = new ArrayList<>();
            leafNodesEachTurn = new ArrayList<>();
        }
        pw.write(experimentSb.toString());
        pw.close();
    }

    private static int calculateAverage(List <Integer> marks) {
        Integer sum = 0;
        if(!marks.isEmpty()) {
            for (Integer mark : marks) {
                sum += mark;
            }
            return (int) Math.round(sum.doubleValue() / marks.size());
        }
        return Math.round(sum);
    }

    private static void algorithmTimePerDepth() throws FileNotFoundException{
        PrintWriter pw = getCSVFile();

        experimentSb.append("sep=,\n")
                    .append("minimax avg time in ms for game per depth\n")
                    .append("Winner,Time,Depth\n");
        pw.write(experimentSb.toString());
        experimentSb.delete(0,experimentSb.length());

        for (int i = 3; i < 4; i++) {
            for (int j = 0; j < 10; j++) {

                Game game = new Game(
                        new MiniMax(Colour.BLACK, i),
                        new RandomPlayer(Colour.WHITE)
                );
                game.createStartState();
                long start = System.nanoTime();
                experimentSb.append(game.play().toString())
                        .append(',')
                        .append((System.nanoTime() - start) / 1_000_000)
                        .append(',')
                        .append(Integer.toString(i))
                        .append('\n');
            }
            pw.write(experimentSb.toString());
            experimentSb.delete(0,experimentSb.length());
        }

        pw.write(experimentSb.toString());
        pw.close();

    }

    private static PrintWriter getCSVFile() throws FileNotFoundException{
        String fileName = "test.csv";
        for (int i = 1; new File(fileName).exists(); i++) {
            fileName = "test" + i + ".csv";
        }
        return new PrintWriter(new File(fileName));
    }
}
