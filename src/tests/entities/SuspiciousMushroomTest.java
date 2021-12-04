package entities;

import entities.food.SuspiciousMushroom;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SuspiciousMushroomTest {

    @Test
    public void addRestorationValue() {
        SuspiciousMushroom mushroomTest = new SuspiciousMushroom("id");
        mushroomTest.addRestorationValue();
        assertTrue(mushroomTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
        assertTrue(mushroomTest.getProperty(InteractableProperties.CONSUMABLE_REST_NAME.name()).getInteger() < 11
        && mushroomTest.getProperty(InteractableProperties.CONSUMABLE_REST_NAME.name()).getInteger() > -6);
    }

}