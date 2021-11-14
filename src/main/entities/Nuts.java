package entities;
import entities.interfaces.Consumable;

public class Nuts extends Food implements Consumable {

    /**
     * Creates a new Nuts object.
     * This constructor takes in a String as the argument.
     *
     * @param id the id of these nuts.
     */
    public Nuts(String id) {
        super(id);
    }

    /**
     * Adds property CONSUMABLE_REST_NAME with the restoration value
     */
    public void addRestorationValue() { super.addProperty(InteractableProperties.CONSUMABLE_REST_NAME.name(), new Variable(10)); }

}

