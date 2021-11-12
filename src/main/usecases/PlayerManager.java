package usecases;

import entities.Interactable;
import entities.Player;
import entities.interfaces.Consumable;

import java.util.List;

public class PlayerManager {
    Player player;
    public PlayerManager(String p) {
        player = new Player(p);
    }

    public Player getPlayer() {return this.player;}

    public String use(Consumable item, int quantity) {
        if (player.inventoryAmount(item.getID()) >= quantity) {
            List<String> allConsumables = player.listConsumables();
            if (!(allConsumables.contains(item.getID()))) {
                return "Your chosen item is not consumable, please select another one";
            }
            player.subInventory(item.getID(), quantity);
            for (int i=0;i <= quantity; i++) {
                player.setHealthPoints(player.getHealthPoints() + item.restorationValue());
            }
            return "Item(s) successfully used.";
        }
        return "You don't have enough " + item.getID() + " to use.";
    }

    public void listInventory() {
        List<String> allConsumables = player.listConsumables();
        for (String s: allConsumables) {
            System.out.println(s);
        }
    }
}
