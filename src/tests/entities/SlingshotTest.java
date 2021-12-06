package entities;

import entities.weapons.Slingshot;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class SlingshotTest{

    @Test
    public void addHitProbability() {
        Slingshot slingshotTest = new Slingshot("id", 1000000);
        slingshotTest.setProperties(new HashMap<>());
        slingshotTest.addHitProbability();

        assertTrue(slingshotTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
    }

    @Test
    public void addWeight() {
        Slingshot slingshotTest = new Slingshot("id", 1000000);
        slingshotTest.setProperties(new HashMap<>());
        slingshotTest.addWeight();

        assertTrue(slingshotTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
    }
}