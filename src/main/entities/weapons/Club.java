package entities.weapons;

import entities.InteractableProperties;
import entities.Variable;

public class Club extends SwingableWeapon{
    /**
     * constructs a Club object
     * @param id the id of the club
     */
    public Club(String id) {
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
        int weight = 25 + (int)(Math.random() * (10 + 1));
        super.addProperty(InteractableProperties.WEIGHT.name(), new Variable(weight));
    }

    public Club(){}
}
