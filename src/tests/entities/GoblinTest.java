package entities;

import entities.characters.Goblin;
import entities.characters.Player;
import entities.characters.RiddleGoblin;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class GoblinTest {

    @Test
    public void speak() {
        Goblin goblinTest = new RiddleGoblin("id", new Player("j"));

        assertEquals("", goblinTest.speak());
    }

    @Test
    public void listenAndRespond() {
        RiddleGoblin goblinTest = new RiddleGoblin("id", new Player("j"));
        List<String> hints = new ArrayList<>();
        hints.add("hint");
        goblinTest.setHints(hints);
        goblinTest.setCompleted(true);
        assertEquals("Congrats on solving the riddle!", goblinTest.listenAndRespond());
    }
}