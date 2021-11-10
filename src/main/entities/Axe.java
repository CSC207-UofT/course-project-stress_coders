package entities;

import entities.interfaces.Throwable;

/**
A throwable weapon
must add the required properties of a throwable object
 @see Throwable
 for details on enforcement

Handle unique axe interactions. I.e. chopping a tree.

 auto completed
 **/
public class Axe extends Weapon implements Throwable {

    public Axe(String id) {
        super(id);
        /*
         adding enforced variables to interactable properties map, if this is not done accessing them
         in the command will throw a propertyNotAddedException
         */
        addHitProbability();
        addWeight();
    }

    /**
     @see Throwable
     for details isntance varaibles
     **/
    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(50));
    }

    @Override
    public void addWeight() {
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(20));
    }
}