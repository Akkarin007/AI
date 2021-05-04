package Kalah;

import java.util.LinkedList;
import java.util.List;

public class Alpha_Beta_Pruning_Heuristic {


    private static int size = 0;

    public static int miniMax(KalahBoard board, int depth) {

        int v = Integer.MIN_VALUE;
        List<KalahBoard> boards = heuristic(board, true);
        KalahBoard action = boards.get(0);
        for (KalahBoard b : boards) {
            int temp = v;
            size++;
            v = Integer.max(v, min(b, Integer.MAX_VALUE, Integer.MIN_VALUE,depth - 1));
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
        for (KalahBoard b : heuristic(board, false)) {
            size++;
            v = Integer.min(v, max(b, alpha, beta, depth - 1));
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
        for (KalahBoard b : heuristic(board, true)) {
            size++;
            v = Integer.max(v, min(b, alpha, beta, depth - 1));
            if (v >= beta) {
                return v; // Beta-Cutoff
            }
            alpha = Integer.max(alpha, v);
        }
        return v;
    }

    private static List<KalahBoard> heuristic(KalahBoard board, boolean maximizedPlayer) {
        List<KalahBoard> tmp_list = board.possibleActions();
        List<KalahBoard> sorted_list = new LinkedList<>();
        while (true) {
            if (tmp_list.size() == 0) {
                for (KalahBoard x : sorted_list) {
                    System.out.print(maximizedPlayer + " _> "+ x.getAKalah() + " \n");
                }
                return sorted_list;
            }
            KalahBoard tmp_board = tmp_list.get(0);

            for (KalahBoard x : tmp_list) {
                if(maximizedPlayer){
                    if (x.getBKalah() >= tmp_board.getBKalah()) {
                        tmp_board = x;
                    }
                } else {
                    if (x.getAKalah() <= tmp_board.getAKalah()) {
                        tmp_board = x;
                    }
                }
            }
            tmp_list.remove(tmp_board);
            sorted_list.add(tmp_board);
        }
    }
}
