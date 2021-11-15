import entities.*;
import org.junit.Test;
import usecases.Hint;
import usecases.Spin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HintTest {
    @Test
    public void execute() {
        HashMap<String, Interactable> testArgs = new HashMap<>();
        Door testDoor = new VaultDoor("vd", new String[]{"First digit 1", "Second digit 2", "Third digit 3"}, "123");
        testArgs.put("shi", testDoor);

        Hint hintCommand = new Hint();
        assertEquals( "Not a valid door! Try again.", hintCommand.execute(testArgs));

        testArgs.put("door", testDoor);
        assertEquals("First digit 1", hintCommand.execute(testArgs));
        assertEquals("Second digit 2", hintCommand.execute(testArgs));
        assertEquals("Third digit 3", hintCommand.execute(testArgs));
        assertEquals("First digit 1", hintCommand.execute(testArgs));
    }
}
