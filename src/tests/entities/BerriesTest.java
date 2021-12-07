package entities;

import entities.food.Berries;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class BerriesTest{

    @Test
    public void addRestorationValue() {
        Berries berriesTest = new Berries("id");
        berriesTest.setProperties(new HashMap<>());
        berriesTest.addRestorationValue();

        assertTrue(berriesTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}