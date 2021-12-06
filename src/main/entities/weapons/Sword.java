package entities.weapons;

import entities.InteractableProperties;
import entities.Variable;

public class Sword extends SwingableWeapon{
    /**
     * constructs a Sword object
     * @param id the id of the sword
     */
    public Sword(String id) {
        super(id);
        addHitProbability();
        addWeight();

    }
    /**
     * Adds property hit probability with the probability value
     */
    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(85));
    }

    /**
     * Adds property weight with the weight/damage value that is randomized
     */
    @Override
    public void addWeight() {
        int weight = 15 + (int)(Math.random() * (45 + 1));
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(weight));
    }

    public Sword(){}
}
