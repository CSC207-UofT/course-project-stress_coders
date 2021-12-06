package commands;

import entities.*;
import entities.minigames.Door;
import entities.minigames.VaultDoor;
import org.junit.Test;
import interfaceadapters.commands.HintCommand;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class HintTest {
    @Test
    public void execute() {
        HashMap<String, Interactable> testArgs = new HashMap<>();
        Door testDoor = new VaultDoor("vd", new String[]{"First digit 1", "Second digit 2", "Third digit 3"}, "123");
        testArgs.put("shi", testDoor);

        HintCommand hintCommand = new HintCommand();
        assertEquals( "Not a valid door! Try again.", hintCommand.execute(testArgs));

        testArgs.put("door", testDoor);
        assertEquals("First digit 1", hintCommand.execute(testArgs));
        assertEquals("Second digit 2", hintCommand.execute(testArgs));
        assertEquals("Third digit 3", hintCommand.execute(testArgs));
        assertEquals("First digit 1", hintCommand.execute(testArgs));
    }
}
