package Kalah;

import java.util.Arrays;

public class MinMax {

    public MinMax(KalahBoard board) {

    }

    public int min(KalahBoard board) {
        if (board.isFinished()){
            return board.getBKalah();
        }
        int v = Integer.MAX_VALUE;
        for (KalahBoard b : board.possibleActions()) {
            v = Integer.min(v, max(b));
        }
        return v;
    }

    public int max(KalahBoard board) {
        if (board.isFinished()){
            return board.getAKalah();
        }
        int v = Integer.MIN_VALUE;
        for (KalahBoard b : board.possibleActions()) {
            v = Integer.max(v, min(b));
        }
        return v;
    }

}
