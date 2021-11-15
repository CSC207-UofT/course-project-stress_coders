package entities;

import entities.interfaces.CanTradeWith;
import entities.interfaces.Consumable;

import java.util.HashMap;

/**
 * This is a gameobject that allows the user to trade currency for consumables (like an apple or potion)
 */
public class Trader extends Item implements CanTradeWith {
    private final HashMap<String, Consumable> inventory = new HashMap<>();
    public Trader(String id) {
        super(id, "Trade with this to gain consumables");
    }

    @Override
    public String trade(Player p, String item){
        int price = Math.abs(((Interactable) this.inventory.get(item)).getProperty(InteractableProperties.CONSUMABLE_REST_NAME.name()).getInteger());
        if (price <= p.getWallet()) {
            p.addConsumable(this.inventory.get(item));
            p.subCurrency(price);
            return "Bought " + item + " for " + price + " geld";
        } else { return "Cannot afford that"; }
    }

    // Populate the trader with consumables
    public void addConsumablesToStore(HashMap<String, Consumable> itemsToAdd){
        for (String key : itemsToAdd.keySet()) {
            this.inventory.put(key, itemsToAdd.get(key));
        }
    }
}
