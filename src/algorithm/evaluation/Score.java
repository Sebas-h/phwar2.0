package algorithm.evaluation;

import game.Move;

import java.util.ArrayList;


public class Score {

    public int score;
    public ArrayList<Move> moves;

    public Score(){
        this.moves = new ArrayList<>();
    }

    public Score (Score score){
        this.score = score.score;
        this.moves = Evaluation.getFirstPlyMoves(score.moves);
    }

}
