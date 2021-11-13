package entities;

import entities.Interactable;
import entities.interfaces.Consumable;

public class UnusablePotion extends Potion implements Consumable {
    public UnusablePotion() {
        super("broke");
    }

    @Override
    public void addRestorationValue() {
        super.addProperty(InteractableProperties.CONSUMABLE_REST_NAME.name(), new Variable(15));
    }
}
