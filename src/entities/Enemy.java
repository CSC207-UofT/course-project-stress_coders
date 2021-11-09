package entities;

import entities.interfaces.ThrowableTarget;

public class Enemy extends Character implements ThrowableTarget {
    public Enemy(String id) {
        super(id);
    }

    @Override
    public String handleHit(Interactable throwable) {
        int weight = throwable.getProperty(InteractableProperties.WEIGHT.name()).getInteger();
        setHealthPoints(getHealthPoints() - weight);

        return "Your axe hits " + getId() + " for " + weight + " damage!";
    }
}
