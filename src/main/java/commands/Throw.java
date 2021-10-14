package commands;

import Characters.Player;
import interfaces.ThrowableObject;


import java.util.Random;

public class Throw extends Action {

    public Throw(String input, String name, Player user){
        super(input, name, user);
    }

    public String performAction(){
        if (this.user.getWeapon() != null){

            // Get the weapon's throw hit probability and generate a random number to determine if it was a hit
            Double throwProb = this.user.getWeapon().getProb();
            Random rand = new Random();
            int hitNum = rand.nextInt(101);

            // Determine a hit
            if (hitNum < (100 * throwProb)) {
                return "You hit your target";
            } else {
                return "You missed";
            }

        } else {
            // TODO: use Exceptions instead
            return "You can't throw that";
        }
    }
}