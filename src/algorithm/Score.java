package algorithm;

import game.Move;

import java.util.ArrayList;


public class Score {

    int score;
    ArrayList<Move> moves;

    public Score(){
        this.moves = new ArrayList<>();
    }

    public Score (Score score){
        this.score = score.score;
        this.moves = Evaluation.getFirstPlyMoves(score.moves);
    }

}
