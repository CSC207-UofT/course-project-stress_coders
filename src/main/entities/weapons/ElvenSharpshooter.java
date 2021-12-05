package entities.weapons;

import entities.characters.Enemy;
import entities.characters.Player;

import java.util.Random;

public class ElvenSharpshooter extends Enemy {
    //an enemy that cannot miss

    public ElvenSharpshooter(String id, Player player, int valueDefeated) {
        super(id, player, valueDefeated);
    }

    public ElvenSharpshooter(String id, Player player, int valueDefeated, String howTo) {
        super(id, player, valueDefeated, howTo);
    }

    public ElvenSharpshooter(String id, int health, Player player, int valueDefeated) {
        super(id, health, player, valueDefeated);
    }

    public void setDefaultDamage() {
        Random r = new Random();
        this.defaultDamage = r.nextInt(11) + 29;
    }

    public String defaultHitBack() {
        String throwingBack = getId()+" hits you back for " + this.defaultDamage +" !\n";
        getPlayer().setHealthPoints(getPlayer().getHealthPoints() - defaultDamage);
        return throwingBack;
    }

    public ElvenSharpshooter(){}
}


