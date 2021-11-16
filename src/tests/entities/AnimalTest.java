package entities;

import entities.interfaces.Consumable;
import org.junit.Test;
import usecases.Throw;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalTest {

    @Test
    public void setHealthPoints() {
        Player dummy = new Player("dummy");
        Animal animal = new Animal("id", 100, dummy);
        assertEquals(100, animal.getHealthPoints());
    }

    @Test
    public void handleHit() {
        Player dummy = new Player("dummy");
        Animal animal = new Animal("id", 10, dummy);
        Axe axe = new Axe("axe");
        axe.setHeldBy(dummy);
        Throw throwCmd = new Throw();
        HashMap<String, Interactable> args = new HashMap<>();
        args.put("throw_obj", axe);
        args.put("target", animal);
        String result = throwCmd.execute(args);
        if (result == "It missed") {
            assertTrue(!animal.isDead());
        } else {
            assertTrue(animal.isDead());
            boolean meatFound = false;
            for (Consumable item : dummy.getConsumables()) {
                if (item instanceof Meat) { meatFound = true; }
            }
            assertTrue(meatFound);
        }
    }
}
