package entities.food;

import entities.InteractableProperties;
import entities.Variable;
import entities.food.Food;
import entities.interfaces.Consumable;

import java.util.Random;

public class SuspiciousMushroom extends Food implements Consumable {

    /**
     * Creates a new Suspicious Mushroom object.
     * This constructor takes in a String as the argument.
     *
     * @param id the id of this potato.
     */
    public SuspiciousMushroom(String id) {
        super(id);
    }

    /**
     * Adds property CONSUMABLE_REST_NAME with a restoration value between -5 and 10
     */

    Random r = new Random();
    int chosen = (r.nextInt(15) - 5);

    public void addRestorationValue() {
        super.addProperty(InteractableProperties.CONSUMABLE_REST_NAME.name(), new Variable(chosen));
    }
}

