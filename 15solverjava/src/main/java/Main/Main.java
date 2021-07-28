/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.ArrayList;

/**
 *
 * @author merir
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        State startState = new State();
        System.out.println("Start state:");
        printState(startState);
        search(startState);
 
    }
    
    public static void printState(State state) {
        int[][] board = state.getBoard();
        for (int i = 0; i < 4; i++) {
           for (int j = 0; j < 4; j++) {
              System.out.print(board[i][j] + " ");
            }
            System.out.println(); 
        }
    }
    
    public static void search(State startState) {
        PriorityQueue<State> queue = new PriorityQueue<>();
        HashSet<State> visited = new HashSet<>();
        queue.add(startState);
        while (!(queue.isEmpty())) {
            State state = queue.poll();
            if (!(visited.contains(state))) {
                visited.add(state);
                //TODO: add check for wincon
                ArrayList<State> children = state.generateChildren();
                for (State child : children) {
                    printState(child);
                }
            }
        }
    }
    
    public static boolean isSolved(State state) {
        return false;
    }
    
}
