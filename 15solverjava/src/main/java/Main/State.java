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

    //Konstruktori luo tilan annetulla pelilaudalla ja etäisyydellä
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

    //Tämä tarkistaa, onko tila ratkaistavissa.
    //Ratkaistavan tilan vaadittu inversioiden määrä 
    //riippuu siitä, millä rivillä tyhjä paikka on.
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

    /**
     * @return palauttaa tilan lapsitilat (yhdellä siirrolla saavutettavat)
     */
    @SuppressWarnings("checkstyle:magicnumber")
    public ArrayList<State> generateChildren() {
        ArrayList<State> children = new ArrayList<State>();
        for (int i = 0; i < 16; i++) {
            if (this.board[i] == 0) {
                //Jos ylhäällä on tilaa, tiilen voi siirtää ylös
                if ((i / 4) > 0) {
                    Integer[] newBoardUp = new Integer[16];
                    for (int j = 0; j < 16; j++) {
                        newBoardUp[j] = this.board[j];
                    } 
                    newBoardUp[i] = this.board[i - 4];
                    newBoardUp[i - 4] = 0;
                    State newChildUp = new State(
                    newBoardUp, this.getDistance());
                    children.add(newChildUp);
                }
                //Jos vasemmalla on tilaa
                if ((i % 4) != 0) {
                    Integer[] newBoardLeft = new Integer[16];
                    for (int j = 0; j < 16; j++) {
                        newBoardLeft[j] = this.board[j];
                    } 
                    newBoardLeft[i] = this.board[i - 1];
                    newBoardLeft[i - 1] = 0;
                    State newChildLeft = new State(
                    newBoardLeft, this.getDistance());
                    children.add(newChildLeft);
                }
                //Jos oikealla on tilaa
                if (((i + 1) % 4) != 0) {
                    Integer[] newBoardRight = new Integer[16];
                    for (int j = 0; j < 16; j++) {
                        newBoardRight[j] = this.board[j];
                    } 
                    newBoardRight[i] = this.board[i + 1];
                    newBoardRight[i + 1] = 0;
                    State newChildRight = new State(
                    newBoardRight, this.getDistance());
                    children.add(newChildRight);
                }
                //Jos alhaalla on tilaa
                if ((i / 4) < 3) {
                    Integer[] newBoardDown = new Integer[16];
                    for (int j = 0; j < 16; j++) {
                        newBoardDown[j] = this.board[j];
                    } 
                    newBoardDown[i] = this.board[i + 4];
                    newBoardDown[i + 4] = 0;
                    State newChildDown = new State(
                    newBoardDown, this.getDistance());
                    children.add(newChildDown);
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

}
