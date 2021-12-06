package entities.characters;

import entities.Interactable;
import entities.InteractableProperties;
import entities.interfaces.Throwable;

import java.util.Random;

public class FeralGhoul extends Enemy{
    //An enemy that heals for 50% of the damage it deals
//    private int defaultDamage;

    public FeralGhoul(String id, Player player, int valueDefeated) {
        super(id, player, valueDefeated);
    }

    public FeralGhoul(String id, Player player, int valueDefeated, String howTo) {
        super(id, player, valueDefeated, howTo);
    }

    public FeralGhoul(String id, int health, Player player, int valueDefeated) {
        super(id, health, player, valueDefeated);
    }

    private int getHeal(int damage) {
        return ((int) (damage / 2));
    }

    public String hitBack(Throwable throwable) {
        Random r = new Random();
        int hit = r.nextInt(2);
        int healing_done = this.getHeal(((Interactable) throwable).getProperty(InteractableProperties.WEIGHT.name()).getInteger());
        if (hit == 0) {
            return "Nice! " + getId() + " missed their hit.. time to strike!";
        }
        else {
            String throwingBack = (getId() + " hits you back for " +
                    ((Interactable) throwable).getProperty(InteractableProperties.WEIGHT.name()).getInteger() +
                    " and heals for " + healing_done + " !");
            getPlayer().setHealthPoints(getPlayer().getHealthPoints() -
                    ((Interactable) throwable).getProperty(InteractableProperties.WEIGHT.name()).getInteger());
            this.setHealthPoints(this.getHealthPoints() + healing_done);
            return throwingBack;
        }
    }

    public String defaultHitBack() {
        Random r = new Random();
        int hit = r.nextInt(2);
        int healing_done = this.getHeal(this.defaultDamage);
        if (hit == 0) {
            return "Nice! " + getId() + " missed their hit.. time to strike!";
        }
        else {
            String throwingBack = getId()+" hits you back for " +
                    this.defaultDamage + " and heals for "+ healing_done +" !\n";
            getPlayer().setHealthPoints(getPlayer().getHealthPoints() -
                    defaultDamage);
            this.setHealthPoints(this.getHealthPoints() + healing_done);
            return throwingBack;
        }
    }

    public void setDefaultDamage() {
        Random r = new Random();
        this.defaultDamage = r.nextInt(25) + 24;
    }

    public FeralGhoul(){}
}
