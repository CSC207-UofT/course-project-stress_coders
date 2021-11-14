import entities.Axe;
import entities.InteractableProperties;
import entities.Variable;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

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