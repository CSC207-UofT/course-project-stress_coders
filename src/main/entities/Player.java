package entities;

import entities.Character;
import entities.interfaces.ThrowableTarget;

import java.util.HashMap;
import java.util.List;

/*
The player character hold their inventory and their stats and handle how those change
 */
public class Player extends Character implements ThrowableTarget {
    private HashMap<String, List<String>> inventory = new HashMap<String, List<String>>();

    public Player(String id) {
        super(id);
    }

    @Override
    public String handleHit(Interactable throwable) {
        int damageDone = throwable.getProperty(InteractableProperties.WEIGHT.name()).getInteger();
        this.setHealthPoints(this.getHealthPoints() - damageDone);
        if (this.isDead()) {
            return "You died";
        } else {
            return "Your damage has decreased by " + damageDone + ", you have " + this.getHealthPoints() + " health points";
        }
    }

    public void addInventory(String name, List<String> resources){
        this.inventory.put(name, resources);
    }
}
