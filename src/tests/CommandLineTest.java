import entities.Axe;
import entities.Interactable;
import frameworks.CommandLine;
import interfaceadapters.GameState;
import org.junit.Test;
import usecases.Encounter;
import usecases.IDreader;
import usecases.PlayerManager;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CommandLineTest {

    @Test
    public void parseCommand() throws IOException {
        String input = "weapon=axe, target=207gpa";
        HashMap<String, String> expected = new HashMap<>();
        expected.put("weapon", "axe");
        expected.put("target", "207gpa");

        CommandLine CLTest = new CommandLine();
        assertEquals(expected, CLTest.parseCommand(input));

        String error_input = "weapon=ax====e, target=207gpa";
        HashMap<String, String> expected_error = new HashMap<>();
        assertEquals(expected_error, CLTest.parseCommand(error_input));
    }

    @Test
    public void getInteractablesFromID() throws IOException {
        Encounter[] testEncounter = {new Encounter("why is this different", "idk", "pain")};
        GameState testGS = new GameState(testEncounter);
        PlayerManager pl = new PlayerManager("player", "easy");
        Axe testAxe = new Axe("axe ah ah ah ah ah");
        pl.getPlayer().setWeapon(testAxe);
        testEncounter[0].addObj(testAxe);

        HashMap<String, String> testHM = new HashMap<>();
        testHM.put("weapon", "axe ah ah ah ah ah");
        HashMap<String, Interactable> expected = new HashMap<>();
        expected.put("weapon", testAxe);

        CommandLine CLTest = new CommandLine(testGS);
        assertEquals(expected, CLTest.getInteractablesFromID(testHM));
    }

    @Test
    public void callCommand() throws IOException {
        IDreader idReader = new IDreader();
        String error_input = "im:going:to:lose:my:mind";
        Encounter[] testEncounter = {new Encounter("what is this project", "idk", "pain")};
        GameState testGS = new GameState(testEncounter);
        Axe testAxe = new Axe("axe ah ah ah ah ah");
        PlayerManager p = new PlayerManager("player", "easy");
        p.getPlayer().setWeapon(testAxe);
        testEncounter[0].addObj(testAxe);

        CommandLine CLTest = new CommandLine(testGS);

        String expected_error = "Unrecognized input";

        assertEquals(expected_error, CLTest.callCommand(error_input));

        String second_error_input = "thisisnotacommanditsanencodedmessageorisitidontknow: weapon=test, args=lmao";
        String second_expected_error = "Not a command";
        assertEquals(second_expected_error, CLTest.callCommand(second_error_input));

    }
}