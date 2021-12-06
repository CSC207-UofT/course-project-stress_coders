package entities;

import entities.food.Potato;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class PotatoTest  {

    @Test
    public void addRestorationValue() {
        Potato PotatoTest = new Potato("id");
        PotatoTest.setProperties(new HashMap<>());
        PotatoTest.addRestorationValue();

        assertTrue(PotatoTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}