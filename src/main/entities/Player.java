package entities;

import entities.Character;
import entities.interfaces.ThrowableTarget;

import java.util.HashMap;
import java.util.List;

/*
The player character hold their inventory and their stats and handle how those change
 */
public class Player extends Character implements ThrowableTarget {
    private HashMap<String, Integer> inventory = new HashMap<String, Integer>();
    private int wallet;

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

    /***
     * Modify to the inventory
     * @param name
     * @param quantity
     */
    public void addInventory(String name, int quantity){
        if (!this.inventory.containsKey(name)) {
            this.inventory.put(name, quantity);
        } else {
            int curr = this.inventory.get(name);
            this.inventory.put(name, curr + quantity);
        }
    }

    public void subInventory(String name, int quantity){
        if (this.inventory.containsKey(name)) {
            int curr = this.inventory.get(name);
            this.inventory.put(name, curr - quantity);
        }
    }

    /**
     * modify the wallet
     * @param quantity
     */
    public void addCurrency(int quantity){ this.wallet = this.wallet + quantity;}

    public void subCurrency(int quantity){ this.wallet = this.wallet - quantity;}
}
