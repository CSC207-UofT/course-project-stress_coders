package commands;

import characters.Player;
import weaponiteminterfaces.ThrowableObject;

public class Throw extends Action {

    public Throw(String input, String name, Player user){
        super(input, name, user);
    }

    public String performAction(){
        if (this.user.getWeapon() != null){
            if (this.user.getWeapon() instanceof ThrowableObject) {
                // player has this object and can throw it
                // modify the inventory
                // TODO: should inventory modification be done here? or in weapon.throw()
                this.user.disposeItem(this.user.getWeapon());
                return ((ThrowableObject) this.user.getWeapon()).throwObj();
            } else {
                return "This object is not throwable";
            }
        } else {
            // TODO: use Exceptions instead
            return "No weapon to throw";
        }
    }
}