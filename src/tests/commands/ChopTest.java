package commands;

import entities.*;
import entities.characters.Enemy;
import entities.characters.Player;
import entities.weapons.Axe;
import interfaceadapters.commands.ChopCommand;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ChopTest {

    @Test
    public void execute() {
        Player testPlayer = new Player("id1");
        Enemy enemyTest = new Enemy("id", testPlayer, 1);
        HashMap<String, Interactable> args = new HashMap<>();

        args.put("tool", enemyTest);
        args.put("target", enemyTest);

        ChopCommand chopCommand = new ChopCommand();
        Axe testAxe = new Axe("id3");

        assertEquals("You cannot harvest that!", chopCommand.execute(args));

        args.put("tool", testAxe);
        Tree testTree = new Tree("id4");
        args.put("target", testTree);
        assertEquals("You don't hold that", chopCommand.execute(args));

        testAxe.setHeldBy(testPlayer);

        assertEquals("Added 20 wood to id1 inventory", chopCommand.execute(args));
    }
}