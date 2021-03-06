package entities;

import entities.weapons.Sword;
import entities.weapons.SwingableWeapon;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;

public class SwingableWeaponTest {

    @Test
    public void addHitProbability() {
        SwingableWeapon swordTest = new Sword("id");
        swordTest.setProperties(new HashMap<>());
        swordTest.addHitProbability();

        assertTrue(swordTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
    }

}
