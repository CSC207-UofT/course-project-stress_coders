import entities.Axe;
import entities.Interactable;
import entities.Potion;
import org.junit.Test;
import usecases.Encounter;

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
        Interactable axe = new Axe("id");

        testEncounter.addGeneric(axe);

        assertTrue(testEncounter.getGenericPool().contains(axe));
    }

    @Test
    public void loadInitial() {
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        assertEquals("init_text", testEncounter.loadInitial());
    }

    // This method was removed, leaving this here for the time being in case its readded
//    @Test
//    public void loadFirstInteractable() {
//        Encounter testEncounter = new Encounter("init_text", "name", "description");
//        Interactable axe = new Axe("id");
//        axe.setInitialText("Init TEXT");
//        Interactable axe2 = new Axe("id2");
//
//        testEncounter.addGeneric(axe);
//        testEncounter.addGeneric(axe2);
//
//        assertEquals(axe.getInitialText(), testEncounter.loadFirstInteractable());
//    }

    @Test
    public void progress() {
        // Add test here Im not sure how to test this
    }

    @Test
    public void addObj() {
        Encounter testEncounter = new Encounter("init_text", "name", "description");
        Interactable axe = new Axe("id");
        testEncounter.addObj(axe);
        assertEquals(axe, testEncounter.getFromID("id"));
    }

    @Test
    public void addAdjective() {
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
        testEncounter.addObj(potion);
        assertEquals(potion, testEncounter.getFromID("id22"));
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

        assertEquals(testPool, testEncounter.getGenericPool());
    }
}