package interfaceadapters.commands;

import entities.Interactable;
import usecases.Trade;

import java.util.HashMap;

/**
 * Trade with a trader command
 */
public class TradeCommand extends Command {

    final Trade tradeAction;

    public TradeCommand() {
        this.tradeAction = new Trade();
    }

    @Override
    public String execute(HashMap<String, Interactable> args) {
        return this.tradeAction.tradeAction(args);
    }
}
