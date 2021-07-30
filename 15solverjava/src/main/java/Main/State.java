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

    private int[][] board;
    
    private int distance;
    
    public State() {
        int[][] startBoard = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
        List<int[]> boardAsList = Arrays.asList(startBoard);
        Collections.shuffle(boardAsList);
        this.board = boardAsList.toArray(startBoard);
        this.distance = 0;
    }

    public State(int[][] board, int distance) {
      this.board = board;
      this.distance = distance;
    }
    
    public int[][] getBoard() {
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
                int goal_x = (curr_value - 1) % 4;
                int goal_y = (curr_value - 1) / 4;
                manhattan += (Math.abs(goal_x - i) + Math.abs(goal_y - j));
            } 
        }
        return manhattan;
    }
    
    public ArrayList<State> generateChildren() {
        return new ArrayList<State>();
    }
    
    @Override
    public int compareTo(State otherState) {
        return this.getDistance() - otherState.getDistance();
        
    }
    
}
