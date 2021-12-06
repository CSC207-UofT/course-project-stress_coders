package entities;

import entities.food.PoisonedBerry;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class PoisonedBerryTest {
    @Test
    public void addRestorationValue() {
        PoisonedBerry pberryTest = new PoisonedBerry("id");
        pberryTest.setProperties(new HashMap<>());
        pberryTest.addRestorationValue();

        assertTrue(pberryTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}
