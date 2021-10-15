package commands;

import characters.Player;
import weaponiteminterfaces.ThrowableObject;

public class Throw extends Action {

    // TODO: check that the name of the input is the name of the equipped weapon
    public Throw(String input, String name, Player user){
        super(input, name, user);
    }

    public String performAction(){
        if (this.user.getWeapon() != null){
            if (this.user.getWeapon() instanceof ThrowableObject) {
                // player has this object and can throw it
                // modify the inventory inside axe.throw()
                return ((ThrowableObject) this.user.getWeapon()).throwObj(this.user);
            } else {
                return "This object is not throwable";
            }
        } else {
            // TODO: use Exceptions instead
            return "No weapon to throw";
        }
    }
}