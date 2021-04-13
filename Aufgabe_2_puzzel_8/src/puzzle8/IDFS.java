package puzzle8;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Klasse IDFS für iterative deepening depth-first search
 * @author Ihr Name
 */
public class IDFS {

	private static int size = 0;

	private static Deque<Board> dfs(Board curBoard, Deque<Board> path, int limit) {
		if (curBoard.isSolved()) {
			return path; 			// Lösung
		} else if (limit == 0) {
			return null; 			// Maximal zulässige Rekursionstiefe erreicht
		} else {
			boolean cutOffOccurred = false;
			for(Board child: curBoard.possibleActions()) {
				if (is_in_deque(path, child)) {
					continue;
				}
				size++;
				path.add(child);
				Deque<Board> result = dfs(child, path, limit -1);
				if (result == null) {
					cutOffOccurred = true;
				} else if (result.getLast().isSolved()) {
					return result;
				}
				path.removeLast();
			}
			return null;
		}
	}

	public static int getSize() {
		return size;
	}

	private static boolean is_in_deque(Deque<Board> path, Board child) {
		for (Board node: path) {
			if (Arrays.equals(node.board, child.board)) {
				return true;
			}
		}
		return false;
	}
	
	private static Deque<Board> idfs(Board curBoard, Deque<Board> path) {
		for (int limit = 5; limit < Integer.MAX_VALUE; limit++) {
			Deque<Board> result = dfs(curBoard,path,limit);
			if (result != null)
				return result;
		}
		return null;
	}
	
	public static Deque<Board> idfs(Board curBoard) {
		Deque<Board> path = new LinkedList<>();
		path.addLast(curBoard);
		Deque<Board> res =  idfs(curBoard, path); 
		return res;
	}
}
