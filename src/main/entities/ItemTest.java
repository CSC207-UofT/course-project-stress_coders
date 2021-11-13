package entities;

import org.junit.Test;

import static org.junit.Assert.*;

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