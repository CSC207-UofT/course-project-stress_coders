package entities;

import entities.interfaces.Throwable;

public class Axe extends Weapon implements Throwable {

    public Axe(String id) {
        super(id);
        addHitProbability();
        addWeight();
    }

    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(50));
    }

    @Override
    public void addWeight() {
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(20));
    }
}
