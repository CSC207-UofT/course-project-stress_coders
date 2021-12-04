import entities.Interactable;
import entities.food.Nuts;
import entities.characters.Player;
import entities.characters.Trader;
import entities.interfaces.Consumable;
import org.junit.Test;
import interfaceadapters.commands.TradeCommand;

import java.util.HashMap;

public class TradeTest {
    @Test
    public void execute(){
        Player p = new Player("devanRocks");
        p.addCurrency(100);
        Trader t = new Trader("id", p);
        Nuts nut = new Nuts("nutty");
        HashMap<String, Consumable> inventoryToAdd = new HashMap<String, Consumable>();
        inventoryToAdd.put(nut.getId(), nut);
        t.addConsumablesToStore(inventoryToAdd);

        TradeCommand tradeCmd = new TradeCommand();
        HashMap<String, Interactable> args = new HashMap<>();
        args.put("trader", t);
        // String res = tradeCmd.execute(args);
        // assertEquals("Bought nutty for 10 geld", res);
    }
}
