import java.util.Random

public class Throw extends Action{

    public Throw(String input, String name, Player user){
        super(input, name, user)
    }

    public String performAction(){
        if(this.user.getWeapon() instanceof ThrowableObject){

            // Get the weapon's throw hit probability and generate a random number to determine if it was a hit
            throwProb = this.user.getWeapon().getProb();
            Random rand = new Random();
            hitNum = rand.nextIn(101);

            // Determine a hit
            if (hitnum < (100 * throwProb)) {
                return "You hit your target";
            } else {
                return "You missed";
            }

        } else {
            // TODO: use Exceptions insted
            return "You can't throw that";
        }
    }
}