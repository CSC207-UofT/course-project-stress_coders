package entities;

import entities.food.Food;
import entities.food.Nuts;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class FoodTest {

    @Test
    public void getID() {
        Food foodTest = new Nuts("id");
        assertEquals("id", foodTest.getId());
    }

    @Test
    public void addRestorationValue() {
        Food foodTest = new Nuts("id");
        foodTest.setProperties(new HashMap<String, Variable>());
        foodTest.addRestorationValue();

        assertTrue(foodTest.getProperties().containsKey(InteractableProperties.CONSUMABLE_REST_NAME.name()));
    }

    @Test
    public void consume() {
        // Finish Later.
    }
}