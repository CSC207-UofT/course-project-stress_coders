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
        assertEquals("name : description", testEncounter.getDetails());
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

        testEncounter.addGeneric(tree);

        assertTrue(testEncounter.getGenericPool().contains(tree));
    }

    @Test
    public void loadInitial() {
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        assertEquals("init_text", testEncounter.loadInitial());
    }

    @Test
    public void progress() {
        // Add test here Im not sure how to test this
    }

    @Test
    public void addObj() {
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        Interactable axe = new Axe("id22");
        testEncounter.addObj(axe);
        assertNull(testEncounter.getFromID("id22"));
    }

    @Test
    public void addAdjective() throws IOException {
        IDreader idReader = new IDreader();
        idReader.initAdjectives();
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        Interactable axe = new Axe("id");
        Interactable axe2 = new Axe("id");
        testEncounter.addObj(axe);
        testEncounter.addObj(axe2);
        assertNotEquals("id", axe2.getId());
    }

    @Test
    public void getFromID() {
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        Interactable potion = new Potion("id22");
        Interactable axe = new Axe("id3333");
        Tree treeTest = new Tree("ah");
        testEncounter.addObj(potion);
        testEncounter.addObj(axe);
        testEncounter.addObj(treeTest);
        //Both are items
        assertNull(testEncounter.getFromID("id22"));
        assertNull(testEncounter.getFromID("id3333"));
        assertEquals(treeTest, testEncounter.getFromID("ah"));
    }

    @Test
    public void isCompleted() {
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        assertFalse(testEncounter.isCompleted());
        // Add test here, this is can be done better
    }

    @Test
    public void getHelp() {
        // Add test here, im not sure what to do here
    }

    @Test
    public void getGenericPool(){
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        Interactable axe = new Axe("id");

        testEncounter.addGeneric(axe);
        ArrayList<Interactable> testPool = new ArrayList<>();
        testPool.add(axe);

        // since axe is an entity it wont get added to the pool
        ArrayList<Interactable> testPoolActual = new ArrayList<>();

        assertEquals(testPoolActual, testEncounter.getGenericPool());
    }
}