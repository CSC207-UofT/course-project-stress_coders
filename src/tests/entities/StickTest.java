package entities;

import entities.weapons.Stick;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class StickTest {
    @Test
    public void addHitProbability() {
        Stick stickTest = new Stick("id");
        stickTest.setProperties(new HashMap<>());
        stickTest.addHitProbability();

        assertTrue(stickTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
        assertEquals(95, stickTest.getProperty(InteractableProperties.HIT_PROB.name()).getInteger());
    }

    @Test
    public void addWeight() {
        Stick stickTest = new Stick("id");
        stickTest.setProperties(new HashMap<>());
        stickTest.addWeight();

        assertTrue(stickTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
        assertTrue(10 <= stickTest.getProperty(InteractableProperties.WEIGHT.name()).getInteger() &&
                stickTest.getProperty(InteractableProperties.WEIGHT.name()).getInteger() <= 15);
    }
}
