package entities;

import entities.food.SmellyMixture;
import org.junit.Test;

import java.util.HashMap;
import static org.junit.Assert.*;
public class SmellyMixtureTest {
    @Test
    public void addRestorationValue() {
        SmellyMixture smellyMixtureTest = new SmellyMixture("id");
        smellyMixtureTest.setProperties(new HashMap<String, Variable>());
        smellyMixtureTest.addRestorationValue();

        assertTrue(smellyMixtureTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}
