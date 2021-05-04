package Kalah;

import java.util.List;

public class Alpha_Beta_Pruning {


    private static int size = 0;

    public static int miniMax(KalahBoard board, int depth) {

        int v = Integer.MIN_VALUE;
        List<KalahBoard> boards = board.possibleActions();
        KalahBoard action = boards.get(0);
        for (KalahBoard b : boards) {
            int temp = v;
            size++;
            if(b.isBonus()){
                v = Integer.max(v, max(b, Integer.MAX_VALUE, Integer.MIN_VALUE,depth - 1));
            } else {
                v = Integer.max(v, min(b, Integer.MAX_VALUE, Integer.MIN_VALUE,depth - 1));
            }
            if (temp != v){
                action = b;
            }
        }
        int actionIndex = boards.indexOf(action);
        System.out.println("size: " + size);
        System.out.println("action: " + actionIndex);
        return actionIndex;
    }


    private static int min(KalahBoard board, int alpha, int beta, int depth) {
        if (depth == 0 || board.isFinished()){
            return board.getAKalah();
        }
        int v = Integer.MAX_VALUE;
        for (KalahBoard b : board.possibleActions()) {
            size++;
            if(b.isBonus()){
                v = Integer.min(v, min(b, alpha, beta, depth - 1));
            } else {
                v = Integer.min(v, max(b, alpha, beta, depth - 1));
            }
            if (v <= alpha) {
                return v; // Alpha-Cutoff
            }
            beta = Integer.min(beta, v);
        }
        return v;
    }

    private static int max(KalahBoard board, int alpha, int beta, int depth) {

        if (depth == 0 || board.isFinished()){
            return board.getBKalah();
        }
        int v = Integer.MIN_VALUE;
        for (KalahBoard b : board.possibleActions()) {
            size++;
            if(b.isBonus()){
                v = Integer.max(v, max(b, alpha, beta,depth - 1));
            } else {
                v = Integer.max(v, min(b, alpha, beta,depth - 1));
            }
            if (v >= beta) {
                return v; // Beta-Cutoff
            }
            alpha = Integer.max(alpha, v);
        }
        return v;
    }

}
