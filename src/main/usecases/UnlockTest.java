package usecases;

import entities.*;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class UnlockTest {

    @Test
    public void execute() {
        HashMap<String, Interactable> testArgs = new HashMap<>();
        Tree treeTest = new Tree("id");
        testArgs.put("door", treeTest);

        Unlock unlockCommand = new Unlock();

        assertEquals("Invalid door, please pass in a valid door.", unlockCommand.execute(testArgs));

        String[] hints = {"hint1", "hint2"};
        VaultDoor doorTest = new VaultDoor("door", hints, "answer!");

        // Cannot test the rest because there is a scanner
    }
}