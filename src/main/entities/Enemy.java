package entities;

import entities.interfaces.ThrowableTarget;
import java.util.Random;

public class Enemy extends Character implements ThrowableTarget {

    private int damage;

    public Enemy(String id) {
        super(id);
        Random r = new Random();
        this.damage = r.nextInt(50)+1;
        int hp = r.nextInt(100)+1;
        super.setHealthPoints(hp);
    }

    public Enemy(String id, int damage) {
        super(id);
        Random r = new Random();
        this.damage = damage;
        int hp = r.nextInt(100)+1;
        super.setHealthPoints(hp);
    }

    public Enemy(String id, int damage, int health) {
        super(id);
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
            return "Your " + throwable.getId() + "hits " + getId() + " for " + weight + " damage! They still have some " +
                    "power left and fight back!" ;
        }
    }

    public boolean isDead() {return super.getHealthPoints() == 0;}

    public String strikeBack() {
        Random r = new Random();
        int hit = r.nextInt(2);
        if (hit == 0) {
            return "Nice! " + getId() + " missed their hit.. time to strike!";
        }
        else {
            String dmg = ((Integer) this.damage).toString();
            return "Ouch! " + getId() + " hit you for " + dmg + " hp, be careful!";
        }

    }

}
