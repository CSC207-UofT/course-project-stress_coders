package entities;

import entities.interfaces.CanChop;
import entities.interfaces.Throwable;

/**
 A throwable weapon
 must add the required properties of a throwable object
 @see Throwable
 for details on enforcement

 **/
public class Spear extends Weapon implements Throwable {
    /**
     constructs a new Spear object.
     adds enforced variables to interactable properties map, if this is not done accessing them
    in the command will throw a propertyNotAddedException
    **/
    public Spear(String id) {
        super(id, "throw: thrown_obj=spear1, target=enemy1");

        addHitProbability();
        addWeight();
    }

    /**
     * @see Throwable
     * for details for instance varaibles
     **/
    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(75));
    }

    @Override
    public void addWeight() {
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(30));
    }

}

