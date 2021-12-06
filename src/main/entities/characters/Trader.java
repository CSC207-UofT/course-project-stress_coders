package entities.characters;

import entities.Interactable;
import entities.InteractableProperties;
import entities.Item;
import entities.characters.Player;
import entities.interfaces.CanTradeWith;
import entities.interfaces.Consumable;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This is a gameobject that allows the user to trade currency for consumables (like an apple or potion)
 */
public class Trader extends Item implements CanTradeWith {
    private final HashMap<String, Consumable> inventory = new HashMap<>();
    Player player;
    public Trader(String id, Player p) {
        super(id, "Trade with this to gain consumables.E.G. trade: trader=[some trader], item=[some consumable]");
        this.player = p;
    }

    @Override
    public String trade(){
        Scanner input = new Scanner(System.in);
        System.out.print("What would you like to buy: ");
        String item = input.nextLine();
        if (this.inventory.containsKey(item)) {
            int price = Math.abs(((Interactable) this.inventory.get(item)).getProperty(InteractableProperties.CONSUMABLE_REST_NAME.name()).getInteger());
            if (price <= this.player.getWallet()) {
                this.player.addConsumable((Item) this.inventory.get(item));
                this.player.subCurrency(price);
                this.setCompleted(true);
                return "Bought " + item + " for " + price + " geld";
            } else {
                this.setCompleted(true);
                return "Cannot afford that";
            }
        } else {
            this.setCompleted(true);
            return "I don't sell that in my store, sorry";
        }
    }

    // Populate the trader with consumables
    public void addConsumablesToStore(Map<String, Consumable> itemsToAdd){
        for (String key : itemsToAdd.keySet()) {
            this.inventory.put(key, itemsToAdd.get(key));
        }
    }

    public HashMap<String, Consumable> getInventory() {
        return this.inventory;
    }


    public Trader(){}

    public void setPlayer(Player p) {
        this.player = p;
    }
}
