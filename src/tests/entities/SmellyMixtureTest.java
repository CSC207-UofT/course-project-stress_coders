package entities;

import entities.food.SmellyMixture;

import java.util.HashMap;

public class SmellyMixtureTest {
    @Test
    public void addRestorationValue() {
        SmellyMixture smellyMixtureTest = new SmellyMixture("id");
        smellyMixtureTest.setProperties(new HashMap<String, Variable>());
        smellyMixtureTest.addRestorationValue();

        assertTrue(smellyMixtureTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}
