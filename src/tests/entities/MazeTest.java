package entities;

import org.junit.Test;
import static org.junit.Assert.*;
import entities.characters.*;

public class MazeTest {

    @Test
    public void hasPathFailedFalse(){
        Player p = new Player();
        Maze m = new Maze("Test Maze", p);
        boolean actual = m.hasPathFailed("rrllrrl", "rrll", 4);
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void hasPathFailedTrue(){
        Player p = new Player();
        Maze m = new Maze("Test Maze", p);
        boolean actual = m.hasPathFailed("rrllrrl", "lldd", 4);
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void hasTravelledDistanceFalse(){
        Player p = new Player();
        Maze m = new Maze("Test Maze", p);
        boolean actual = m.hasTravelledDistance(5, 10);
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void hasTravelledDistanceTrue(){
        Player p = new Player();
        Maze m = new Maze("Test Maze", p);
        boolean actual = m.hasTravelledDistance(10, 10);
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void moveComplete(){
        Player p = new Player();
        Maze m = new Maze("Test Maze", p, "lrud", 100000000);
        String moveFail = m.move('r');
        assertEquals("path", moveFail);
        String move1 = m.move('l');
        assertEquals("false", move1);
        String move2 = m.move('r');
        assertEquals("false", move2);
        String move3 = m.move('u');
        assertEquals("false", move3);
        String move4 = m.move('d');
        assertEquals("true", move4);
    }

    @Test
    public void moveTime(){
        Player p = new Player();
        Maze m = new Maze("Test Maze", p, "l", 30);
        double time = System.currentTimeMillis();
        while (time >= System.currentTimeMillis() - 40);
        String move1 = m.move('d');
        assertEquals(move1, "time");
    }
}
