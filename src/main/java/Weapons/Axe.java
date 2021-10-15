package Weapons;

import interfaces.ThrowableObject;

import java.util.Random;

public class Axe extends Weapon implements ThrowableObject {
    // The probability of weapon hitting a successfully hitting a Character.
    private float hitProb;

    public Axe(String name, float hitProb) {
        super(name,25); // 25 HP of damage
        this.hitProb = hitProb;
    }

    public float getProb() {return this.hitProb;}

    /** This method throws the axe. hitProb is the probability of throwing an axe.
     *  throwProb is the random quantity that uses hitProb to either successfully hit something or not.
     */
    public String throwObj() {

        // Get the weapon's throw hit probability and generate a random number to determine if it was a hit
        Random rand = new Random();
        // throwProb is the throwing Probability, which is randomly selected.
        int throwProb = rand.nextInt(101);

        // Determine a hit
        if (this.hitProb < (100 * throwProb)) {
            return "You hit your target";
        } else {
            return "You missed";
        }

    }
}
