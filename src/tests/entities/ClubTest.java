package entities;

import entities.weapons.Club;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;

public class ClubTest {
    @Test
    public void addHitProbability() {
        Club clubTest = new Club("id");
        clubTest.setProperties(new HashMap<String, Variable>());
        clubTest.addHitProbability();

        assertTrue(clubTest.getProperties().containsKey(InteractableProperties.HIT_PROB.name()));
        assertEquals(95, clubTest.getProperty(InteractableProperties.HIT_PROB.name()).getInteger());
    }

    @Test
    public void addWeight() {
        Club clubTest = new Club("id");
        clubTest.setProperties(new HashMap<String, Variable>());
        clubTest.addWeight();

        assertTrue(clubTest.getProperties().containsKey(InteractableProperties.WEIGHT.name()));
    }
}

