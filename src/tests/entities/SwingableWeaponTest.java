package entities;

import entities.weapons.Sword;
import entities.weapons.SwingableWeapon;
import org.junit.Test;

import java.util.HashMap;
import static org.junit.Assert.*;
public class SwingableWeaponTest {

    @Test
    public void addHitProbability() {
        SwingableWeapon swordTest = new Sword("id");
        swordTest.setProperties(new HashMap<String, Variable>());
        swordTest.addHitProbability();

        assertTrue(swordTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
    }

    @Test
    public void addWeight() {
    }

}
