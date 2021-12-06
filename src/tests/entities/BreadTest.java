package entities;

import entities.food.Bread;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class BreadTest {
    @Test
    public void getID() {
        Bread breadTest = new Bread("id");
        assertEquals("id", breadTest.getId());
    }

    @Test
    public void addRestorationValue() {
        Bread breadTest = new Bread("id");
        breadTest.setProperties(new HashMap<>());
        breadTest.addRestorationValue();

        assertTrue(breadTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}
