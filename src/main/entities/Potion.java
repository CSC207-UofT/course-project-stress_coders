package entities;

/*
A default potion class, doesn't actually do anything as opposed to its specialized subclasses
 */

import entities.interfaces.Consumable;
import entities.interfaces.Throwable;

public class Potion extends Item implements Throwable, Consumable {

    public String id;
    public int strength;

    public Potion(String id, int strength) {
        super(id, "First call useInventory then [consumable_name]: quantity");
        this.strength = strength;
        this.addRestorationValue();
        addHitProbability();
        addWeight();
    }


    public Potion(String id) {
        super(id, "First call useInventory then [consumable_name]: quantity");
        addRestorationValue();
        addHitProbability();
        addWeight();
    }


    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(100));
    }
    @Override
    public void addWeight() {
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(0));
    }

    public String getID() {
        return this.id;
    }

    public int restorationValue() {
        return this.strength;
    }


    @Override
    public void addRestorationValue() {
        super.addProperty(InteractableProperties.CONSUMABLE_REST_NAME.name(), new Variable(this.strength));
    }
    @Override
    public String consume() {
        Player p = (Player) this.getHeldBy();
        if (p != null) {
            p.subConsumable(this, 1);
            p.setHealthPoints(p.getHealthPoints() + this.getProperty(InteractableProperties.CONSUMABLE_REST_NAME.name()).getInteger());
            return "You consumed 1" + this.getId();
        } else {
            return "You don't hold this!";
        }
    }
}
