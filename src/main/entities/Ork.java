package entities;

import entities.interfaces.Throwable;

import java.util.Random;

public class Ork extends Enemy{
    //an ork that enrages at 30% hp
    private final double enrage_hp;
    private int defaultDamage;

    public Ork(String id, int health, Player player, int valueDefeated) {
        super(id, health, player, valueDefeated);
        this.enrage_hp = health*0.3;
    }

    public Ork(String id, Player player, int valueDefeated) {
        super(id, player, valueDefeated);
        Random r = new Random();
        int hp = r.nextInt(71)+24;
        this.enrage_hp = hp*0.3;
    }

    public Ork(String id, Player player, int valueDefeated, String howTo) {
        super(id, player, valueDefeated, howTo);
        Random r = new Random();
        int hp = r.nextInt(71)+24;
        this.enrage_hp = hp*0.3;
    }

    public String defaultHitBack() {
        Random r = new Random();
        int hit = r.nextInt(2);
        if (hit == 0) {
            return "Nice! " + getId() + " missed their hit.. time to strike!";
        }
        else {
            String throwingBack = getId()+ getEnrageStatus() + " hits you back for " +
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
            String throwingBack = getId()+ getEnrageStatus() + " hits you back for " +
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

    private String getEnrageStatus() {
        if (this.enrage_hp >= this.getHealthPoints()) {
            return " is enranged and";
        } else {
            return "";
        }
    }

}
