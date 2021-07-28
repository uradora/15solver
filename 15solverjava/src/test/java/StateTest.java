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
import java.util.Arrays;
import java.util.Collections;
import Main.State;

public class StateTest {
    
    State startState;
    Integer[] flatBoard;
    
    @Before
    public void setUp() {
        startState = new State();
        int[][] board = startState.getBoard();
        flatBoard = new Integer[16];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                flatBoard[index++] = board[i][j];
            }
        }
    }
    
    @Test
    public void startStateHasMinimumValue0() {
        int min = Collections.min(Arrays.asList(flatBoard));
        assertEquals(0, min);
    }
    
    @Test
    public void startStateHasMaximumValue15() {
        int max = Collections.max(Arrays.asList(flatBoard));
        assertEquals(15, max);
    }
    
    @Test
    public void startStateHasDistanceOf0() {
        assertEquals(startState.getDistance(), 0);
    }

}
