package entities;

import entities.food.SmellyMixture;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class SmellyMixtureTest {
    @Test
    public void addRestorationValue() {
        SmellyMixture smellyMixtureTest = new SmellyMixture("id");
        smellyMixtureTest.setProperties(new HashMap<>());
        smellyMixtureTest.addRestorationValue();

        assertTrue(smellyMixtureTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}
