package entities;

import entities.characters.Player;
import entities.food.Potion;
import entities.interfaces.Consumable;
import entities.weapons.Axe;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void setWeapon() {
        Player testPlayer = new Player("id");
        Axe testAxe = new Axe("id");
        testPlayer.setWeapon(testAxe);
        assertEquals(testAxe, testPlayer.getCurrentWeapon());
    }

    @Test
    public void getCurrentWeapon() {
        Player testPlayer = new Player("id");
        Axe testAxe = new Axe("id");

        assertNotNull(testPlayer.getCurrentWeapon());
        testPlayer.setWeapon(testAxe);
        assertEquals(testAxe, testPlayer.getCurrentWeapon());
    }

    @Test
    public void getWallet() {
        Player testPlayer = new Player("id");

        assertEquals(0, testPlayer.getWallet());
        testPlayer.addCurrency(100);
        assertEquals(100, testPlayer.getWallet());
    }

    @Test
    public void handleHit() {
        Player testPlayer = new Player("id");
        testPlayer.setHealthPoints(10);
        Axe testAxe = new Axe("id2");
        assertEquals("You died", testPlayer.handleHit(testAxe));

        testPlayer.setHealthPoints(100);
        assertEquals("Your health has decreased by " + 20
                + ", you have " + 80 + " health points", testPlayer.handleHit(testAxe));
    }

    @Test
    public void addInventory() {
        Player testPlayer = new Player("id");
        testPlayer.addInventory("nuts", 2);

        assertEquals(2, testPlayer.inventoryAmount("nuts"));

        testPlayer.addInventory("nuts", 3);
        assertEquals(5, testPlayer.inventoryAmount("nuts"));
    }

    @Test
    public void subInventory() {
        Player testPlayer = new Player("id");
        testPlayer.addInventory("nuts", 5);

        testPlayer.subInventory("nuts", 2);
        assertEquals(3, testPlayer.inventoryAmount("nuts"));
    }

    @Test
    public void addConsumable() {
        Item potion = new Potion("Im listening to daft punk - harder better faster stronger as i write this");
        Player testPlayer = new Player("id");
        testPlayer.addConsumable(potion);

        assertEquals(testPlayer, ((Item) potion).getHeldBy());
        assertTrue(testPlayer.getItems().containsKey(potion));
    }

    @Test
    public void subConsumable() {
        Item potion = new Potion("Im listening to daft punk - harder better faster stronger as i write this");
        Player testPlayer = new Player("id");
        testPlayer.addConsumable(potion);
        testPlayer.addConsumable(potion);
        testPlayer.addConsumable(potion);

        testPlayer.subConsumable(potion, 1);
        assertEquals(2, (int) testPlayer.getItems().get(potion));
    }

    @Test
    public void inventoryAmount() {
        Player testPlayer = new Player("id");
        testPlayer.addInventory("nuts", 5);

        assertEquals(5, testPlayer.inventoryAmount("nuts"));
    }

    @Test
    public void itemAmount() {
        Item potion = new Potion("OOP OOP OOP");
        Player testPlayer = new Player("id");
        testPlayer.addConsumable(potion);
        testPlayer.addConsumable(potion);
        testPlayer.addConsumable(potion);

        assertEquals(3, testPlayer.itemAmount((Consumable) potion));
    }

    @Test
    public void addCurrency() {
        Player testPlayer = new Player("id");
        testPlayer.addCurrency(100);

        assertEquals(100, testPlayer.getWallet());
    }

    @Test
    public void subCurrency() {
        Player testPlayer = new Player("id");
        testPlayer.addCurrency(100);
        testPlayer.subCurrency(69);

        assertEquals(31, testPlayer.getWallet());
    }

    @Test
    public void getItems(){
        Player testPlayer = new Player("id");
        Item potion = new Potion("OOP OOP OOP");
        testPlayer.addConsumable(potion);
        HashMap<Item, Integer> testMap = new HashMap<>();

        testMap.put(potion, 1);
        assertEquals(testMap, testPlayer.getItems());
    }
}