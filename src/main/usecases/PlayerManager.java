package usecases;

import entities.Interactable;
import entities.Item;
import entities.characters.Player;

import java.util.List;

public class PlayerManager {
    final Player player;
    public PlayerManager(String p, String difficulty) {
        player = new Player(p);
        if (difficulty.equals("hard")) {player.setHealthPoints(300);}
        else if (difficulty.equals("medium")) {player.setHealthPoints(200);}
        else {player.setHealthPoints(300);}


    }

    public PlayerManager(Player p){
        this.player = p;
    }

    public Player getPlayer() {return this.player;}

    public List<Item> getAllConsumables() {
        return player.getConsumables();
    }

    public Interactable findConsumable(String userInput) {
        return player.findConsumable(userInput);
    }

    public String getPlayerInfo(){
        String playerInfo = "Player Info: ";
        playerInfo += "Name: " + player.getId() + ", ";
        playerInfo += "Health: " + player.getHealthPoints() + ", ";
        playerInfo += "Wallet: " + player.getWallet() + ", ";
        playerInfo += "Weapon: " + player.getCurrentWeapon().getId() + ", ";
        playerInfo += "Items: " + player.getItems() + ", ";
        return playerInfo;
    }
}
