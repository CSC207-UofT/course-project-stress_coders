package entities;

import entities.minigames.Maze;
import interfaceadapters.SystemTimeable;
import interfaceadapters.TimeSystem;
import org.junit.Test;
import static org.junit.Assert.*;
import entities.characters.*;
import usecases.*;

public class MazeTest {

    @Test
    public void hasPathFailedFalse(){
        Player p = new Player();
        SystemTimeable st = new TimeSystem();
        Timing time = new Time(st);
        Maze m = new Maze("Test Maze", p, time);
        boolean actual = m.hasPathFailed("rrllrrl", "rrll", 4);
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void hasPathFailedTrue() {
        Player p = new Player();
        SystemTimeable st = new TimeSystem();
        Timing time = new Time(st);
        Maze m = new Maze("Test Maze", p, time);
        boolean actual = m.hasPathFailed("rrllrrl", "lldd", 4);
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void hasTravelledDistanceFalse(){
        Player p = new Player();
        SystemTimeable st = new TimeSystem();
        Timing time = new Time(st);
        Maze m = new Maze("Test Maze", p, time);
        boolean actual = m.hasTravelledDistance(5, 10);
        boolean expected = false;

        assertEquals(expected, actual);
    }

    @Test
    public void hasTravelledDistanceTrue(){
        Player p = new Player();
        SystemTimeable st = new TimeSystem();
        Timing time = new Time(st);
        Maze m = new Maze("Test Maze", p, time);
        boolean actual = m.hasTravelledDistance(10, 10);
        boolean expected = true;

        assertEquals(expected, actual);
    }

    @Test
    public void moveFail(){
        Player p = new Player();
        SystemTimeable st = new TimeSystem();
        Timing time = new Time(st);
        Maze m = new Maze("Test Maze", p, "lrud", 100000000, time);
        String moveFail = m.move('r');
        assertEquals("path", moveFail);
    }

    @Test
    public void movePassFalse(){
        Player p = new Player();
        SystemTimeable st = new TimeSystem();
        Timing time = new Time(st);
        Maze m = new Maze("Test Maze", p, "lrud", 100000000, time);
        String moveFail = m.move('l');
        assertEquals("false", moveFail);
    }

    @Test
    public void movePassTrue(){
        Player p = new Player();
        SystemTimeable st = new TimeSystem();
        Timing time = new Time(st);
        Maze m = new Maze("Test Maze", p, "l", 100000000, time);
        String moveFail = m.move('l');
        assertEquals("true", moveFail);
    }

    @Test
    public void moveTime(){
        Player p = new Player();
        SystemTimeable st = new TimeSystem();
        Timing time = new Time(st);
        Maze m = new Maze("Test Maze", p, "l", 30, time);
        double currTime = System.currentTimeMillis();
        while (currTime >= System.currentTimeMillis() - 40);
        String move1 = m.move('d');
        assertEquals(move1, "time");
    }
}
