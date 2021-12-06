package entities.weapons;

import entities.InteractableProperties;
import entities.Variable;

public class HeavySword extends SwingableWeapon{
    /**
     * constructs a Sword object
     * @param id the id of the heavy sword
     */
    public HeavySword(String id) {
        super(id);
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
        int weight = 30 + (int)(Math.random() * (60 + 1));
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(weight));
    }

    public HeavySword(){}
}
