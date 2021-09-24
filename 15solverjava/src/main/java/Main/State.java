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
    private Integer[][] board;
    /**
     * Tilan Manhattan-etäisyys maalista.
     */
    private int manhattanDistance;

    public State() {
        @SuppressWarnings("checkstyle:magicnumber")
        Integer[][] startBoard = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 0 } };
        List<Integer[]> boardAsList = Arrays.asList(startBoard);
        for (int i = 0; i < 4; i++) {
            List<Integer> row = Arrays.asList(boardAsList.get(i));
            Collections.shuffle(row);
            boardAsList.set(i, row.toArray(boardAsList.get(i)));
        }
        Collections.shuffle(boardAsList);
        this.board = boardAsList.toArray(startBoard);
        this.manhattanDistance = 0;
    }

    // Konstruktori luo tilan annetulla pelilaudalla ja etäisyydellä
    public State(final Integer[][] board, final int manhattanDistance) {
        this.board = board;
        this.manhattanDistance = manhattanDistance;
    }

    /**
     * @return tilan nykyinen pelilauta
     */
    public Integer[][] getBoard() {
        return this.board;
    }

    /**
     * @return tilan nykyinen Manhattan-etäisyys
     */
    public int getDistance() {
        return this.manhattanDistance;
    }

    public Integer[] flattenBoard (Integer[][] board) {
        Integer[] flatBoard = new Integer[16];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                flatBoard[index++] = board[i][j];
            }
        }
        return flatBoard;
    }

    // Tämä tarkistaa, onko tila ratkaistavissa.
    // Ratkaistavan tilan vaadittu inversioiden määrä
    // riippuu siitä, millä rivillä tyhjä paikka on.
    @SuppressWarnings("checkstyle:magicnumber")
    public boolean stateCanBeSolved() {
        int inversions = 0;
        int zeroPosition = 0;

        Integer[] flatBoard = flattenBoard(this.board);
        for (int i = 0; i < 16; i++) {
            int tile = flatBoard[i];
            if (tile != 0) {
                for (int j = i + 1; j < 16; j++) {
                    int tileToCompare = flatBoard[j];
                    // Otetaan talteen nollan paikka
                    if (tileToCompare == 0) {
                        zeroPosition = j;
                    } else if (tileToCompare < tile) {
                        inversions++;
                    }
                }
            }
        }
        // Jos tyhjä on parillisella rivillä (indeksöinti
        // alkaa nollasta) inversioita on oltava pariton määrä,
        // ja toisin päin parittomalla rivillä.
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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int curr_value = this.board[i][j];
                int goal_x;
                int goal_y;
                if (curr_value == 0) {
                    goal_x = 3;
                    goal_y = 3;
                } else {
                    goal_x = (curr_value - 1) % 4;
                    goal_y = (curr_value - 1) / 4;
                }
                // Tämä laskee nykyisen solun arvon etäisyyden halutusta
                // x-koordinaatista ja halutusta y-koordinaatista yhteen
                manhattan += (Math.abs(goal_x - j) + Math.abs(goal_y - i)); 
            }
        }
        this.manhattanDistance = this.manhattanDistance + manhattan;
        return manhattan;
    }

    public Integer[][] copyBoard() {
        Integer[][] copy = new Integer[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copy[i][j] = this.board[i][j];
            }
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
        Integer[] flatBoard = flattenBoard(this.board);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.board[i][j] == 0) {
                    // Jos ylhäällä on tilaa, tiilen voi siirtää ylös
                    if (i > 0) {
                        List<Integer> boardAsList = Arrays.asList(flatBoard);
                        Collections.swap(boardAsList, i, (i-1));
                        Integer[][] newBoard = new Integer[4][4];
                        int counter = 0;
                        for (int x = 0; x < 4; x++) {
                            for (int y = 0; y < 4; y++) {
                                newBoard[x][y] = boardAsList.toArray(flatBoard)[y+counter];
                            }
                            counter += 4;
                        }
                        State child = new State(newBoard, this.manhattanDistance + 1);
                        children.add(child);
                    }
                    // Jos vasemmalla on tilaa
                    if (i < 3) {
                        List<Integer> boardAsList = Arrays.asList(flatBoard);
                        Collections.swap(boardAsList, i, (i+1));
                        Integer[][] newBoard = new Integer[4][4];
                        int counter = 0;
                        for (int x = 0; x < 4; x++) {
                            for (int y = 0; y < 4; y++) {
                                newBoard[x][y] = boardAsList.toArray(flatBoard)[y+counter];
                            }
                            counter += 4;
                        }
                        State child = new State(newBoard, this.manhattanDistance + 1);
                        children.add(child);
                    }
                    // Jos oikealla on tilaa
                    if (j > 0) {
                        List<Integer> boardAsList = Arrays.asList(flatBoard);
                        Collections.swap(boardAsList, j, (j-1));
                        Integer[][] newBoard = new Integer[4][4];
                        int counter = 0;
                        for (int x = 0; x < 4; x++) {
                            for (int y = 0; y < 4; y++) {
                                newBoard[x][y] = boardAsList.toArray(flatBoard)[y+counter];
                            }
                            counter += 4;
                        }
                        State child = new State(newBoard, this.manhattanDistance + 1);
                        children.add(child);
                    }
                    // Jos alhaalla on tilaa
                    if (j < 3) {
                        List<Integer> boardAsList = Arrays.asList(flatBoard);
                        Collections.swap(boardAsList, j, (i+1));
                        Integer[][] newBoard = new Integer[4][4];
                        int counter = 0;
                        for (int x = 0; x < 4; x++) {
                            for (int y = 0; y < 4; y++) {
                                newBoard[x][y] = boardAsList.toArray(flatBoard)[y+counter];
                            }
                            counter += 4;
                        }
                        State child = new State(newBoard, this.manhattanDistance + 1);
                        children.add(child);
                    }
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
        for (int i = 0; i < 4; i++) {
           for (int j = 0; j < 4; j++) {
              System.out.print(this.board[i][j] + " ");
            }
            System.out.println(); 
        }
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
