package entities;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class PotionTest {

    @Test
    public void addHitProbability() {
        Potion potionTest = new Potion("id");
        potionTest.setProperties(new HashMap<String, Variable>());
        potionTest.addHitProbability();

        assertTrue(potionTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
    }

    @Test
    public void addWeight() {
        Potion potionTest = new Potion("id");
        potionTest.setProperties(new HashMap<String, Variable>());
        potionTest.addWeight();

        assertTrue(potionTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
    }

    @Test
    public void addRestorationValue() {
        Potion potionTest = new Potion("id");
        potionTest.setProperties(new HashMap<String, Variable>());
        potionTest.addRestorationValue();

        assertTrue(potionTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }

    @Test
    public void consume() {
        Player player = new Player("id");
        Potion potionTest = new Potion("id2");

        int initHP = player.getHealthPoints();
        assertEquals("You don't hold this!", potionTest.consume());

        player.addConsumable(potionTest);
        assertEquals("You consumed 1 id2", potionTest.consume());
        assertEquals(player.getHealthPoints(), initHP + 15);
    }
}