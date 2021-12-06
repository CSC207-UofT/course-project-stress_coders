package entities;

import entities.weapons.Stick;
import org.junit.Test;

import java.util.HashMap;
import static org.junit.Assert.*;
public class StickTest {
    @Test
    public void addHitProbability() {
        Stick stickTest = new Stick("id");
        stickTest.setProperties(new HashMap<String, Variable>());
        stickTest.addHitProbability();

        assertTrue(stickTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
        assertEquals(95, stickTest.getProperty(InteractableProperties.HIT_PROB.name()).getInteger());
    }

    @Test
    public void addWeight() {
        Stick stickTest = new Stick("id");
        stickTest.setProperties(new HashMap<String, Variable>());
        stickTest.addWeight();

        assertTrue(stickTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
        assertEquals(12, stickTest.getProperty(InteractableProperties.WEIGHT.name()).getInteger());
    }
}
