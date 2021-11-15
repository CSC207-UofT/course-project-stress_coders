package entities;

import entities.interfaces.CanChop;
import entities.interfaces.Throwable;

/**
A throwable weapon
must add the required properties of a throwable object
 @see Throwable
 for details on enforcement

Handle unique axe interactions. I.e. chopping a tree.

 auto completed
 **/
public class Axe extends Weapon implements Throwable, CanChop {


    public Axe(String id) {
        super(id, "to throw use throw: throw_obj:[weapon_id], target=[enemy_id] and to chop use " +
                "chop: tool=[weapon_id], target=[resource_id]");
        /*
         adding enforced variables to interactable properties map, if this is not done accessing them
         in the command will throw a propertyNotAddedException
         */
        addHitProbability();
        addWeight();
        addChopDamage();
    }

    /**
     @see Throwable
     @see CanChop
     for details isntance varaibles
     **/
    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(75));
    }

    @Override
    public void addWeight() {
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(20));
    }

    @Override
    public void addChopDamage() {
        super.addProperty(InteractableProperties.CHOP_DMG_NAME.name(), new Variable(20));
    }
}
