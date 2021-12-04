package entities;

import entities.characters.Player;
import entities.weapons.Axe;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    @Test
    public void setHeldBy() {
        Item test = new Axe("test Axe");
        Player player = new Player("playerTest");
        test.setHeldBy(player);
        assertEquals(player, test.getHeldBy());
    }

    @Test
    public void getHeldBy() {
        Item test = new Axe("test Axe");
        Player player = new Player("playerTest");
        test.setHeldBy(player);
        assertEquals(player, test.getHeldBy());
    }
}