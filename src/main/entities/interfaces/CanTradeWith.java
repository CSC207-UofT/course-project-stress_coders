package entities.interfaces;

import entities.Player;

/**
 * interface to enforce if you can trade with the trader
 */
public interface CanTradeWith {
    String trade(Consumable item);
}
