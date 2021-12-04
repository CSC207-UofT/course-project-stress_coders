package entities;

import entities.weapons.Spear;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class SpearTest {

    @Test
    public void addHitProbability() {
        Spear spearTest = new Spear("id");
        spearTest.setProperties(new HashMap<String, Variable>());
        spearTest.addHitProbability();

        assertTrue(spearTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
    }

    @Test
    public void addWeight() {
        Spear spearTest = new Spear("id");
        spearTest.setProperties(new HashMap<String, Variable>());
        spearTest.addWeight();

        assertTrue(spearTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
    }
}