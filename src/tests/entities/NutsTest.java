package entities;

import entities.food.Nuts;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
public class NutsTest {

    @Test
    public void addRestorationValue() {
        Nuts nutsTest = new Nuts("id");
        nutsTest.setProperties(new HashMap<>());
        nutsTest.addRestorationValue();

        assertTrue(nutsTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}