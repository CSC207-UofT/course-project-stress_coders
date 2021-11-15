
import entities.InteractableProperties;
import entities.UnusablePotion;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class UnusablePotionTest {

    @Test
    public void addRestorationValue() {
        UnusablePotion potionTest = new UnusablePotion();
        potionTest.setProperties(new HashMap<>());
        potionTest.addRestorationValue();

        assertTrue(potionTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}