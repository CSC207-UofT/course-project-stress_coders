package entities;

import entities.Character;
import entities.interfaces.Consumable;
import entities.interfaces.ThrowableTarget;

import java.util.*;

/*
The player character hold their inventory and their stats and handle how those change
 */
public class Player extends Character implements ThrowableTarget {
    private HashMap<String, Integer> inventory = new HashMap<String, Integer>();
    private Set<Consumable> items = new HashSet<>();
    private int wallet;
    private Weapon currentWeapon;

    public Player(String id) {
        super(id);
        Axe a = new Axe("Molag Bal");
        setWeapon(a);
    }

    public void setWeapon(Weapon w) {
        this.currentWeapon = w;
    }

    public Weapon getCurrentWeapon() {return this.currentWeapon;}

    public int getWallet() {return this.wallet;}

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

    public void addConsumables(Consumable item, int quantity) {
        this.items.add(item);
        addInventory(item.getID(), quantity);
    }

    public int inventoryAmount(String name) {return this.inventory.get(name);}

    /**
     * modify the wallet
     * @param quantity
     */
    public void addCurrency(int quantity){ this.wallet = this.wallet + quantity;}

    public void subCurrency(int quantity){ this.wallet = this.wallet - quantity;}

    public List<String> listConsumables() {
        List<String> items = new ArrayList<>();
        for (Consumable item: this.items) {
            Integer amount = this.inventoryAmount(item.getID());
            items.add(item.getID() + " : " + amount.toString());
        }
        return items;
    }
}
