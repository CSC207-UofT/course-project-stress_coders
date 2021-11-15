package entities;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ShootableWeaponTest  {

    @Test
    public void addHitProbability() {
        ShootableWeapon handCannonTest = new HandCannon("id", 1000);
        handCannonTest.setProperties(new HashMap<String, Variable>());
        handCannonTest.addHitProbability();

        assertTrue(handCannonTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
    }

    @Test
    public void addWeight() {
    }

    @Test
    public void addAmmo() {
    }

    @Test
    public void getAmmoCount() {
    }

    @Test
    public void spendAmmo() {
    }

    @Test
    public void changeAmmoPerShot() {
    }
}