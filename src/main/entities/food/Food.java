package entities.food;
import entities.InteractableProperties;
import entities.Item;
import entities.characters.Player;
import entities.interfaces.Consumable;


public abstract class Food extends Item implements Consumable {
    /**
     * Creates a new Food object.
     * This constructor takes in a String as the argument.
     *
     * @param id the id of this potato.
     */
    public Food(String id) {
        super(id, "First call useInventory then [consumable_name]: quantity");
    }


    /**
     * Adds property CONSUMABLE_REST_NAME with the restoration value when implemented in child classes
     */
    public abstract void addRestorationValue();

    /**
     * what happens when a player consumes the food.
     *
     * the food should be removed from the player's items
     * and the health points from the food should be added to player's health points
     */
    public String consume() {
        Player p = (Player) this.getHeldBy();
        if (p != null) {
            p.subConsumable(this, 1);
            p.setHealthPoints(p.getHealthPoints() + (this.getProperty(InteractableProperties.CONSUMABLE_REST_NAME.name()).getInteger()));
            return "You consumed 1 " + this.getId() + "\n" + this.getStringMessage();
        } else {
            return "You don't have this!";
        }
    }

    private String getStringMessage() {
        if ((this.getProperty(InteractableProperties.CONSUMABLE_REST_NAME.name()).getInteger()) >= 0){
            return "You were healed for " + (this.getProperty(InteractableProperties.CONSUMABLE_REST_NAME.name()).getInteger()) + " HP!";
        } else {
            return "Oh no! You lost " + (-(this.getProperty(InteractableProperties.CONSUMABLE_REST_NAME.name()).getInteger())) + " HP!";
        }
    }

    public Food(){}
}
