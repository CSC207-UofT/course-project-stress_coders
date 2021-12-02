package entities;

import entities.interfaces.Throwable;

import java.util.Random;

public class Ork extends Enemy{
    //an ork that enrages at 30% hp
    private final double enrage_hp;
    private int defaultDamage;
    private final int valueDefeated;
    private final Player player;

    public Ork(String id, int health, Player player, int valueDefeated) {
        super(id, health, player, valueDefeated);
        this.enrage_hp = health*0.3;
        this.valueDefeated = valueDefeated;
        this.player = player;
    }

    public Ork(String id, Player player, int valueDefeated) {
        super(id, player, valueDefeated);
        Random r = new Random();
        int hp = r.nextInt(71)+24;
        this.enrage_hp = hp*0.3;
        this.valueDefeated = valueDefeated;
        this.player = player;
    }

    public Ork(String id, Player player, int valueDefeated, String howTo) {
        super(id, player, valueDefeated, howTo);
        Random r = new Random();
        int hp = r.nextInt(71)+24;
        this.enrage_hp = hp*0.3;
        this.valueDefeated = valueDefeated;
        this.player = player;
    }

    public String defaultHitBack() {
        Random r = new Random();
        int hit = r.nextInt(2);
        if (hit == 0) {
            return "Nice! " + getId() + " missed their hit.. time to strike!";
        }
        else {
            String throwingBack = getId()+" is enraged and hits you back for " +
                    (int) (this.defaultDamage * this.getDamageMultiplier()) +" !\n";
            getPlayer().setHealthPoints((int)(getPlayer().getHealthPoints() -
                    (defaultDamage*this.getDamageMultiplier())));
            return throwingBack;
        }
    }

    public String hitBack(Throwable throwable) {
        Random r = new Random();
        int hit = r.nextInt(2);
        if (hit == 0) {
            return "Nice! " + getId() + " missed their hit.. time to strike!";
        }
        else {
            String throwingBack = getId()+" is enraged and hits you back for " +
                    ((Interactable) throwable).getProperty(InteractableProperties.WEIGHT.name()).getInteger() +" !";
            getPlayer().setHealthPoints((int) (getPlayer().getHealthPoints() -
                    ((Interactable) throwable).getProperty(InteractableProperties.WEIGHT.name()).getInteger()*this.getDamageMultiplier()));
            return throwingBack;
        }
    }

    @Override
    public void setDefaultDamage() {
        Random r = new Random();
        this.defaultDamage = r.nextInt(20) + 39;
    }

    private double getDamageMultiplier() {
        if (this.getHealthPoints() < this.enrage_hp) {
            return 1.8;
        } else {
            return 1;
        }
    }

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

}
