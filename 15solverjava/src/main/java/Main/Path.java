/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.PriorityQueue;

/**
 *
 * @author merir
 */
public class Path {
    
    private PriorityQueue<State> path;
    
    private int bound;
    
    public Path(PriorityQueue<State> path, int bound) {
        this.path = path;
        this.bound = bound;
    }
    
    public PriorityQueue<State> getPath() {
        return this.path;
    }
    
}
