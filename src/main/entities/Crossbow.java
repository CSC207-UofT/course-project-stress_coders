package entities;
import java.lang.Math;

/*
A crossbow is a shootable weapon with damage that can vary from 0.8 times the damage value to 1.4 times the damage value
 */

public class Crossbow extends ShootableWeapon{

    public Crossbow(String id, int ammoCount, int damage) {
        super(id);
        this.addAmmo(ammoCount);
        this.addDamage(damage);
        this.addHitProb(100);
    }

    @Override
    public int getDamage(){
        int lowerBound = (int)(Math.round(0.8*this.damage));
        int upperBound = (int)(Math.round(1.4*this.damage));
        int range = lowerBound - upperBound;
        return ((int)(Math.random() * range) + lowerBound);
    }

}
