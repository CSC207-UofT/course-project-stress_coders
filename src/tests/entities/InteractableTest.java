package entities;

import entities.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class InteractableTest {

    @Test
    public void getHelp() {
        Interactable testAxe = new Axe("the rapidly dwindling sanity of CSC207 " +
                "students viewed through test strings");
        assertEquals("to throw use throw: throw_obj:[weapon_id], target=[enemy_id] and to chop use " +
                "chop: tool=[weapon_id], target=[resource_id]", testAxe.getHelp());
    }

    @Test
    public void getId() {
        Interactable testAxe = new Axe("howdy big man");
        assertEquals("howdy big man", testAxe.getId());
    }

    @Test
    public void getInitialText() {
        Interactable testAxe = new Axe("howdy big man");
        testAxe.setInitialText("mooooooooooo");
        assertEquals("mooooooooooo", testAxe.getInitialText());
    }

    @Test
    public void isCompleted() {
        Interactable testEnemy = new Enemy("This class (JK)", new Player("id"), 1);
        assertFalse(testEnemy.isCompleted());
        testEnemy.setCompleted(true);
        assertTrue(testEnemy.isCompleted());
    }

    @Test
    public void setCompleted() {
        Interactable testEnemy = new Enemy("testID", new Player("id"), 1);
        testEnemy.setCompleted(false);
        assertFalse(testEnemy.isCompleted());
        testEnemy.setCompleted(true);
        assertTrue(testEnemy.isCompleted());
    }

    @Test
    public void addProperty() {
        Interactable testAxe = new Enemy("Around the world around the world ...", new Player("id"), 1);
        Variable testVar = new Variable(true);
        testAxe.addProperty("hohoho merry christmas", testVar);

        assertEquals(testVar, testAxe.getProperties().get("hohoho merry christmas"));
    }

    @Test
    public void getProperty() {
        Interactable testAxe = new Enemy("Around the world around the world ...", new Player("id"), 1);
        Variable testVar = new Variable(true);
        testAxe.addProperty("hohoho merry christmas", testVar);

        assertEquals(testVar, testAxe.getProperty("hohoho merry christmas"));
    }

    @Test
    public void setId() {
        Interactable testAxe = new Axe("id");
        testAxe.setId("testID2");

        assertEquals("testID2", testAxe.getId());

    }

    @Test
    public void getProperties() {
        Interactable testAxe = new Axe("Have you heard DEUSTCHLAND REMIX BY RICHARD Z KRUPSE?");
        testAxe.setId("testID2");

        assertTrue(testAxe.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
        assertTrue(testAxe.getProperties().containsKey(InteractableProperties.WEIGHT.name()));

    }
}