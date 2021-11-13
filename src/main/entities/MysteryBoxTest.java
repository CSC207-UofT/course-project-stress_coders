package entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class MysteryBoxTest {

    @Test
    public void loadWeapons() {
        Weapon[] weapons = {new Axe("axe1"), new Axe("axe2")};
        MysteryBox mysteryBoxTest = new MysteryBox("im gonna make a CSC207 mixTape", new Weapon[0], new Player("id"));

        mysteryBoxTest.loadWeapons(weapons);

        assertTrue(mysteryBoxTest.weapons.contains(weapons[0]));
        assertTrue(mysteryBoxTest.weapons.contains(weapons[1]));
    }

    @Test
    public void addWeapon() {
        Axe testAxe = new Axe("axe1");
        MysteryBox mysteryBoxTest = new MysteryBox("the first track will be", new Weapon[0], new Player("id"));

        mysteryBoxTest.addWeapon(testAxe);

        assertTrue(mysteryBoxTest.weapons.contains(testAxe));
    }

    @Test
    public void spin() {
        Player player = new Player("id");
        Axe testAxe = new Axe("axe1");
        Weapon[] weapons = {testAxe};
        MysteryBox mysteryBoxTest = new MysteryBox("I once knew a man named J calv", weapons, player);
        player.addCurrency(100);
        assertTrue(mysteryBoxTest.spin() instanceof Unafforable);
        player.addCurrency(900);
        assertEquals(testAxe, mysteryBoxTest.spin());
    }

    @Test
    public void changePlayerWeapon() {
        Player player = new Player("id");
        Axe testAxe = new Axe("axe1");
        Weapon[] weapons = {testAxe};
        MysteryBox mysteryBoxTest = new MysteryBox("billboard top 100 is sweating right now", weapons, player);
        mysteryBoxTest.changePlayerWeapon(testAxe);
        assertEquals(testAxe, player.getCurrentWeapon());
    }
}