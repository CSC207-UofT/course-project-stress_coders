package commands;

import entities.Interactable;
import entities.characters.Player;
import entities.food.Potion;
import entities.Tree;
import org.junit.Test;
import interfaceadapters.commands.ConsumeCommand;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ConsumeTest {

    @Test
    public void execute() {
        HashMap<String, Interactable> testArgs = new HashMap<>();
        Tree testTree = new Tree("id");

        testArgs.put("consumable", testTree);
        ConsumeCommand testConsume = new ConsumeCommand();

        assertEquals("Can't consume that", testConsume.execute(testArgs));

        Potion potionTest = new Potion("id2");
        Player testPlayer = new Player("id3");
        potionTest.setHeldBy(testPlayer);
        testPlayer.addConsumable(potionTest);
        testArgs.put("consumable", potionTest);
        assertEquals("You consumed 1 id2", testConsume.execute(testArgs));
    }
}