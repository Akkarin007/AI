package Kalah;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MinMax {

    public MinMax(KalahBoard board) {
        KalahBoard move = miniMax(board, 5);
        System.out.println("using move: " + move);

    }


    public KalahBoard miniMax(KalahBoard board, int depth) {

        int v = Integer.MIN_VALUE;
        List<KalahBoard> boards = board.possibleActions();
        KalahBoard action = boards.get(0);
        for (KalahBoard b : board.possibleActions()) {
            int temp = v;
            v = Integer.max(v, min(b, depth - 1));
            if (temp != v){
                action = b;
            }
        }
        return action;
    }


    public int min(KalahBoard board, int depth) {
        if (depth == 0 || board.isFinished()){
            return board.getBKalah();
        }
        int v = Integer.MAX_VALUE;
        for (KalahBoard b : board.possibleActions()) {
            v = Integer.min(v, max(b, depth - 1));
        }
        return v;
    }

    public int max(KalahBoard board, int depth) {

        if (depth == 0 || board.isFinished()){
            return board.getAKalah();
        }
        int v = Integer.MIN_VALUE;
        for (KalahBoard b : board.possibleActions()) {
            v = Integer.max(v, min(b, depth - 1));
        }
        return v;
    }

}
