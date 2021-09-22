package Main;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 *
 * @author merir
 */

public class IDAStar {

    public static String startSearch() {
        //Käynnistetään IDA* -polunhaku, luodaan tarvittavat tietorakenteet.
        ArrayDeque<State> path = new ArrayDeque<State>();
        State startState = new State();
        boolean isSolvable = startState.stateCanBeSolved();
        if (!isSolvable) {
            return "Ei voi ratkaista";
        }
        //Lasketaan ensin alkuheuristiikka, johon verrataan tulevia etäisyyksiä.
        int threshold = startState.calculateManhattanDistance();
        path.add(startState);
        while (true) {
            //Jatkataan hakua ja heuristiikan kasvattamista, kunnes maali löytyy.
            threshold = search(startState, 0, threshold);
            if (threshold < 0) {
                return("Maali löytyi, kokonaisetäisyys on " + -threshold + " siirtoa");
            }
        }
    }

    public static int search(State state, int distance, int threshold) {
        //Nykyinen etäisyys on heuristiikan ja solmun etäisyyden alkusolmusta summa.
        int currentDistance = distance + state.calculateManhattanDistance();
        //Jos tila on maali niin palautetaan negatiivinen luku merkitsemään, että maali on löydetty.
        if (state.stateIsGoal()) {
            return -currentDistance;
        }
        //Jos nykyinen etäisyys on suurempi kuin edellinen threshold, päivitetään sen rajaa.
        if (currentDistance > threshold) {
            return currentDistance;
        }
        int min = Integer.MAX_VALUE;
        //Luodaan nykyisen tilan kaikki lapset (viereiset tilat)
        ArrayList<State> children = state.generateChildren();
        for (State child : children) {
            //Rekursiivinen haku tilan lapsille.
            threshold = search(child, (distance + 1), threshold);
            //Jos löytyy miinusmerkkinen threshold-arvo, se tarkoittaa että maali löytynyt.
            if (threshold < 0) {
                return threshold;
            }
            //Päivitetään threshold-arvoa jos se on tämänhetkistä minimiä pienempi.
            if (threshold < min) {
                min = threshold;
            } 
        }
        //Palautetaan pienin tällä kierroksella löydetty threshold-arvo
        return min;
    }

}
