package tests;

import entities.Axe;
import entities.Weapon;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class WeaponTest {

    @Test
    public void getDamage() {
        Weapon testAxe = new Axe("2");
        testAxe.setDamage(100);

        assertEquals(100, testAxe.getDamage());
    }

    @Test
    public void setDamage(){
        Weapon testAxe = new Axe("2");
        testAxe.setDamage(100);

        assertEquals(100, testAxe.getDamage());
    }
}