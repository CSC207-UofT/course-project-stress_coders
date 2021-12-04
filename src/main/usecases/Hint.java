package usecases;

import entities.Door;
import entities.Interactable;

import java.util.HashMap;

public class Hint {
    /**
     * if interactable is instance of door, provides hint using Doorr's provideHint() method
     * @param args Hashmap of String and Interactable of item
     * @return string that is the hint or that says the interactable isn't valid
     */

    public String giveHint(HashMap<String, Interactable> args) {
        if (args.get("door") instanceof Door) {
            return ((Door) args.get("door")).provideHint();
        }
        return "Not a valid door! Try again.";
    }
}
