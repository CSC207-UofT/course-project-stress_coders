package usecases;

import entities.Door;
import entities.Interactable;

import java.util.HashMap;

public class Hint extends Command{
    @Override
    public String execute(HashMap<String, Interactable> args) {
        if (args.get("door") instanceof Door) {
            return ((Door) args.get("door")).provideHint();
        }
        return "Not a valid door! Try again.";
    }
}
