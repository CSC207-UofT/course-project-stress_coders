package entities;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class MeatTest {

    @Test
    public void getID() {
        Meat meatTest = new Meat("id");
        assertEquals("id", meatTest.getID());
    }

    @Test
    public void addRestorationValue() {
        Meat meatTest = new Meat("id");
        meatTest.setProperties(new HashMap<String, Variable>());
        meatTest.addRestorationValue();

        assertTrue(meatTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}