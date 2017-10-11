package algorithm;

import game.Move;

import java.util.ArrayList;

public class Score {

    int score;
    ArrayList<Move> moves;

    public Score(){}

    public Score (Score score){
        this.score = score.score;
        this.moves = new ArrayList<>();
        for (Move move : score.moves) {
            moves.add(new Move(move.particle, move.destination, move.capture));
        }
    }

}
