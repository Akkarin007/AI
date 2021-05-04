package puzzle8;

import java.util.Deque;

/**
 * Hauptprogramm für 8-Puzzle-Problem.
 *
 * @author Ihr Name
 */
public class Puzzle8 {

    public static void main(String[] args) {
        Board b = new Board(); // Loesbares Puzzle b zufällig genrieren.
        //Board b = new Board(new int[]{7, 2, 4, 5, 0, 6, 8, 3, 1});

        while (!b.parity()) b = new Board();
        System.out.println(b);
        if (b.parity()) {
            Deque<Board> res = A_Star.aStar(b);
            int n = res == null ? -1 : res.size();
            System.out.println("Astar:");
            System.out.println("Anz.Zuege: " + n + ": " + res);
            System.out.println("Anz.Zustände: " + A_Star.getSize());

            res = IDFS.idfs(b);
            n = res == null ? -1 : res.size();
            System.out.println("IDFS:");
            System.out.println("Anz.Zuege: " + n + ": " + res);
            System.out.println("Anz.Zustände: " + IDFS.getSize());
        }
    }
}
