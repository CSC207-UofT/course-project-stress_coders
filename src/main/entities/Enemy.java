package entities;

import entities.interfaces.ThrowableTarget;
import java.util.Random;

public class Enemy extends Character implements ThrowableTarget {

    private int damage;
    private Player player;

    public Enemy(String id, Player player) {
        super(id);
        this.player = player;
        Random r = new Random();
        this.damage = r.nextInt(50)+1;
        int hp = r.nextInt(100)+1;
        super.setHealthPoints(hp);
    }

    public Enemy(String id, int damage, Player player) {
        super(id);
        Random r = new Random();
        this.player = player;
        this.damage = damage;
        int hp = r.nextInt(100)+1;
        super.setHealthPoints(hp);
    }

    public Enemy(String id, int damage, int health, Player player) {
        super(id);
        this.player = player;
        this.damage = damage;
        super.setHealthPoints(health);
    }
    @Override
    public String handleHit(Interactable throwable) {
        int weight = throwable.getProperty(InteractableProperties.WEIGHT.name()).getInteger();
        setHealthPoints(getHealthPoints() - weight);
        if (isDead()) {
            return "Your "+ throwable.getId() + "hits " + getId() + " for " + weight + " damage! You killed the beast!";
        }
        else {
            String s = strikeBack();
            if (player.isDead()) {
                s = "Oof! " + getId() + " hit a bit blow.. you died!";
            }
            return "Your " + throwable.getId() + "hits " + getId() + " for " + weight + " damage! They still have some " +
                    "power left and fight back! " + s;
        }
    }



    public String strikeBack() {
        Random r = new Random();
        int hit = r.nextInt(2);
        if (hit == 0) {
            return "Nice! " + getId() + " missed their hit.. time to strike!";
        }
        else {
            String dmg = ((Integer) this.damage).toString();
            this.player.setHealthPoints(this.player.getHealthPoints() - this.damage);
            return "Ouch! " + getId() + " hit you for " + dmg + " hp, be careful!";
        }

    }

}
