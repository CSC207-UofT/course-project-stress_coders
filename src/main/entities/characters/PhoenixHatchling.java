package entities.characters;

import entities.Interactable;
import entities.InteractableProperties;
import entities.characters.Enemy;
import entities.characters.Player;
import entities.interfaces.Throwable;

import java.util.Random;

public class PhoenixHatchling extends Enemy {
    //An enemy type that is healed to full hp once upon "death"
    private int lives_remaining = 1;
    private final int max_hit_points;

    public PhoenixHatchling(String id, int health, Player player, int valueDefeated) {
        super(id, health, player, valueDefeated);
//        this.valueDefeated = valueDefeated;
        this.player = player;
        this.max_hit_points = health;
    }

    public PhoenixHatchling(String id, Player player, int valueDefeated) {
        super(id, player, valueDefeated);
        Random r = new Random();
        int hp = r.nextInt(31)+19;
//        this.valueDefeated = valueDefeated;
        this.player = player;
        this.max_hit_points = hp;
    }

    public PhoenixHatchling(String id, Player player, int valueDefeated, String howTo) {
        super(id, player, valueDefeated, howTo);
        Random r = new Random();
        int hp = r.nextInt(71)+24;
//        this.valueDefeated = valueDefeated;
        this.player = player;
        this.max_hit_points = hp;
    }

    private void revive(){
        this.setHealthPoints(this.max_hit_points);
        this.lives_remaining -= 1;
    }

    @Override
    public String handleHit(Interactable weapon) {
        int weight = weapon.getProperty(InteractableProperties.WEIGHT.name()).getInteger();
        setHealthPoints(getHealthPoints() - weight);
        if (isDead() & this.lives_remaining == 0) {
            this.setCompleted(true);
            player.addCurrency(valueDefeated); // Hard coded for now, should add rewards for each monster
            return "Your "+ weapon.getId() + " hits " + getId() + " for " + weight + " damage! You killed the beast!";
        } else if (isDead() & this.lives_remaining > 0) {
            this.revive();
            return ("Your "+ weapon.getId() + " hits " + getId() + " for " + weight + " damage! You killed the beast! " +
                    "\n \n Wait... \n The Phoenix hatchling is reborn from the flames, the fight continues!");
        } else {
            String landedHitString="Your "+ weapon.getId() + " hits " + getId() + " for " + weight + " damage!\n";
            if (weapon instanceof Throwable) {
                return landedHitString+super.hitBack((Throwable) weapon);
            }
            System.out.println(landedHitString);
            return super.defaultHitBack();
        }
    }

    public PhoenixHatchling(){
//        valueDefeated = 0;
        player = new Player();
        max_hit_points = 69;
    }
}
