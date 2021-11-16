package entities;

import entities.Goblin;
import entities.Player;
import entities.RiddleGoblin;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GoblinTest {

    @Test
    public void speak() {
        Goblin goblinTest = new RiddleGoblin("id", new Player("j"));

        assertEquals("", goblinTest.speak());
    }

    @Test
    public void listenAndRespond() {
        Goblin goblinTest = new RiddleGoblin("id", new Player("j"));
        goblinTest.listenAndRespond();

        assertEquals("hehe test", goblinTest.speak());
    }
}