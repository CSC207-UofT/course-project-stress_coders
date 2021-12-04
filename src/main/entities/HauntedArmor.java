package entities;

import entities.characters.Enemy;
import entities.characters.Player;
import entities.interfaces.Throwable;

import java.util.Random;

public class HauntedArmor extends Enemy {
    //An enemy that takes reduced damage until his thick skin is damaged enough (at 60% health)
    private final int valueDefeated;
    private final Player player;
    private final double skin_piercing_threshold;

    public HauntedArmor(String id, int health, Player player, int valueDefeated) {
        super(id, health, player, valueDefeated);
        this.valueDefeated = valueDefeated;
        this.player = player;
        this.skin_piercing_threshold = health*0.6;
    }

    public HauntedArmor(String id, Player player, int valueDefeated) {
        super(id, player, valueDefeated);
        Random r = new Random();
        int hp = r.nextInt(11) + 59;
        this.valueDefeated = valueDefeated;
        this.player = player;
        this.skin_piercing_threshold = hp*0.6;
    }

    public HauntedArmor(String id, Player player, int valueDefeated, String howTo) {
        super(id, player, valueDefeated, howTo);
        Random r = new Random();
        int hp = r.nextInt(71) + 24;
        this.valueDefeated = valueDefeated;
        this.player = player;
        this.skin_piercing_threshold = hp*0.6;
    }

        private double getResistanceMultiplier() {
        return 0.8;
    }

        private String armorStatus() {
            return "The monster is heavily armored, you deal reduced damage ! \n";
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


}
