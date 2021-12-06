package usecases;

import entities.Interactable;
import entities.characters.Trader;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Use case to trade an object
 */
public class Trade {
    public String tradeAction(HashMap<String, Interactable> args) {
        String traderString = "trader";
        if (args.get(traderString) instanceof Trader){
            Scanner input = new Scanner(System.in);
            System.out.print("What would you like to buy: ");
            String item = input.nextLine();
            return ((Trader) args.get(traderString)).trade(item);
        } else {
            return "Cannot trade with that";
        }
    }
}
