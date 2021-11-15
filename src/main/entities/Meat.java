package entities;

/**
A meat class to consume

Meat object should not be created on it's own, Animal classes create a meat object
 **/

import entities.interfaces.Consumable;


public class Meat extends Food implements Consumable {

    private String id;

    /**
     * Construct the Meat
     *
     * @param id the appropriate ID for Meat
     */
    public Meat(String id) {
        super(id);
    }

    public String getID() {
        return this.id;
    }


    @Override
    public void addRestorationValue() { super.addProperty(InteractableProperties.CONSUMABLE_REST_NAME.name(), new Variable(20)); }

}
