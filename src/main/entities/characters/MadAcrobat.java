package entities.characters;

import entities.Interactable;

import java.util.Random;

public class MadAcrobat extends Enemy {
    //an enemy type that has a chance to dodge an attack

    public MadAcrobat(String id, Player player, int valueDefeated) {
        super(id, player, valueDefeated);
    }

    public MadAcrobat(String id, Player player, int valueDefeated, String howTo) {
        super(id, player, valueDefeated, howTo);
    }

    public MadAcrobat(String id, int health, Player player, int valueDefeated) {
        super(id, health, player, valueDefeated);
    }

    private boolean hasDodged() {
        Random r = new Random();
        int hit = r.nextInt(3);
        return hit == 0;
    }

    @Override
    public String handleHit(Interactable weapon) {
        if (this.hasDodged()) {
            return "The " + this.getId() + " maniacally laughs as he easily dodges your attack";
        } else {
            return super.handleHit(weapon);
        }
    }
}
