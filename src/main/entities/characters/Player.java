package entities.characters;

import entities.*;
import entities.food.UnusablePotion;
import entities.interfaces.Consumable;
import entities.interfaces.ThrowableTarget;
import entities.weapons.Axe;
import entities.weapons.Weapon;

import java.util.*;

/*
The player character hold their inventory and their stats and handle how those change
 */
public class Player extends Character implements ThrowableTarget {
    private  HashMap<String, Integer> inventory = new HashMap<>();
    private  HashMap<Item, Integer> items = new HashMap<>();
    private int wallet;
    private Weapon currentWeapon;
    /**
     * Construct a Player
     *
     * @param id the appropriate id for the player
     */
    public Player(String id) {
        super(id);
        Axe a = new Axe("Molag Bal");
        setWeapon(a);
    }

    /**
     * Get the player's Consumables
     *
     * @return list of consumables the player has
     */
    public List<Item> getConsumables() {
        return new ArrayList<>(this.items.keySet());
    }

    /**
     * Find and Return a specific consumable
     *
     * @param input the consumable to find
     * @return the consumable if found, and an UnusablePotion otherwise
     */
    public Interactable findConsumable(String input) {
        for (Item c: items.keySet()) {
            if (c.getId().equals(input)) {
                return c;
            }
        }
        return new UnusablePotion();
    }

    /**
     * Set the player's current weapon
     *
     * @param w the player's new weapon
     */
    public void setWeapon(Weapon w) {
        this.currentWeapon = w;
    }

    /**
     * Get the player's current weapon
     *
     * @return the player's current weapon
     */
    public Weapon getCurrentWeapon() {return this.currentWeapon;}

    /**
     * Get the amount of currency the player has
     *
     * @return amount of currency in wallet
     */
    public int getWallet() {return this.wallet;}

    /**
     * Handle a hit against the player
     *
     * @param throwable the thrown object
     * @return what occurred when the player was hit
     */
    @Override
    public String handleHit(Interactable throwable) {
        int damageDone = throwable.getProperty(InteractableProperties.WEIGHT.name()).getInteger();
        this.setHealthPoints(this.getHealthPoints() - damageDone);
        if (this.isDead()) {
            System.out.println("You died");
            return "You died";
        } else {
            System.out.println("Your health has decreased by " + damageDone + ", you have " + this.getHealthPoints() + " health points");
            return "Your health has decreased by " + damageDone + ", you have " + this.getHealthPoints() + " health points";
        }
    }

    /***
     * Modify the inventory
     * @param name name of object to be added/removed
     * @param quantity quantity of the object to be added/removed
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

    public void addConsumable(Item item) {
        int i;
        if (this.items.get(item) != null) {
            i = this.items.get(item);
        }
        else {
            i = 0;
        }
        this.items.put(item, i+1);
        item.setHeldBy(this);
    }

    public void subConsumable(Item item, int quantity) {
        item.setHeldBy(null);
        this.items.put(item, this.items.get(item)-quantity);
    }

    public void subConsumable(Item item, int quantity, boolean dontChangeHeldBy) {
        if (!dontChangeHeldBy) { item.setHeldBy(null); }
        this.items.put(item, this.items.get(item)-quantity);
    }

    public int inventoryAmount(String name) {return this.inventory.get(name);}
    public int itemAmount(Consumable name) {return this.items.get(name);}

    /**
     * modify the wallet
     * @param quantity the quantity of money to add to the wallet
     */
    public void addCurrency(int quantity){ this.wallet = this.wallet + quantity;}

    public void subCurrency(int quantity){ this.wallet = this.wallet - quantity;}

    public HashMap<Item, Integer> getItems(){return this.items;}

    public Player(){}

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public void setInventory(HashMap<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public void setItems(HashMap<Item, Integer> items) {
        this.items = items;
    }
}
