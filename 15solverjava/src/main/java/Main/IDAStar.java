package main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author merir
 */

public final class IDAStar {

    public static String startSearch() {
        //Käynnistetään IDA* -polunhaku, luodaan tarvittavat tietorakenteet.
        ArrayDeque<State> path = new ArrayDeque<State>();
        State startState = new State();
        boolean isSolvable = startState.stateCanBeSolved();
        System.out.println("Alkutila: ");
        startState.printState();
        if (!isSolvable) {
            return "Ei voi ratkaista";
        }
        //Lasketaan ensin alkuheuristiikka, johon verrataan tulevia etäisyyksiä.
        int threshold = startState.calculateManhattanDistance();
        System.out.println("Alkuetäisyys: " + threshold);
        path.add(startState);
        while (true) {
            //Jatketaan hakua ja thresholdin kasvattamista, kunnes maali löytyy.
            int t = search(path, 0, threshold);
            if (t < 0) {
                return ("Maali löytyi, kokonaisetäisyys on " 
                + -threshold + " siirtoa");
            } else {
                threshold = t;
            }
        }
    }

    public static int search(final ArrayDeque<State> path, 
    final int distance, final int threshold) {
        State currentState = path.peek();
        System.out.println("Ollaan tilassa: ");
        currentState.printState();
        //Nykyinen etäisyys on heuristiikan ja solmun etäisyyden
        //alkusolmusta summa.
        int currentDistance = distance 
        + currentState.calculateManhattanDistance();
        System.out.println("Tilan etäisyys: " + currentDistance);
        //Jos tila on maali niin palautetaan negatiivinen
        //luku merkitsemään, että maali on löydetty.
        if (currentState.stateIsGoal()) {
            return -currentDistance;
        }
        //Jos nykyinen etäisyys on suurempi kuin edellinen threshold,
        //päivitetään sen rajaa.
        if (currentDistance > threshold) {
            return currentDistance;
        }
        int min = Integer.MAX_VALUE;
        //Luodaan nykyisen tilan kaikki lapset (viereiset tilat)
        ArrayList<State> children = currentState.generateChildren();
        for (State child : children) {
            if (!(path.contains(child))) {
                path.add(child);
                //Rekursiivinen haku tilan lapsille.
                int t = search(path, 
                (distance + 1), threshold);
                //Jos löytyy miinusmerkkinen threshold-arvo, 
                //se tarkoittaa että maali löytynyt.
                if (t < 0) {
                    return -t;
                }
                //Päivitetään threshold-arvoa jos se on 
                //tämänhetkistä minimiä pienempi.
                if (t < min) {
                    min = t;
                } 
                path.pop();
            }
        }
        //Palautetaan pienin tällä kierroksella löydetty threshold-arvo
        return min;
    }

}
