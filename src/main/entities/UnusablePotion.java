package entities;

import entities.Interactable;
import entities.interfaces.Consumable;

public class UnusablePotion extends Potion implements Consumable {
    public UnusablePotion() {
        super("broke", 0);
    }

    @Override
    public int restorationValue() {
        return 0;
    }

    @Override
    public String getID() {
        return "never use";
    }
}
