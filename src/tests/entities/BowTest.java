package entities;

import entities.weapons.Bow;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class BowTest {
    @Test
    public void addHitProbability() {
        Bow bowTest = new Bow("id", 900000);
        bowTest.setProperties(new HashMap<>());
        bowTest.addHitProbability();

        assertTrue(bowTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
    }

    @Test
    public void addWeight() {
        Bow bowTest = new Bow("id", 900000);
        bowTest.setProperties(new HashMap<>());
        bowTest.addWeight();

        assertTrue(bowTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
    }
}
