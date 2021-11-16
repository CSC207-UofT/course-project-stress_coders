package usecases;

import entities.Interactable;
import entities.InteractableProperties;
import entities.Trader;
import entities.Variable;
import entities.interfaces.Consumable;
import entities.interfaces.Throwable;
import entities.interfaces.ThrowableTarget;

import java.util.HashMap;

/**
 * Trade with a trader command
 */
public class Trade extends Command{

    @Override
    public String execute(HashMap<String, Interactable> args) {
        String traderString = "trader"; String itemString = "item";
        if (args.get(traderString) instanceof Trader){
            return ((Trader) args.get(traderString)).trade((Consumable) args.get(itemString));
        } else {
            return "Cannot trade with that";
        }
    }
}
