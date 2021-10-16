package commands;

import characters.Player;
import weaponiteminterfaces.ThrowableObject;

import java.util.Random;

public class Throw extends Action {


    // TODO: check that the name of the input is the name of the equipped weapon
    public Throw(String input, String name, Player user){
        super(input, name, user);
    }

    public String performAction(float hitProb){
        if (this.user.getWeapon() != null){
            if (this.user.getWeapon() instanceof ThrowableObject) {
                this.user.disposeItem(this.user.getWeapon());
                Random rand = new Random();
                // throwProb is the throwing Probability, which is randomly selected.
                int throwProb = rand.nextInt(101);
                if (hitProb > throwProb) {
                    return "You hit your target";
                } else {
                    return "You missed";
                }
            } else {
                return "This object is not throwable";
            }
        } else {
            // TODO: use Exceptions instead
            return "No weapon to throw";
        }
    }

    public String performAction() {return "This is just some boilerplate because of errors.";}

}