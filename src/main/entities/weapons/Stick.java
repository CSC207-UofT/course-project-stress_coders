package entities.weapons;

import entities.InteractableProperties;
import entities.Variable;

public class Stick extends SwingableWeapon {
    /**
     * constructs a Stick object
     * @param id the id of the stick
     */
    public Stick(String id) {
        super(id);
        addHitProbability();
        addWeight();

    }
    /**
     * Adds property hit probability with the probability value
     */
    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(95));
    }

    /**
     * Adds property weight with the weight/damage value that is randomized
     */
    @Override
    public void addWeight() {
        int weight = 10 + (int)(Math.random() * (5));
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(weight));
    }

    public Stick(){}
}
