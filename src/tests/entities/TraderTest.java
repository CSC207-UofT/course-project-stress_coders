package entities;

import entities.interfaces.Consumable;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TraderTest {

    @Test
    public void trade() {
        Player p = new Player("devanRocks");
        p.addCurrency(100);
        Trader t = new Trader("id", p);
        Nuts nut = new Nuts("nutty");
        HashMap<String, Consumable> inventoryToAdd = new HashMap<String, Consumable>();
        inventoryToAdd.put(nut.getId(), nut);
        t.addConsumablesToStore(inventoryToAdd);
        t.trade();

        // how do i make the user ask to trade 'nutty'


        assertEquals(nut.getId(), p.getConsumables().get(0).getId());
    }

    @Test
    public void addConsumablesToStore() {
        Player p = new Player("devanRocks");
        Trader t = new Trader("id", p);
        Nuts nut = new Nuts("nutty");
        HashMap<String, Consumable> inventoryToAdd = new HashMap<String, Consumable>();
        inventoryToAdd.put(nut.getId(), nut);
        t.addConsumablesToStore(inventoryToAdd);
        assertTrue(t.getInventory().containsKey(nut.getId()));
        assertEquals(1, t.getInventory().size());
    }
}