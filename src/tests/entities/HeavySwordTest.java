package entities;

import entities.weapons.HeavySword;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;

public class HeavySwordTest {
    @Test
    public void addHitProbability() {
        HeavySword hswordTest = new HeavySword("id");
        hswordTest.setProperties(new HashMap<>());
        hswordTest.addHitProbability();

        assertTrue(hswordTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
        assertEquals(60, hswordTest.getProperty(InteractableProperties.HIT_PROB.name()).getInteger());
    }

    @Test
    public void addWeight() {
        HeavySword hswordTest = new HeavySword("id");
        hswordTest.setProperties(new HashMap<>());
        hswordTest.addWeight();

        assertTrue(hswordTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
    }
}

