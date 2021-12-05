package entities.weapons;
import entities.InteractableProperties;
import entities.Variable;

import java.lang.Math;


public class HandCannon extends ShootableWeapon {

    /**
     * Construct a HandCannon object
     *
     * @param id the id of the handcannon
     * @param ammoCount the amount of ammo the handcannon comes with
     */
    public HandCannon(String id, int ammoCount) {
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

    public HandCannon(){}
}

