package entities.food;


import entities.InteractableProperties;
import entities.Variable;
import entities.interfaces.Consumable;
/**
 A meat class to consume

 Meat object should not be created on it's own, Animal classes create a meat object
 **/

public class Meat extends Food implements Consumable {
    /**
     * Construct the Meat
     *
     * @param id the appropriate ID for Meat
     */
    public Meat(String id) {
        super(id);
        this.addRestorationValue();
    }
    @Override
    public void addRestorationValue() { super.addProperty(InteractableProperties.CONSUMABLE_REST_NAME.name(), new Variable(20)); }

}
