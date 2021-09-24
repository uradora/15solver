package main;

import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 *
 * @author merir
 */

public final class IDAStar {

    public static String startSearch() {
        //Käynnistetään IDA* -polunhaku, luodaan tarvittavat tietorakenteet.
        PriorityQueue<State> path = new PriorityQueue<State>();
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
        System.out.print(path.comparator());
        while (true) {
            //Jatketaan hakua ja thresholdin kasvattamista, kunnes maali löytyy.
            threshold = search(path, startState, 0, threshold);
            if (threshold < 0) {
                return ("Maali löytyi, kokonaisetäisyys on " 
                + -threshold + " siirtoa");
            }
        }
    }

    public static int search(final PriorityQueue<State> path, 
    final State state, final int distance, final int threshold) {
        State currentState = state;
        if (path.peek() != null) {
            currentState = path.poll();
        }
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
                child.calculateManhattanDistance();
                path.add(child);
                //Rekursiivinen haku tilan lapsille.
                int newThreshold = search(path, child, 
                (distance + 1), threshold);
                //Jos löytyy miinusmerkkinen threshold-arvo, 
                //se tarkoittaa että maali löytynyt.
                if (newThreshold < 0) {
                    return -newThreshold;
                }
                //Päivitetään threshold-arvoa jos se on 
                //tämänhetkistä minimiä pienempi.
                if (newThreshold < min) {
                    min = newThreshold;
                } 
            }
        }
        //Palautetaan pienin tällä kierroksella löydetty threshold-arvo
        return min;
    }

}
