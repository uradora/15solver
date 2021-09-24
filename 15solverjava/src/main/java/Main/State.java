package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author merir
 */

public class State implements Comparable<State> {

    /**
     * Konstruktori luo satunnaisen aloitustilan
     */

    /**
     * Tilan pelilauta taulukkona.
     */
    private Integer[] board;
    /**
     * Tilan Manhattan-etäisyys maalista.
     */
    private int manhattanDistance;

    public State() {
        @SuppressWarnings("checkstyle:magicnumber")
        Integer[] startBoard = 
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        List<Integer> boardAsList = Arrays.asList(startBoard);
        Collections.shuffle(boardAsList);
        this.board = boardAsList.toArray(startBoard);
        this.manhattanDistance = 0;
    }

    // Konstruktori luo tilan annetulla pelilaudalla ja etäisyydellä
    public State(final Integer[] board, final int manhattanDistance) {
        this.board = board;
        this.manhattanDistance = manhattanDistance;
    }

    /**
     * @return tilan nykyinen pelilauta
     */
    public Integer[] getBoard() {
        return this.board;
    }

    /**
     * @return tilan nykyinen Manhattan-etäisyys
     */
    public int getDistance() {
        return this.manhattanDistance;
    }

    // Tämä tarkistaa, onko tila ratkaistavissa.
    // Ratkaistavan tilan vaadittu inversioiden määrä
    // riippuu siitä, millä rivillä tyhjä paikka on.
    @SuppressWarnings("checkstyle:magicnumber")
    public boolean stateCanBeSolved() {
        int inversions = 0;
        int zeroPosition = 0;

        for (int i = 0; i < 16; i++) {
            int tile = this.board[i];
            if (tile != 0) {
                for (int j = i + 1; j < 16; j++) {
                    int tileToCompare = this.board[j];
                    //Otetaan talteen nollan paikka
                    if (tileToCompare == 0) {
                        zeroPosition = j;
                    } else if (tileToCompare < tile) {
                        inversions++;
                    }
                }
            }
        }
        //Jos tyhjä on parillisella rivillä (indeksöinti 
        //alkaa nollasta) inversioita on oltava pariton määrä, 
        //ja toisin päin parittomalla rivillä.
        if (((zeroPosition / 4) % 2) == 0) {
            if (inversions % 2 == 1) {
                return true;
            } else {
                return false;
            }
        } else if (inversions % 2 == 0) {
            return true;
        }
        return false;
    }

    /**
     * @return palauttaa jokaisen pelilaudan solun Manhattan-etäisyyksien summan
     */
    @SuppressWarnings("checkstyle:magicnumber")
    public int calculateManhattanDistance() {
        int manhattan = 0;
        for (int i = 0; i < 16; i++) {
            if (this.board[i] != 0) {
                //Tämä laskee nykyisen solun arvon etäisyyden halutusta 
                //x-koordinaatista ja halutusta y-koordinaatista yhteen
                manhattan += (Math.abs((i / 4) - (this.board[i] - 1) / 4) 
                + Math.abs((i % 4) - ((this.board[i] - 1) % 4)));
            }
        }
        this.manhattanDistance = manhattan;
        return manhattan;
    }

    public Integer[] copyBoard() {
        Integer[] copy = new Integer[16];
        for (int i = 0; i < 16; i++) {
            copy[i] = this.board[i];
        }
        return copy;
    }

    public void swap(Integer[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    /**
     * @return palauttaa tilan lapsitilat (yhdellä siirrolla saavutettavat)
     */
    @SuppressWarnings("checkstyle:magicnumber")
    public ArrayList<State> generateChildren() {
        ArrayList<State> children = new ArrayList<State>();
        for (int i = 0; i < 16; i++) {
            if (this.board[i] != 0) {
                // Jos ylhäällä on tilaa, tiilen voi siirtää ylös
                if (((i / 4) != 0) && (this.board[i - 4] == 0)) {
                    Integer[] newBoardUp = this.copyBoard();
                    swap(newBoardUp, i, (i - 4));
                    State child = new State(newBoardUp, this.manhattanDistance + 1);
                    children.add(child);
                }
                // Jos vasemmalla on tilaa
                if (((i % 4) != 0) && (this.board[i - 1] == 0)) {
                    Integer[] newBoardLeft = this.copyBoard();
                    swap(newBoardLeft, i, (i - 1));
                    State child = new State(newBoardLeft, this.manhattanDistance + 1);
                    children.add(child);
                }
                // Jos oikealla on tilaa
                if (((i % 4) != 3)&& (this.board[i + 1] == 0)) {
                    Integer[] newBoardRight = this.copyBoard();
                    swap(newBoardRight, i, (i + 1));
                    State child = new State(newBoardRight, this.manhattanDistance + 1);
                    children.add(child);
                }
                // Jos alhaalla on tilaa
                if (((i / 4) != 3) && (this.board[i + 4] == 0)) {
                    Integer[] newBoardDown = this.copyBoard();
                    swap(newBoardDown, i, (i + 4));
                    State child = new State(newBoardDown, this.manhattanDistance + 1);
                    children.add(child);
                }
            }
        }
        return children;
    }

    /**
     * Tulostaa nykyisen tilan
     */
    @SuppressWarnings("checkstyle:magicnumber")
    public void printState() {
        for (int i = 0; i < 16; i++) {
            System.out.print(this.board[i]);
            if (((i + 1) % 4) == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();

    }

    /**
     * @return palauttaa tosi, jos tila on maalitila
     */
    public boolean stateIsGoal() {
        if (this.getDistance() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(State otherState) {
        return this.getDistance() - otherState.getDistance();
    }

}
