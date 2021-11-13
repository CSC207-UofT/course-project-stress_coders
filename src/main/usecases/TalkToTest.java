package usecases;

import entities.Interactable;
import entities.Player;
import entities.RiddleGoblin;
import entities.Tree;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TalkToTest {

    @Test
    public void execute() {
        HashMap<String, Interactable> testArgs = new HashMap<>();
        Tree treeTest = new Tree("id");
        testArgs.put("receiver", treeTest);

        TalkTo talkToCommand = new TalkTo();

        assertEquals("They cannot talk!", talkToCommand.execute(testArgs));

        RiddleGoblin riddleGoblin = new RiddleGoblin("id2", new Player("1"), 1);
        // Cannot test the rest because there is a scanner
    }
}