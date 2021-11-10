package entities;

import entities.interfaces.ThrowableTarget;

/**
Interactable that can damage the player

 completed when killed
 **/
public class Enemy extends Character implements ThrowableTarget {
    public Enemy(String id) {
        super(id);
    }

    /**
    @see ThrowableTarget for details
     Damage the enemy by the weight of the object

     return "your axe hits {enemy name} for {weight} damage"
     **/
    @Override
    public String handleHit(Interactable throwable) {
        int weight = throwable.getProperty(InteractableProperties.WEIGHT.name()).getInteger();
        setHealthPoints(getHealthPoints() - weight);

        return "Your axe hits " + getId() + " for " + weight + " damage!";
    }
}
