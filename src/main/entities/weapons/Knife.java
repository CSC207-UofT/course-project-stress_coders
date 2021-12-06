package entities.weapons;

import entities.InteractableProperties;
import entities.Variable;

public class Knife extends SwingableWeapon{
    /**
     * constructs a Knife object
     * @param id the id of the knife
     */
    public Knife(String id) {
        super(id);
        addHitProbability();
        addWeight();

    }
    /**
     * Adds property hit probability with the probability value
     */
    @Override
    public void addHitProbability() {
        super.addProperty(InteractableProperties.HIT_PROB.name(), new Variable(65));
    }

    /**
     * Adds property weight with the weight/damage value that is randomized
     */
    @Override
    public void addWeight() {
        int weight = 1 + (int)(Math.random() * (99));
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(weight));
    }

    public Knife(){}
}
