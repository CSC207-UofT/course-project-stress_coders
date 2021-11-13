package entities;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class AxeTest {

    @Test
    public void addHitProbability() {
        Axe axeTest = new Axe("id");
        axeTest.setProperties(new HashMap<String, Variable>());
        axeTest.addHitProbability();

        assertTrue(axeTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
    }

    @Test
    public void addWeight() {
        Axe axeTest = new Axe("id");
        axeTest.setProperties(new HashMap<String, Variable>());
        axeTest.addWeight();

        assertTrue(axeTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
    }

    @Test
    public void addChopDamage() {
        Axe axeTest = new Axe("id");
        axeTest.setProperties(new HashMap<String, Variable>());
        axeTest.addChopDamage();

        assertTrue(axeTest.getProperties().containsKey(InteractableProperties.CHOP_DMG_NAME.name()));
    }
}