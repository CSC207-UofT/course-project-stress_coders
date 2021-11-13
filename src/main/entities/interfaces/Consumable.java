package entities.interfaces;

import entities.Player;

public interface Consumable {
    void addRestorationValue();

    String consume();
}
