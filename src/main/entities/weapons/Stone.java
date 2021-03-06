package entities.weapons;

import entities.InteractableProperties;
import entities.Variable;
import entities.interfaces.CanChop;
import entities.interfaces.Throwable;
import entities.weapons.Weapon;

/*
A rock that serves as a basic tool that can be found very commonly. High hitProb, low weight and chopDamage
 */
public class Stone extends Weapon implements Throwable, CanChop {

    public Stone(String id) {
        super(id, "throw: thrown_obj=rock1, target=enemy1");
        addHitProbability();
        addWeight();
    }

    public Stone(){}

    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(100));
    }

    @Override
    public void addWeight() {
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(1));
    }

    @Override
    public void addChopDamage() {
        super.addProperty(InteractableProperties.CHOP_DMG_NAME.name(), new Variable(4));
    }
}
