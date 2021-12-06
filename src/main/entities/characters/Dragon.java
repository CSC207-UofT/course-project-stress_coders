package entities.characters;

import entities.Interactable;
import entities.InteractableProperties;
import entities.interfaces.Throwable;

import java.util.Random;

public class Dragon extends Enemy {
    private final double skin_piercing_threshold;

    public Dragon(String id, int health, Player player, int valueDefeated) {
        super(id, health, player, valueDefeated);
//        this.valueDefeated = valueDefeated;
        this.skin_piercing_threshold = health*0.6;
    }

    public Dragon(String id, Player player, int valueDefeated) {
        super(id, player, valueDefeated);
        Random r = new Random();
        int hp = r.nextInt(11) + 59;
//        this.valueDefeated = valueDefeated;
        this.skin_piercing_threshold = hp*0.6;
    }

    public Dragon(String id, Player player, int valueDefeated, String howTo) {
        super(id, player, valueDefeated, howTo);
        Random r = new Random();
        int hp = r.nextInt(71) + 24;
//        this.valueDefeated = valueDefeated;
        this.skin_piercing_threshold = hp*0.6;
    }

    private double getResistanceMultiplier() {
        if (this.getHealthPoints() < this.skin_piercing_threshold) {
            return 1;
        } else {
            return 0.5;
        }
    }

    private String armorStatus() {
        if (this.getHealthPoints() < this.skin_piercing_threshold) {
            return "The beast has thick skin, you deal reduced damage until you damage it enough ! \n";
        } else {
            return "";
        }
    }

    @Override
    public String handleHit(Interactable weapon) {
        int weight = (int) (weapon.getProperty(InteractableProperties.WEIGHT.name()).getInteger()*getResistanceMultiplier());
        setHealthPoints(getHealthPoints() - weight);
        if (isDead()) {
            this.setCompleted(true);
            player.addCurrency(valueDefeated);
            return "Your "+ weapon.getId() + " hits " + getId() + " for " + weight + " damage! You killed the beast!";
        } else {
            String landedHitString="Your "+ weapon.getId() + " hits " + getId() + " for " + weight + " damage!\n" + armorStatus();
            if (weapon instanceof Throwable) {
                return landedHitString+super.hitBack((Throwable) weapon);
            }
            System.out.println(landedHitString);
            return super.defaultHitBack();
        }
    }

    @Override
    public void setDefaultDamage() {
        Random r = new Random();
        this.defaultDamage = r.nextInt(14) + 29;
    }

    public Dragon(){
//        valueDefeated = 0;
        skin_piercing_threshold = 69;
    }

}
