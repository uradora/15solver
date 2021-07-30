/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author merir
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import Main.Main;
import Main.State;

public class MainTest {
    
    State state;
    
    @Before
    public void setUp() {
        Integer[][] readyBoard = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
        state = new State(readyBoard, 0);
    }
    
    @Test
    public void readyStateIsAGoalState() {
        assertTrue(Main.isGoal(state));
    }
    
}
