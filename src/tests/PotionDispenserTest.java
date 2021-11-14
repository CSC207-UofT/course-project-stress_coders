
import entities.Player;
import entities.Potion;
import entities.PotionDispenser;
import entities.UnusablePotion;
import org.junit.Test;

import static org.junit.Assert.*;

public class PotionDispenserTest {

    @Test
    public void loadPotions() {
        Potion[] testPotions = {new Potion("1"), new Potion("2")};
        PotionDispenser testPotionDispenser = new PotionDispenser("id", new Potion[0], new Player("id"));
        testPotionDispenser.loadPotions(testPotions);

        assertTrue(testPotionDispenser.potions.contains(testPotions[0]));
        assertTrue(testPotionDispenser.potions.contains(testPotions[1]));
    }

    @Test
    public void addPotion() {
        Potion testPotion = new Potion("1");
        PotionDispenser testPotionDispenser = new PotionDispenser("id", new Potion[0], new Player("id"));
        testPotionDispenser.addPotion(testPotion);

        assertTrue(testPotionDispenser.potions.contains(testPotion));
    }

    @Test
    public void spin() {
        Player testPlayer = new Player("1");

        testPlayer.addCurrency(100);
        Potion testPotion = new Potion("1");

        Potion[] testPotions = {testPotion};
        PotionDispenser testPotionDispenser = new PotionDispenser("id", testPotions, testPlayer);

        assertTrue(testPotionDispenser.spin() instanceof UnusablePotion);
        testPlayer.addCurrency(900);

        assertEquals(testPotion, testPotionDispenser.spin());
    }
}