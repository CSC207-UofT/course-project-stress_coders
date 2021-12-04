package entities.food;

import entities.InteractableProperties;
import entities.Variable;
import entities.food.Potion;
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
