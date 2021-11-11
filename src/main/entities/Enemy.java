package entities;

import entities.interfaces.Throwable;
import entities.interfaces.ThrowableTarget;
import usecases.Command;
import usecases.CommandConstants;

import java.util.HashMap;
import java.util.Random;

/**
Interactable that can damage the player

 completed when killed
 **/
public class Enemy extends Character implements ThrowableTarget {

    private Player player;

    public Enemy(String id, Player player) {
        super(id);
        this.player = player;
        Random r = new Random();
        int hp = r.nextInt(100)+1;
        super.setHealthPoints(hp);
    }

    public Enemy(String id, int health, Player player) {
        super(id);
        this.player = player;
        super.setHealthPoints(health);
    }

     /**
    @see ThrowableTarget for details
     Damage the enemy by the weight of the object

     return "your axe hits {enemy name} for {weight} damage"
     **/
    @Override
    public String handleHit(Interactable throwable) {
        int weight = throwable.getProperty(InteractableProperties.WEIGHT.name()).getInteger();
        setHealthPoints(getHealthPoints() - weight);
        if (isDead()) {
            return "Your "+ throwable.getId() + " hits " + getId() + " for " + weight + " damage! You killed the beast!";
        }
        else {
            String landedHitString="Your "+ throwable.getId() + " hits " + getId() + " for " + weight + " damage!\n";
            return landedHitString+hitBack((Throwable) throwable);
        }
    }



    public String hitBack(Throwable throwable) {
        Random r = new Random();
        int hit = r.nextInt(2);
        if (hit == -1) {
            return "Nice! " + getId() + " missed their hit.. time to strike!";
        }
        else {
            //Basic response is to throw back what was thrown. This can be overridden with different (special) enemies
            String throwingBack = getId()+" is throwing it back!\n";
            ((Item) throwable).setHeldBy(this);
            Command throwCommand = CommandConstants.COMMANDS.get("throw");
            HashMap<String, Interactable> enemyThrowArgs = new HashMap<>();
            enemyThrowArgs.put("obj", (Interactable) throwable);  // Throwing the axe
            enemyThrowArgs.put("target", this.player);  // Throwing it at the player
            return throwingBack+throwCommand.execute(enemyThrowArgs);
        }
    }

}

/*
Interactions


 */
