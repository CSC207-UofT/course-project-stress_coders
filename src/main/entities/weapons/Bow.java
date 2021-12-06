package entities.weapons;

import entities.InteractableProperties;
import entities.Variable;

public class Bow extends ShootableWeapon {

    /**
     * constructs a Bow object
     * @param id the id of the bow
     * @param ammoCount the amount of ammo (arrows) the bow comes with
     */
    public Bow(String id, int ammoCount) {
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
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(80));
    }

    /**
     * Adds property weight with the weight/damage value that is randomized
     */
    @Override
    public void addWeight() {
        int weight = 25 + (int)(Math.random() * (15));
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(weight));
    }

    public Bow(){}
}
