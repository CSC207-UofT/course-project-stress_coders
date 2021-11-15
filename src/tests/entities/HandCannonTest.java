package entities;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class HandCannonTest {

    @Test
    public void addHitProbability() {
        HandCannon handCannonTest = new HandCannon("id", 1000);
        handCannonTest.setProperties(new HashMap<String, Variable>());
        handCannonTest.addHitProbability();

        assertTrue(handCannonTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
    }

    @Test
    public void addWeight() {
        HandCannon handCannonTest = new HandCannon("id", 1000);
        handCannonTest.setProperties(new HashMap<String, Variable>());
        handCannonTest.addWeight();

        assertTrue(handCannonTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
    }
}