package entities;

import entities.food.WineBottle;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class WineTest {
    @Test
    public void getID() {
        WineBottle wineTest = new WineBottle("id");
        assertEquals("id", wineTest.getId());
    }

    @Test
    public void addRestorationValue() {
        WineBottle wineTest = new WineBottle("id");
        wineTest.setProperties(new HashMap<String, Variable>());
        wineTest.addRestorationValue();

        assertTrue(wineTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}
