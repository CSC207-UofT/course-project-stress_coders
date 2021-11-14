package tests;

import entities.Axe;
import entities.Interactable;
import entities.Player;
import entities.Tree;
import org.junit.Test;
import usecases.Chop;
import usecases.Command;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CommandTest {

    @Test
    public void execute() {
        Player testPlayer = new Player("id1");
        HashMap<String, Interactable> args = new HashMap<>();

        Command testCommand = new Chop();
        Axe testAxe = new Axe("id3");

        args.put("tool", testAxe);
        Tree testTree = new Tree("id4");
        args.put("target", testTree);
        testAxe.setHeldBy(testPlayer);

        assertEquals("Added 20 wood to name inventory", testCommand.execute(args));
    }

    @Test
    public void help() {
        Command testCommand = new Chop();
        // This will need to be changed, for now this is fine.
        assertEquals("No description", testCommand.help());
    }

    @Test
    public void setDescription() {
        Command testCommand = new Chop();
        testCommand.setDescription("here is a new description");
        assertEquals("here is a new description", testCommand.help());
    }
}