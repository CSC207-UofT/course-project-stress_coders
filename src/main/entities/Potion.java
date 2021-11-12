package entities;

/*
A default potion class, doesn't actually do anything as opposed to its specialized subclasses
 */

import entities.interfaces.Throwable;

public class Potion extends Item implements Throwable {

    double VALUE_COEFFICIENT = 1.5;

    public double value;
    public String id;
    public int strength;

    public Potion(String id, int strength) {
        super(id, "First call useInventory then [consumable_name]: quantity");
        this.strength = strength;
        this.value = strength * VALUE_COEFFICIENT;
    }

    public Potion(String id, int strength, double value) {
        super(id, "First call useInventory then [consumable_name]: quantity");
        this.strength = strength;
        this.value = value;
    }

    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(100));
    }
    @Override
    public void addWeight() {
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(0));
    }
}
