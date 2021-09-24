package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author merir
 */

public class State {

    /**
     * Konstruktori luo satunnaisen aloitustilan
     */

    /**
     * Tilan pelilauta taulukkona.
     */
    private Integer[] board;
    /**
     * Tilan Manhattan-etÃ¤isyys maalista.
     */

    public State() {
        @SuppressWarnings("checkstyle:magicnumber")
        Integer[] startBoard = 
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        List<Integer> boardAsList = Arrays.asList(startBoard);
        Collections.shuffle(boardAsList);
        this.board = boardAsList.toArray(startBoard);
    }

    // Konstruktori luo tilan annetulla pelilaudalla ja etÃ¤isyydellÃ¤
    public State(final Integer[] board) {
        this.board = board;
    }

    /**
     * @return tilan nykyinen pelilauta
     */
    public Integer[] getBoard() {
        return this.board;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (!Arrays.deepEquals(this.board, other.board)) {
            return false;
        }
        return true;
    }

    // TÃ¤mÃ¤ tarkistaa, onko tila ratkaistavissa.
    // Ratkaistavan tilan vaadittu inversioiden mÃ¤Ã¤rÃ¤
    // riippuu siitÃ¤, millÃ¤ rivillÃ¤ tyhjÃ¤ paikka on.
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
        //Jos tyhjÃ¤ on parillisella rivillÃ¤ (indeksÃ¶inti 
        //alkaa nollasta) inversioita on oltava pariton mÃ¤Ã¤rÃ¤, 
        //ja toisin pÃ¤in parittomalla rivillÃ¤.
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
     * @return palauttaa jokaisen pelilaudan solun Manhattan-etÃ¤isyyksien summan
     */
    @SuppressWarnings("checkstyle:magicnumber")
    public int calculateManhattanDistance() {
        int manhattan = 0;
        for (int i = 0; i < 16; i++) {
            if (this.board[i] != 0) {
                //TÃ¤mÃ¤ laskee nykyisen solun arvon etÃ¤isyyden halutusta 
                //x-koordinaatista ja halutusta y-koordinaatista yhteen
                manhattan += (Math.abs((i / 4) - (this.board[i] - 1) / 4) 
                + Math.abs((i % 4) - ((this.board[i] - 1) % 4)));
            }
        }
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
     * @return palauttaa tilan lapsitilat (yhdellÃ¤ siirrolla saavutettavat)
     */
    @SuppressWarnings("checkstyle:magicnumber")
    public ArrayList<State> generateChildren() {
        ArrayList<State> children = new ArrayList<State>();
        for (int i = 0; i < 16; i++) {
            if (this.board[i] != 0) {
                // Jos ylhÃ¤Ã¤llÃ¤ on tilaa, tiilen voi siirtÃ¤Ã¤ ylÃ¶s
                if (((i / 4) != 0) && (this.board[i - 4] == 0)) {
                    Integer[] newBoardUp = this.copyBoard();
                    swap(newBoardUp, i, (i - 4));
                    State child = new State(newBoardUp);
                    children.add(child);
                }
                // Jos vasemmalla on tilaa
                if (((i % 4) != 0) && (this.board[i - 1] == 0)) {
                    Integer[] newBoardLeft = this.copyBoard();
                    swap(newBoardLeft, i, (i - 1));
                    State child = new State(newBoardLeft);
                    children.add(child);
                }
                // Jos oikealla on tilaa
                if (((i % 4) != 3)&& (this.board[i + 1] == 0)) {
                    Integer[] newBoardRight = this.copyBoard();
                    swap(newBoardRight, i, (i + 1));
                    State child = new State(newBoardRight);
                    children.add(child);
                }
                // Jos alhaalla on tilaa
                if (((i / 4) != 3) && (this.board[i + 4] == 0)) {
                    Integer[] newBoardDown = this.copyBoard();
                    swap(newBoardDown, i, (i + 4));
                    State child = new State(newBoardDown);
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
        if (this.calculateManhattanDistance() == 0) {
            return true;
        }
        return false;
    }

}
