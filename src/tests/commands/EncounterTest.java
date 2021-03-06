package commands;

import Style.ColorConstants;
import entities.weapons.Axe;
import entities.Interactable;
import entities.food.Potion;
import org.junit.Test;
import usecases.Encounter;
import usecases.IDreader;
import entities.Tree;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EncounterTest {

    @Test
    public void getDetails() {
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        assertEquals(ColorConstants.getColorCode("BLUE") + "name" + ColorConstants.getColorCode("RESET") +
                        " : " + ColorConstants.getColorCode("GREEN") + "description" + ColorConstants.getColorCode("RESET"),
                testEncounter.getDetails());
    }

    @Test
    public void getName() {
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        assertEquals("name", testEncounter.getName());
    }

    @Test
    public void addGeneric() {
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        Interactable tree = new Tree("Oak");

        testEncounter.getInteractablesManager().addGeneric(tree);

        assertTrue(testEncounter.getGenericPool().contains(tree));
    }

    @Test
    public void loadInitial() {
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        assertEquals("init_text", testEncounter.loadInitial());
    }

    @Test
    public void addObj() {
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        Interactable axe = new Axe("id22");
        testEncounter.getInteractablesManager().addObj(axe);
        assertNull(testEncounter.getInteractablesManager().getFromID("id22"));
    }

    @Test
    public void addAdjective() throws IOException {
        IDreader idReader = new IDreader();
        idReader.initAdjectives();
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        Interactable axe = new Axe("id");
        Interactable axe2 = new Axe("id");
        testEncounter.getInteractablesManager().addObj(axe);
        testEncounter.getInteractablesManager().addObj(axe2);
        assertNotEquals("id", axe2.getId());
    }

    @Test
    public void getFromID() {
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        Interactable potion = new Potion("id22");
        Interactable axe = new Axe("id3333");
        Tree treeTest = new Tree("ah");
        testEncounter.getInteractablesManager().addObj(potion);
        testEncounter.getInteractablesManager().addObj(axe);
        testEncounter.getInteractablesManager().addObj(treeTest);
        //Both are items
        assertNull(testEncounter.getInteractablesManager().getFromID("id22"));
        assertNull(testEncounter.getInteractablesManager().getFromID("id3333"));
        assertEquals(treeTest, testEncounter.getInteractablesManager().getFromID("ah"));
    }

    @Test
    public void isCompleted() {
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        assertFalse(testEncounter.isCompleted());
        // Add test here, this is can be done better
    }

    @Test
    public void getGenericPool(){
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        Interactable axe = new Axe("id");

        testEncounter.getInteractablesManager().addGeneric(axe);
        ArrayList<Interactable> testPool = new ArrayList<>();
        testPool.add(axe);

        // since axe is an entity it wont get added to the pool
        ArrayList<Interactable> testPoolActual = new ArrayList<>();

        assertEquals(testPoolActual, testEncounter.getGenericPool());
    }
}