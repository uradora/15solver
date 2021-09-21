package Main;

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.ArrayList;

/**
 *
 * @author merir
 */

public class IDAStar {

    public static String startSearch() {
        //Käynnistetään IDA* -polunhaku, luodaan tarvittavat tietorakenteet.
        HashSet<State> visited = new HashSet<State>();
        PriorityQueue<State> path = new PriorityQueue<State>();
        State startState = new State();
        boolean isSolvable = startState.stateCanBeSolved();
        if (!isSolvable) {
            return "Ei voi ratkaista";
        }
        int threshold = startState.calculateManhattanDistance();
        path.add(startState);
        System.out.print(path.comparator());
        while (!(path.isEmpty())) {
            threshold = search(path, threshold, visited, 0);
            if (threshold < 0) {
                return("Maali löytyi, kokonaisetäisyys on " + -threshold + " siirtoa");
                break;
            }
        }
    }

    public static int search(PriorityQueue<State> path, int threshold, HashSet<State>visited, int numberOfMoves) {
        State currentState = path.poll();
        visited.add(currentState);
        int currentDistance = numberOfMoves + currentState.calculateManhattanDistance();
        if (currentState.stateIsGoal()) {
            return -currentDistance;
        }
        if (currentDistance > threshold) {
            return currentDistance;
        }
        ArrayList<State> children = currentState.generateChildren();
        for (State child : children) {
            if (!(visited.contains(child))) {
                path.add(child);
                //miten tästä eteenpäin
            }
        }

    }

}
