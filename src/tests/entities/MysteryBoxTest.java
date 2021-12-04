package entities;

import entities.characters.Player;
import entities.weapons.Axe;
import entities.weapons.Weapon;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MysteryBoxTest {

    @Test
    public void loadWeapons() {
        List<Weapon> weapons = new ArrayList<>();
        weapons.add(new Axe("axe1"));
        weapons.add(new Axe("axe2"));
        MysteryBox mysteryBoxTest = new MysteryBox("im gonna make a CSC207 mixTape",weapons, new Player("id"));

        mysteryBoxTest.loadWeapons(weapons);

        assertTrue(mysteryBoxTest.weapons.contains(weapons.get(0)));
        assertTrue(mysteryBoxTest.weapons.contains(weapons.get(1)));
    }

    @Test
    public void addWeapon() {
        List<Weapon> weapons = new ArrayList<>();
        Axe testAxe = new Axe("axe1");
        weapons.add(testAxe);
        MysteryBox mysteryBoxTest = new MysteryBox("the first track will be", weapons , new Player("id"));

        mysteryBoxTest.addWeapon(testAxe);

        assertTrue(mysteryBoxTest.weapons.contains(testAxe));
    }

    @Test
    public void spin() {
        Player player = new Player("id");
        List<Weapon> weapons = new ArrayList<>();
        Axe testAxe = new Axe("axe1");
        weapons.add(testAxe);
        MysteryBox mysteryBoxTest = new MysteryBox("I once knew a man named J calv", weapons, player);
        player.addCurrency(100);
        assertTrue(mysteryBoxTest.spin() instanceof Unafforable);
        player.addCurrency(900);
        assertEquals(testAxe, mysteryBoxTest.spin());
    }

    @Test
    public void changePlayerWeapon() {
        Player player = new Player("id");
        List<Weapon> weapons = new ArrayList<>();
        Axe testAxe = new Axe("axe1");
        weapons.add(testAxe);
        MysteryBox mysteryBoxTest = new MysteryBox("billboard top 100 is sweating right now", weapons, player);
        mysteryBoxTest.changePlayerWeapon(testAxe);
        assertEquals(testAxe, player.getCurrentWeapon());
    }
}