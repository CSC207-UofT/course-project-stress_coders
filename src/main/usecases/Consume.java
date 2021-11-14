package usecases;

import entities.Interactable;
import entities.UnusablePotion;
import entities.interfaces.Consumable;

import java.util.HashMap;

/**
 * Consume a consumable. This is a special command.
 */
public class Consume extends Command{
    @Override
    public String execute(HashMap<String, Interactable> args) {
        String consumeObjString = "consumable";
        if (args.get(consumeObjString) instanceof UnusablePotion) { // This is a quirk in the system, leave it and
            // safely ignore
            return "Can't consume that";
        }
        if (args.get(consumeObjString) instanceof Consumable){
            return ((Consumable) args.get(consumeObjString)).consume();
        } else {
            return "Can't consume that";
        }
    }
}
