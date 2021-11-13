package entities;

/**
A meat class to consume

Meat object should not be created on it's own, Animal classes create a meat object
 **/

import entities.interfaces.Consumable;


public class Meat extends Item implements Consumable {

    private String id;

    public Meat(String id) {
        super(id, "First call useInventory then [consumable_name]: quantity");
    }

    public String getID() {
        return this.id;
    }


    @Override
    public void addRestorationValue() { super.addProperty(InteractableProperties.CONSUMABLE_REST_NAME.name(), new Variable(15)); }

    @Override
    public String consume() {
        Player p = (Player) this.getHeldBy();
        if (p != null) {
            p.subConsumable(this, 1);
            p.setHealthPoints(p.getHealthPoints() + this.getProperty(InteractableProperties.CONSUMABLE_REST_NAME.name()).getInteger());
            return "You consumed 1" + this.getId();
        } else {
            return "You don't have this!";
        }
    }
}
