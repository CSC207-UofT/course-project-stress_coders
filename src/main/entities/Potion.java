package entities;

/*
A default potion class, doesn't actually do anything as opposed to its specialized subclasses
 */

import java.lang.Math;
import entities.interfaces.Consumable;
import entities.interfaces.Throwable;

public class Potion extends Item implements Throwable, Consumable {

    double VALUE_COEFFICIENT = 1.5;

    public double value;
    public String id;
    public int strength;
    private Player player;

    public Potion(String id, int strength) {
        super(id, "First call useInventory then [consumable_name]: quantity");
        this.strength = strength;
        this.value = strength * VALUE_COEFFICIENT;
    }


    public Potion(String id) {
        super(id, "First call useInventory then [consumable_name]: quantity");
    }


    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(100));
    }
    @Override
    public void addWeight() {
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(0));
    }


    public int restorationValue() {
        int v = (int) Math.round(this.value);
        return v;
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
            p.subConsumable(this);
            p.setHealthPoints(p.getHealthPoints() + this.getProperty(InteractableProperties.CONSUMABLE_REST_NAME.name()).getInteger());
            return "You consumed 1" + this.getId();
        } else {
            return "You don't hold this!";
        }
    }
}
