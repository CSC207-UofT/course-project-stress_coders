import entities.Interactable;
import entities.Nuts;
import entities.Player;
import entities.Trader;
import entities.interfaces.Consumable;
import org.junit.Test;
import usecases.Trade;
import static org.junit.Assert.*;

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

        Trade tradeCmd = new Trade();
        HashMap<String, Interactable> args = new HashMap<>();
        args.put("trader", t);
        // String res = tradeCmd.execute(args);
        // assertEquals("Bought nutty for 10 geld", res);
    }
}
