
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;
import Main.State;

/**
 *
 * @author merir
 */

public class StateTest {

    State startState;
    State readyState;

    @Before
    public void setUp() {
        startState = new State();
        Integer[] readyBoard = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
        readyState = new State(readyBoard, 0);
    }

    @Test
    public void startStateHasMinimumValue0() {
        int min = Collections.min(Arrays.asList(startState.getBoard()));
        assertEquals(0, min);
    }
    
    @Test
    public void startStateHasMaximumValue15() {
        int max = Collections.max(Arrays.asList(startState.getBoard()));
        assertEquals(15, max);
    }
    
    @Test
    public void startStateHasManhattanDistanceOfMoreThan0() {
        assertTrue(startState.calculateManhattanDistance() != 0);
    }
    
    @Test
    public void readyBoardHasManhattanDistanceOf0() {
        System.out.println(readyState.calculateManhattanDistance());
        for (int i = 0; i < 16; i++) {
            System.out.println(readyState.getBoard()[i]);
        }
        assertEquals(readyState.calculateManhattanDistance(), 0);
    }
    
}
