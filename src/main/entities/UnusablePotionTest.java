package entities;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class UnusablePotionTest {

    @Test
    public void addRestorationValue() {
        UnusablePotion potionTest = new UnusablePotion();
        potionTest.setProperties(new HashMap<String, Variable>());
        potionTest.addRestorationValue();

        assertTrue(potionTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}