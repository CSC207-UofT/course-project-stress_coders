package entities;

import entities.food.WaterBottle;
import org.junit.Test;

import java.util.HashMap;
import static org.junit.Assert.*;
public class WaterTest {
    @Test
    public void getID() {
        WaterBottle waterTest = new WaterBottle("id");
        assertEquals("id", waterTest.getId());
    }

    @Test
    public void addRestorationValue() {
        WaterBottle waterTest = new WaterBottle("id");
        waterTest.setProperties(new HashMap<String, Variable>());
        waterTest.addRestorationValue();

        assertTrue(waterTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}
