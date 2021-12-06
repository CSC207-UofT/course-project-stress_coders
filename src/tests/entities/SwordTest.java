package entities;

import entities.weapons.Sword;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class SwordTest {
    @Test
    public void addHitProbability() {
        Sword swordTest = new Sword("id");
        swordTest.setProperties(new HashMap<>());
        swordTest.addHitProbability();

        assertTrue(swordTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
        assertEquals(85, swordTest.getProperty(InteractableProperties.HIT_PROB.name()).getInteger());
    }

    @Test
    public void addWeight() {
        Sword swordTest = new Sword("id");
        swordTest.setProperties(new HashMap<>());
        swordTest.addWeight();

        assertTrue(swordTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
    }
}
