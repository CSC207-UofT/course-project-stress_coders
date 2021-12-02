package entities;

import entities.interfaces.Throwable;
import entities.interfaces.ThrowableTarget;
import java.util.Random;

/**
Interactable that can damage the player

 completed when killed
 **/
public class Enemy extends Character implements ThrowableTarget {

    public final Player player;
    public final int valueDefeated;
    public int defaultDamage;

    /**
     * Construct an Enemy
     *
     * @param id the Enemy id
     * @param player the Player that the Enemy is interacting with
     * @param valueDefeated the amount of currency you get by defeating the Enemy
     * We have multiple constructors that allow flexibility in subclasses for what they wish to specify.
     */
    public Enemy(String id, Player player, int valueDefeated) {
        super(id);
        this.player = player;
        Random r = new Random();
        int hp = r.nextInt(100)+1;
        super.setHealthPoints(hp);
        this.valueDefeated = valueDefeated;
        setDefaultDamage();
    }

    public Enemy(String id, Player player, int valueDefeated, String howTo) {
        super(id, howTo);
        this.player = player;
        Random r = new Random();
        int hp = r.nextInt(100)+1;
        super.setHealthPoints(hp);
        this.valueDefeated = valueDefeated;
        setDefaultDamage();
    }

    /**
     * Construct an Enemy
     *
     * @param id the Enemy id
     * @param health the amount of health the Enemy has
     * @param player the Player that the Enemy is interacting with
     * @param valueDefeated the amount of currency you get by defeating the Enemy
     */
    public Enemy(String id, int health, Player player, int valueDefeated) {
        super(id);
        this.player = player;
        super.setHealthPoints(health);
        this.valueDefeated = valueDefeated;
        setDefaultDamage();
    }

    public void setDefaultDamage() {
        Random r = new Random();
        this.defaultDamage = r.nextInt(75) + 24;
    }
     /**
    @see ThrowableTarget for details
     Damage the enemy by the weight of the object

     @return "your axe hits {enemy name} for {weight} damage"
     **/
    @Override
    public String handleHit(Interactable weapon) {
        int weight = weapon.getProperty(InteractableProperties.WEIGHT.name()).getInteger();
        setHealthPoints(getHealthPoints() - weight);
        if (isDead()) {
            this.setCompleted(true);
            player.addCurrency(valueDefeated); // Hard coded for now, should add rewards for each monster
            return "Your "+ weapon.getId() + " hits " + getId() + " for " + weight + " damage! You killed the beast!";
        }
        else {
            String landedHitString="Your "+ weapon.getId() + " hits " + getId() + " for " + weight + " damage!\n";
            if (weapon instanceof Throwable) {
                return landedHitString+hitBack((Throwable) weapon);
            }
            System.out.println(landedHitString);
            return defaultHitBack();
        }
    }

    
    public String defaultHitBack() {
        Random r = new Random();
        int hit = r.nextInt(2);
        if (hit == 0) {
            return "Nice! " + getId() + " missed their hit.. time to strike!";
        }
        else {
            String throwingBack = getId()+" hits you back for " +
                    this.defaultDamage +" !\n";
            getPlayer().setHealthPoints(getPlayer().getHealthPoints() -
                    defaultDamage);
            return throwingBack;
        }
    }


    /**
     * Throw the throwable weapon back at the character if they missed
     * and return a string of what happened
     *
     * @param throwable the throwable weapon the Enemy may throwing back
     * @return a string with the result
     **/
    public String hitBack(Throwable throwable) {
        Random r = new Random();
        int hit = r.nextInt(2);
        if (hit == 0) {
            return "Nice! " + getId() + " missed their hit.. time to strike!";
        }
        else {
            //Basic response is to throw back what was thrown. This can be overridden with different (special) enemies
            String throwingBack = getId()+" hits you back for " +
                    ((Interactable) throwable).getProperty(InteractableProperties.WEIGHT.name()).getInteger() +" !";
            getPlayer().setHealthPoints(getPlayer().getHealthPoints() -
                    ((Interactable) throwable).getProperty(InteractableProperties.WEIGHT.name()).getInteger());
            return throwingBack;
        }
    }

    /**
     * Get the player that the Enemy is facing
     *
     * @return the Player object the Enemy is facing
     */
    public Player getPlayer(){ return this.player; }

}