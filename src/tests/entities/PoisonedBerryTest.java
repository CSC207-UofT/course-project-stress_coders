package entities;

import entities.food.PoisonedBerry;
import org.junit.Test;

import java.util.HashMap;
import static org.junit.Assert.*;
public class PoisonedBerryTest {
    @Test
    public void addRestorationValue() {
        PoisonedBerry pberryTest = new PoisonedBerry("id");
        pberryTest.setProperties(new HashMap<String, Variable>());
        pberryTest.addRestorationValue();

        assertTrue(pberryTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}
