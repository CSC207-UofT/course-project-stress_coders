
import entities.Goblin;
import entities.Player;
import entities.RiddleGoblin;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GoblinTest {

    @Test
    public void speak() {
        Goblin goblinTest = new RiddleGoblin("id", new Player("player"), 1);

        assertEquals("", goblinTest.speak());
    }

    @Test
    public void listenAndRespond() {
        Goblin goblinTest = new RiddleGoblin("id", new Player("player"), 1);
        goblinTest.listenAndRespond("test");

        assertEquals("hehe test", goblinTest.speak());
    }
}