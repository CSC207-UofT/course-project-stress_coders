package entities;

import entities.interfaces.CanChop;
import entities.interfaces.Throwable;

/*
A rock that serves as a basic tool that can be found very commonly. High hitProb, low weight and chopDamage
 */
public class Rock extends Weapon implements Throwable, CanChop {

    public Rock(String id) {
        super(id, "throw: thrown_obj=rock1, target=enemy1");
        addHitProbability();
        addWeight();
    }

    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(100));
    }

    @Override
    public void addWeight() {
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(1));
    }

    @Override
    public void addChopDamage() { super.addProperty(InteractableProperties.CHOP_DMG_NAME.name(), new Variable(4)); };
}
