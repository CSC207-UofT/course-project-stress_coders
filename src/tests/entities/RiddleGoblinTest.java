package entities;

import entities.characters.Player;
import entities.characters.RiddleGoblin;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class RiddleGoblinTest {

    @Test
    public void setRiddleInfo() {
        RiddleGoblin riddleGoblinTest = new RiddleGoblin("riddle", new Player("j"));
        riddleGoblinTest.setRiddleInfo("Thicc as brick",
                "riddle me this, riddle me that, why is that my rear is so fat?", "Born this way");

        assertEquals("Thicc as brick", riddleGoblinTest.getMagic_message());
        assertEquals("riddle me this, riddle me that, why is that my rear is so fat?",
                riddleGoblinTest.getRiddle());
        assertEquals("Born this way", riddleGoblinTest.getAnswer());
    }

    @Test
    public void speak() {
        RiddleGoblin riddleGoblinTest = new RiddleGoblin("riddle", new Player("j"));
        // We should probably change the interface in some way.
        assertEquals("", riddleGoblinTest.speak());
    }

    @Test
    public void listenAndRespond() {
        RiddleGoblin riddleGoblinTest = new RiddleGoblin("riddle", new Player("j"));
        riddleGoblinTest.setRiddleInfo("a", "b", "c");
        List<String> hints = new ArrayList<>();
        hints.add("hint");
        riddleGoblinTest.setHints(hints);

        riddleGoblinTest.setCompleted(true);
        // Im not sure how to test the stuff with the scanner.
        assertEquals("Congrats on solving the riddle!", riddleGoblinTest.listenAndRespond());

    }

    @Test
    public void getAnswer(){
        RiddleGoblin riddleGoblinTest = new RiddleGoblin("riddle", new Player("j"));
        riddleGoblinTest.setRiddleInfo("a", "b", "c");

        assertEquals("c", riddleGoblinTest.getAnswer());
    }

    @Test
    public void getMagic_message(){
        RiddleGoblin riddleGoblinTest = new RiddleGoblin("riddle", new Player("j"));
        riddleGoblinTest.setRiddleInfo("a", "b", "c");

        assertEquals("a", riddleGoblinTest.getMagic_message());

    }

    @Test
    public void getRiddle(){
        RiddleGoblin riddleGoblinTest = new RiddleGoblin("riddle", new Player("j"));
        riddleGoblinTest.setRiddleInfo("a", "b", "c");

        assertEquals("b", riddleGoblinTest.getRiddle());
    }
}