package puzzle8;

import java.util.*;

/**
 * @author Ihr Name
 */
public class A_Star {
    // cost ordnet jedem Board die Aktuellen Pfadkosten (g-Wert) zu.
    // pred ordnet jedem Board den Elternknoten zu. (siehe Skript S. 2-25).
    // In cost und pred sind genau alle Knoten der closedList und openList enthalten!
    // Nachdem der Zielknoten erreicht wurde, lässt sich aus cost und pred der Ergebnispfad ermitteln.
    private static HashMap<Board, Integer> cost = new HashMap<>();
    private static HashMap<Board, Board> pred = new HashMap<>();

    // openList als Prioritätsliste.
    // Die Prioritätswerte sind die geschätzen Kosten f = g + h (s. Skript S. 2-66)
    private static IndexMinPQ<Board, Integer> openList = new IndexMinPQ<>();

    public static Deque<Board> aStar(Board startBoard) {
        if (startBoard.isSolved())
            return new LinkedList<>();

        List<Board> closedList = new LinkedList<>();
        openList.add(startBoard, startBoard.h2());
        cost.put(startBoard, 0);

        while (!openList.isEmpty()) {
            Board parent = openList.removeMin();
            if (parent.isSolved())
                return getPred(parent);

            closedList.add(parent);

            for (Board childBoard : parent.possibleActions()) {

                Integer parentCost = cost.get(parent);

                if (openList.get(childBoard) == null && !closedList.contains(childBoard)) {
                    Integer newPathCost = parentCost + 1;
                    cost.put(childBoard, newPathCost);
                    openList.add(childBoard, newPathCost + childBoard.h2());
                    pred.put(childBoard, parent);
                } else if (openList.get(childBoard) != null) {
                    Integer currChildCost = cost.get(childBoard);
                    Integer newPathCost = parentCost + 1;
                    if (newPathCost < currChildCost) {
                        openList.change(childBoard, newPathCost + childBoard.h2());
                        cost.put(childBoard, newPathCost);
                    }
                }
            }
        }

        return null; // Keine Lösung
    }

    public static Deque<Board> getPred(Board endBoard) {
        Deque<Board> path = new LinkedList<>();
        path.add(endBoard);
        for (Board next = pred.get(endBoard); next != null; next = pred.get(next)){
            path.add(next);
        }

        return path;
    }

}
