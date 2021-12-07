package entities;

import entities.weapons.HandCannon;
import entities.weapons.ShootableWeapon;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ShootableWeaponTest  {

    @Test
    public void addHitProbability() {
        ShootableWeapon handCannonTest = new HandCannon("id", 1000);
        handCannonTest.setProperties(new HashMap<>());
        handCannonTest.addHitProbability();

        assertTrue(handCannonTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
    }

}