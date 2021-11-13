package usecases;

import entities.Interactable;
import entities.Player;
import entities.Potion;
import entities.Tree;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ConsumeTest {

    @Test
    public void execute() {
        HashMap<String, Interactable> testArgs = new HashMap<>();
        Tree testTree = new Tree("id");

        testArgs.put("consumable", testTree);
        Consume testConsume = new Consume();

        assertEquals("Can't consume that", testConsume.execute(testArgs));

        Potion potionTest = new Potion("id2");
        Player testPlayer = new Player("id3");
        potionTest.setHeldBy(testPlayer);
        testArgs.put("consumable", potionTest);
        assertEquals("You consumed 1 id2", testConsume.execute(testArgs));
    }
}