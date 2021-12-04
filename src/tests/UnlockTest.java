
import entities.Interactable;
import entities.Tree;
import org.junit.Test;
import interfaceadapters.commands.UnlockCommand;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class UnlockTest {

    @Test
    public void execute() {
        HashMap<String, Interactable> testArgs = new HashMap<>();
        Tree treeTest = new Tree("id");
        testArgs.put("door", treeTest);

        UnlockCommand unlockCommand = new UnlockCommand();

        assertEquals("Invalid door, please pass in a valid door.", unlockCommand.execute(testArgs));

//        String[] hints = {"hint1", "hint2"};
//        VaultDoor doorTest = new VaultDoor("door", hints, "answer!");

        // Cannot test the rest because there is a scanner
    }
}