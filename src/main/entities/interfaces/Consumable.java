package entities.interfaces;

import entities.Player;

/**
 * Interface that indicates if an item can be consumed
 */
public interface Consumable {
    void addRestorationValue();

    String consume();
}
