package entities;
import java.lang.Math;

/*
A crossbow is a shootable weapon with damage that can vary from 0.8 times the damage value to 1.4 times the damage value
 */

public class Crossbow extends ShootableWeapon{

    /**
     * constructs a CrossBow object
     * @param id the id of the crossbow
     * @param ammoCount the amount of ammo (arrows) the crossbow comes with
     */
    public Crossbow(String id, int ammoCount) {
        super(id);
        this.addAmmo(ammoCount);

        addHitProbability();
        addWeight();

    }
    /**
     * Adds property hit probability with the probability value
     */
    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(60));
    }

    /**
     * Adds property weight with the weight/damage value that is randomized
     */
    @Override
    public void addWeight() {
        int weight = 10 + (int)(Math.random() * (90 + 1));
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(weight));
    }

}
