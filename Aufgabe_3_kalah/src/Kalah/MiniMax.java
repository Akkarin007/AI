package Kalah;

import java.util.*;

public class MiniMax {


    private static int size = 0;

    public static int miniMax(KalahBoard board, int depth) {

        int v = Integer.MIN_VALUE;
        List<KalahBoard> boards = board.possibleActions();
        KalahBoard action = null;
        for (KalahBoard b : boards) {
            int temp = v;
            size++;
            if(b.isBonus()){
                v = Integer.max(v, max(b, depth - 1));
            } else {
                v = Integer.max(v, min(b, depth - 1));
            }
            if (temp != v){
                action = b;
            }
        }
        int actionIndex = boards.indexOf(action);
        System.out.println("size: " + size);
        System.out.println("action: " + actionIndex);
        size = 0;
        return actionIndex;
    }


    private static int min(KalahBoard board, int depth) {
        if (depth == 0 || board.isFinished()){
            return board.getAKalah();
        }
        int v = Integer.MAX_VALUE;
        for (KalahBoard b : board.possibleActions()) {
            size++;
            if(b.isBonus()){
                v = Integer.min(v, min(b, depth - 1));
            } else {
                v = Integer.min(v, max(b, depth - 1));
            }
        }
        return v;
    }

    private static int max(KalahBoard board, int depth) {

        if (depth == 0 || board.isFinished()){
            return board.getBKalah();
        }
        int v = Integer.MIN_VALUE;
        for (KalahBoard b : board.possibleActions()) {
            size++;
            if(b.isBonus()){
                v = Integer.max(v, max(b, depth - 1));
            } else {
                v = Integer.max(v, min(b, depth - 1));
            }
        }
        return v;
    }
}
