package usecases;

import entities.interfaces.Consumable;

import java.util.HashMap;

public class ConsumableConstants {
    public static HashMap<String, Consumable> CONSUMABLES = new HashMap<>();

    // Load all normal commands into the COMMANDS hashmap
    public static void loadConsumables(){
         // CONSUMABLES.put("potion", new TalkTo()); populate this
    }

    public void add_command(String itemID, Consumable cons){
        CONSUMABLES.put(itemID, cons);
    }

    public Consumable getCommand(String input) {return CONSUMABLES.get(input);}
}
