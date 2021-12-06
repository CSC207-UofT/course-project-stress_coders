package entities;

import entities.weapons.Knife;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class KnifeTest {
    @Test
    public void addHitProbability() {
        Knife knifeTest = new Knife("id");
        knifeTest.setProperties(new HashMap<String, Variable>());
        knifeTest.addHitProbability();

        assertTrue(knifeTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
        assertEquals(65, knifeTest.getProperty(InteractableProperties.HIT_PROB.name()).getInteger());
    }

    @Test
    public void addWeight() {
        Knife knifeTest = new Knife("id");
        knifeTest.setProperties(new HashMap<String, Variable>());
        knifeTest.addWeight();

        assertTrue(knifeTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
    }
}
