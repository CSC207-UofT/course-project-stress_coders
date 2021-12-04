package usecases;

import entities.Interactable;
import entities.characters.Trader;

import java.util.HashMap;

/**
 * Use case to trade an object
 */
public class Trade {
    public String tradeAction(HashMap<String, Interactable> args) {
        String traderString = "trader";
        if (args.get(traderString) instanceof Trader){
            return ((Trader) args.get(traderString)).trade();
        } else {
            return "Cannot trade with that";
        }
    }
}
