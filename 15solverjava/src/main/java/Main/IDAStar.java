package Main;

//import java.util.PriorityQueue;
//import java.util.HashSet;

/**
 *
 * @author merir
 */

public class IDAStar {

    public static String startSearch() {
        //Käynnistetään IDA* -polunhaku, luodaan tarvittavat tietorakenteet.
        //HashSet<State> visited = new HashSet<State>();
        //PriorityQueue<State> path = new PriorityQueue<State>();
        State startState = new State();
        boolean isSolvable = startState.stateCanBeSolved();
        if (!isSolvable) {
            return "Ei voi ratkaista";
        }
        return "Hello";
    }

}
