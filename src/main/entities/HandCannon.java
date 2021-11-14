package entities;
import java.lang.Math;


public class HandCannon extends ShootableWeapon{

    /**
     * constructs a CrossBow object
     * @param id the id of the crossbow
     * @param ammoCount the amount of ammo (arrows) the crossbow comes with
     */
    public HandCannon(String id, int ammoCount) {
        super(id);
        this.addAmmo(ammoCount);
    }
    /**
     * Adds property hit probability with the probability value
     */
    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(75));
    }

    /**
     * Adds property weight with the weight/damage value that is randomized
     */
    @Override
    public void addWeight() {
        int weight = 30 + (int)(Math.random() * (90 + 1));
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(weight));
    }

}
