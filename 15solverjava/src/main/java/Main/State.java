/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
        
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.lang.Math;

/**
 *
 * @author merir
 */
public class State implements Comparable<State> {
  
    private ArrayList<State> children;

    private Integer[][] board;
    
    private int distance;
    
    public State() {
        Integer[][] startBoard = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
        List<Integer[]> boardAsList = Arrays.asList(startBoard);
        for (int i = 0; i < 4; i++) {
            List<Integer> row = Arrays.asList(boardAsList.get(i));
            Collections.shuffle(row);
            boardAsList.set(i, row.toArray(boardAsList.get(i)));
        }
        Collections.shuffle(boardAsList);
        this.board = boardAsList.toArray(startBoard);
        this.distance = 0;
    }

    public State(Integer[][] board, int distance) {
      this.board = board;
      this.distance = distance;
    }
    
    public Integer[][] getBoard() {
        return this.board;
    }
    
    public int getDistance() {
        return this.distance;
    }

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
                manhattan += (Math.abs(goal_x - j) + Math.abs(goal_y - i));
            } 
        }
        this.distance = this.distance + manhattan;
        return manhattan;
    }
    
    public ArrayList<State> generateChildren() {
        ArrayList<State> children = new ArrayList<State>();
        Integer[] flatBoard = new Integer[16];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                flatBoard[index++] = this.board[i][j];
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.board[i][j] == 0) {
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
                        State child = new State(newBoard, this.distance + 1);
                        children.add(child);
                    }
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
                        State child = new State(newBoard, this.distance + 1);
                        children.add(child);
                    }
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
                        State child = new State(newBoard, this.distance + 1);
                        children.add(child);
                    }
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
                        State child = new State(newBoard, this.distance + 1);
                        children.add(child);
                    }
                }
            }
        }
        return children;
    }
    
    @Override
    public int compareTo(State otherState) {
        return this.getDistance() - otherState.getDistance();
        
    }
    
    
}
