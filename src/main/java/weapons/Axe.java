package weapons;

import weaponiteminterfaces.CollectableObject;
import weaponiteminterfaces.ThrowableObject;
import characters.Player;

import java.util.Random;

public class Axe extends Weapon implements ThrowableObject, CollectableObject {
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
    public String throwObj(Player player) {

        // Get the weapon's throw hit probability and generate a random number to determine if it was a hit
        Random rand = new Random();
        // throwProb is the throwing Probability, which is randomly selected.
        int throwProb = rand.nextInt(101);

        //modify inventory
        player.disposeItem(player.getWeapon());
        // Determine a hit
        if (this.hitProb < (100 * throwProb)) {
            return "You hit your target";
        } else {
            return "You missed";
        }

    }
}
