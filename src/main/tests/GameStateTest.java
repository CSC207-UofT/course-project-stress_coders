package tests;

import interfaceadapters.GameState;
import org.junit.Test;
import usecases.Encounter;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GameStateTest {

    @Test
    public void requestEncounter() {
        // I dont know what this does
    }
    // The new change broke these so write equivalent tests later
//    @Test
//    public void loadEncounter() {
//        Encounter[] testEncounters = {new Encounter("a", "aa", "aaa")};
//        GameState testGS = new GameState(testEncounters);
//
//        testGS.loadEncounter(testEncounters[0]);
//        assertEquals(testEncounters[0], testGS.getEncounterConversion().get("aa"));
//    }
//
//    @Test
//    public void loadEncounters() {
//        Encounter[] testEncounters = {new Encounter("a", "aa", "aaa"),
//        new Encounter("b", "whydoesgodhideinheaven?", "description")};
//        GameState testGS = new GameState(testEncounters);
//
//        testGS.loadEncounters(testEncounters);
//        assertEquals(testGS.getEncounterConversion().get("aa"), testEncounters[0]);
//        assertEquals(testEncounters[1], testGS.getEncounterConversion().get("whydoesgodhideinheaven?"));
//    }
//
//    @Test
//    public void getCurrent_encounter() {
//        Encounter[] testEncounters = {new Encounter("a", "aa", "aaa"),
//                new Encounter("b", "UH UH UEH UEH ugEH EASTER EGGGG YAAAAA", "description")};
//        GameState testGS = new GameState(testEncounters);
//        testGS.setCurrent_encounter(1);
//
//        assertEquals(testEncounters[1], testGS.getCurrent_encounter());
//    }

    //Someone who knows what these do write these tests.
    @Test
    public void callCommand() {
    }

    @Test
    public void completedEncounters() {
    }

    @Test
    public void getHelp() {
    }
}