package entities;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ThrowingKnifeTest {
    @Test
    public void addHitProbability() {
        ThrowingKnife knifeTest = new ThrowingKnife("id");
        knifeTest.setProperties(new HashMap<String, Variable>());
        knifeTest.addHitProbability();

        assertTrue(knifeTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
        assertEquals(95, knifeTest.getProperty(InteractableProperties.HIT_PROB.name()).getInteger());
    }

    @Test
    public void addWeight() {
        ThrowingKnife knifeTest = new ThrowingKnife("id");
        knifeTest.setProperties(new HashMap<String, Variable>());
        knifeTest.addWeight();

        assertTrue(knifeTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
        assertEquals(12, knifeTest.getProperty(InteractableProperties.WEIGHT.name()).getInteger());
    }
}
