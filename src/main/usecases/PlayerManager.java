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
}
