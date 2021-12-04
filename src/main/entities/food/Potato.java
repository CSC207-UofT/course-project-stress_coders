package entities.food;
import entities.InteractableProperties;
import entities.Variable;
import entities.interfaces.Consumable;

public class Potato extends Food implements Consumable {
    /**
     * Creates a new Potato object.
     * This constructor takes in a String as the argument.
     *
     * @param id the id of this potato.
     */
    public Potato(String id) {
        super(id);
        this.addRestorationValue();
    }

    /**
     * Adds property CONSUMABLE_REST_NAME with the restoration value
     */
    public void addRestorationValue() {
        super.addProperty(InteractableProperties.CONSUMABLE_REST_NAME.name(), new Variable(10));
    }

}


