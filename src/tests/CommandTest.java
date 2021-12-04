import entities.weapons.Axe;
import entities.Interactable;
import entities.characters.Player;
import entities.Tree;
import interfaceadapters.commands.ChopCommand;
import org.junit.Test;
import usecases.Command;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CommandTest {

    @Test
    public void execute() {
        Player testPlayer = new Player("id1");
        HashMap<String, Interactable> args = new HashMap<>();

        Command testCommand = new ChopCommand();
        Axe testAxe = new Axe("id3");

        args.put("tool", testAxe);
        Tree testTree = new Tree("id4");
        args.put("target", testTree);
        testAxe.setHeldBy(testPlayer);

        assertEquals("Added 20 wood to id1 inventory", testCommand.execute(args));
    }
}