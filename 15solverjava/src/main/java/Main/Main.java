/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.PriorityQueue;
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
        Path path = iterate(startState);
        if (path != null) {
            for (State state : path.getPath()) {
                printState(state);
            }
        }
    }
    
    public static Path iterate(State state) {
        PriorityQueue<State> path = new PriorityQueue<>();
        path.add(state);
        int bound = state.calculateManhattanDistance();
        while (true) {
            int distance = search(state, 0, bound, path);
            if (distance < 0) {
                return new Path(path, bound);
            }
            else if (distance == Integer.MAX_VALUE) {
                return null;
            }
            else {
                bound = distance;
            }
        }
    }
    
    public static void printState(State state) {
        Integer[][] board = state.getBoard();
        for (int i = 0; i < 4; i++) {
           for (int j = 0; j < 4; j++) {
              System.out.print(board[i][j] + " ");
            }
            System.out.println(); 
        }
    }
    
    public static int search(State state, int distance, int bound, PriorityQueue<State> path) {
        int cost = distance + state.calculateManhattanDistance();
        if (cost > bound) {
            return cost;
        }
        if (isGoal(state)) {
            return -distance;
        }
        int min = Integer.MAX_VALUE;
        ArrayList<State> children = state.generateChildren();
        for (State child : children) {
            if (!(path.contains(child))) {
                printState(child);
                path.add(child);
                int found = search(child, distance + child.calculateManhattanDistance(), bound, path);
                if (found == 0) {
                    return 0;
                } else if (found < min) {
                    min = found;
                }
                path.poll();
            }
        }
        return min;
    }
    
    public static boolean isGoal(State state) {
        if (state.calculateManhattanDistance() == 0) {
            return true;
        }
        return false;
    }
    
}
