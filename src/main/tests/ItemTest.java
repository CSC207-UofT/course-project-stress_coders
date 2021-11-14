package tests;

import entities.Axe;
import entities.Item;
import entities.Player;
import org.junit.Test;

import static org.junit.Assert.*;
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