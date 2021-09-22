package Main;
        
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.lang.Math;
import java.util.ArrayList;

/**
 *
 * @author merir
 */

public class State implements Comparable<State> {

    private Integer[] board;
    private int manhattanDistance;

    //Konstruktori luo satunnaisen aloitustilan
    public State() {
        Integer[] startBoard = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
        List<Integer> boardAsList = Arrays.asList(startBoard);
        Collections.shuffle(boardAsList);
        this.board = boardAsList.toArray(startBoard);
        this.manhattanDistance = 0;
    }

    //Konstruktori luo tilan annetulla pelilaudalla ja etäisyydellä
    public State(Integer[] board, int manhattanDistance) {
      this.board = board;
      this.manhattanDistance = manhattanDistance;
    }

    public Integer[] getBoard() {
        return this.board;
    }
    
    public int getDistance() {
        return this.manhattanDistance;
    }

    //Tämä tarkistaa, onko tila ratkaistavissa.
    //Ratkaistavan tilan vaadittu inversioiden määrä riippuu siitä, millä rivillä tyhjä paikka on.
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
        //Jos tyhjä on parillisella rivillä (indeksöinti alkaa nollasta) inversioita on oltava pariton määrä, ja toisin päin parittomalla rivillä.
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

    //Laskee jokaisen pelilaudan solun Manhattan-etäisyyksien summan
    public int calculateManhattanDistance() {
        int manhattan = 0;
        for (int i = 0; i < 16; i++) {
            if (this.board[i] != 0) {
                //Tämä laskee nykyisen solun arvon etäisyyden halutusta x-koordinaatista ja halutusta y-koordinaatista yhteen
                manhattan += (Math.abs((i / 4) - (this.board[i] - 1) / 4) + Math.abs((i % 4) - ((this.board[i] - 1) % 4)));
            }
        }
        this.manhattanDistance = manhattan;
        return manhattan;
    }

    public ArrayList<State> generateChildren() {
        //Luodaan tilan lapsitilat (yhdellä siirrolla saavutettavat)
        ArrayList<State> children = new ArrayList<State>();
        for (int i = 0; i < 16; i++) {
            if (this.board[i] == 0) {
                //Jos ylhäällä on tilaa, tiilen voi siirtää ylös
                if ((i / 4) > 0) {
                    Integer[] newBoard = this.board;
                    newBoard[i] = this.board[i-4];
                    newBoard[i-4] = this.board[i];
                    State newChild = new State(newBoard, this.getDistance());
                    children.add(newChild);
                }
                //Jos vasemmalla on tilaa
                if ((i % 4) != 0) {
                    Integer[] newBoard = this.board;
                    newBoard[i] = this.board[i-1];
                    newBoard[i-1] = this.board[i];
                    State newChild = new State(newBoard, this.getDistance());
                    children.add(newChild);
                }
                //Jos oikealla on tilaa
                if (((i + 1) % 4) != 0) {
                    Integer[] newBoard = this.board;
                    newBoard[i] = this.board[i+1];
                    newBoard[i+1] = this.board[i];
                    State newChild = new State(newBoard, this.getDistance());
                    children.add(newChild);
                }
                //Jos alhaalla on tilaa
                if ((i / 4) < 3) {
                    Integer[] newBoard = this.board;
                    newBoard[i] = this.board[i+4];
                    newBoard[i+4] = this.board[i];
                    State newChild = new State(newBoard, this.getDistance());
                    children.add(newChild);
                }
            }
        }
        return children;
    }

    public void printState() {
        for (int i = 0; i < 16; i++) {
            if (((i + 1) % 4) == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
            System.out.print(this.board[i]);
        }
    }

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
