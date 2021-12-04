
import entities.Interactable;
import entities.Tree;
import org.junit.Test;
import interfaceadapters.TalkToCommand;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TalkToTest {

    @Test
    public void execute() {
        HashMap<String, Interactable> testArgs = new HashMap<>();
        Tree treeTest = new Tree("id");
        testArgs.put("receiver", treeTest);

        TalkToCommand talkToCommand = new TalkToCommand();

        assertEquals("They cannot talk!", talkToCommand.execute(testArgs));

//        RiddleGoblin riddleGoblin = new RiddleGoblin("id2", new Player("j"));
        // Cannot test the rest because there is a scanner
    }
}