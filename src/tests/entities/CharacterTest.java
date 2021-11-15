package entities;

import entities.Character;
import entities.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

    @Test
    public void setHealthPoints() {
        Character charTest = new Player("player");

        charTest.setHealthPoints(10);
        assertEquals(10, charTest.getHealthPoints());

        charTest.setHealthPoints(charTest.getMaxHealthPoints() + 2);
        assertEquals(charTest.getMaxHealthPoints(), charTest.getHealthPoints());
    }

    @Test
    public void setMaxHealthPoints() {
        Character charTest = new Player("player");

        charTest.setMaxHealthPoints(1000);
        assertEquals(1000, charTest.getMaxHealthPoints());
    }

    @Test
    public void getHealthPoints() {
        Character charTest = new Player("player");

        charTest.setHealthPoints(10);
        assertEquals(10, charTest.getHealthPoints());
    }

    @Test
    public void isDead() {
        Character charTest = new Player("player");

        charTest.setHealthPoints(0);
        assertTrue(charTest.isDead());

        charTest.setHealthPoints(1);
        assertFalse(charTest.isDead());
    }

    @Test
    public void addModifier() {
        Character charTest = new Player("player");
        int healthTest = charTest.getHealthPoints();
        charTest.addModifier(2);

        assertEquals(healthTest * 2, charTest.getHealthPoints());
    }
}