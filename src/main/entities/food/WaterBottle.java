package entities.food;

import entities.InteractableProperties;
import entities.Variable;
import entities.interfaces.Consumable;

public class WaterBottle extends Food implements Consumable {
    /**
     * Creates a new WineBottle object.
     * This constructor takes in a String as the argument.
     *
     * @param id the id of this wine bottle.
     */
    public WaterBottle(String id) {
        super(id);
        this.addRestorationValue();
    }
    /**
     * Adds property CONSUMABLE_REST_NAME with the restoration value
     */
    public void addRestorationValue() {
        super.addProperty(InteractableProperties.CONSUMABLE_REST_NAME.name(), new Variable(2));
    }
    public WaterBottle(){}
    }

