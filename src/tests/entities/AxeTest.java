package entities;

import entities.Axe;
import entities.InteractableProperties;
import org.junit.Test;

import java.util.HashMap;
import static org.junit.Assert.assertTrue;

public class AxeTest {

    @Test
    public void addHitProbability() {
        Axe axeTest = new Axe("id");
        axeTest.setProperties(new HashMap<>());
        axeTest.addHitProbability();

        assertTrue(axeTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
    }

    @Test
    public void addWeight() {
        Axe axeTest = new Axe("id");
        axeTest.setProperties(new HashMap<>());
        axeTest.addWeight();

        assertTrue(axeTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
    }

    @Test
    public void addChopDamage() {
        Axe axeTest = new Axe("id");
        axeTest.setProperties(new HashMap<>());
        axeTest.addChopDamage();

        assertTrue(axeTest.getProperties().containsKey(InteractableProperties.CHOP_DMG_NAME.name()));
    }
}