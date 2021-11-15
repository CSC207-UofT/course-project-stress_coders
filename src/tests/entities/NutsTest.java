package entities;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
public class NutsTest {

    @Test
    public void addRestorationValue() {
        Nuts nutsTest = new Nuts("id");
        nutsTest.setProperties(new HashMap<String, Variable>());
        nutsTest.addRestorationValue();

        assertTrue(nutsTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }
}