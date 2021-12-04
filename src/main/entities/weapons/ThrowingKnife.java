package entities.weapons;

import entities.InteractableProperties;
import entities.Variable;
import entities.interfaces.Throwable;

/*
A throwing knife that has high hit prob and low damage.
 */
public class ThrowingKnife extends Weapon implements Throwable {


    public ThrowingKnife(String id) {
        super(id, "throw: thrown_obj=knife1, target=enemy1");
        addHitProbability();
        addWeight();
    }

    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(95));
    }

    @Override
    public void addWeight() {
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(12));
    }

}
