package entities;

import entities.Interactable;

public abstract class Character extends Interactable {
    private int healthPoints;

    public Character(String id) {
        super(id);
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints(){
        return this.healthPoints;
    }
}
