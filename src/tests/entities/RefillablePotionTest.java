package entities;

import entities.characters.Player;
import entities.food.RefillablePotion;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RefillablePotionTest {

    @Test
    public void consumableRestorationValueAdded() {
        RefillablePotion potion = new RefillablePotion("id");
        potion.addRestorationValue();
        assertTrue(potion.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
        assertEquals(10, potion.getProperty(InteractableProperties.CONSUMABLE_REST_NAME.name()).getInteger());
    }

    @Test
    public void consume() {
        Player p = new Player("id");
        RefillablePotion potion = new RefillablePotion("id2");
        potion.setHeldBy(p);
        p.addConsumable(potion);
        int oldHP = p.getHealthPoints();
        potion.consume();
        potion.consume();
        potion.consume();
        String result = potion.consume();
        assertEquals("You don't have enough charges left!", result);
        assertEquals(oldHP + 30, p.getHealthPoints());
    }
}
