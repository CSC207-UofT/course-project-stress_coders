package entities;

import entities.weapons.Crossbow;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CrossbowTest {

    @Test
    public void addHitProbability() {
        Crossbow crossbowTest = new Crossbow("id", 900000);
        crossbowTest.setProperties(new HashMap<String, Variable>());
        crossbowTest.addHitProbability();

        assertTrue(crossbowTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
    }

    @Test
    public void addWeight() {
        Crossbow crossbowTest = new Crossbow("id", 900000);
        crossbowTest.setProperties(new HashMap<String, Variable>());
        crossbowTest.addWeight();

        assertTrue(crossbowTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
    }
}