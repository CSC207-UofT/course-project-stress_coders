package entities.interfaces;

import entities.Item;
import entities.Player;

/**
 * Interface that indicates if an item can be consumed
 */
public interface Consumable {
    void addRestorationValue();

    String consume();

    String getId(); // I need this please don't remove
}
