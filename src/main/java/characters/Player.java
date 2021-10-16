package characters;
import items.Item;
import weapons.Weapon;
import java.util.List;

public class Player extends Character {
    /**
     *  A Player subclass of Character with inventory, health points, and weapon.
     */

    /**
     * Constructs a Player with attributes inventory, healthPoints, and weapon
     *
     * @param inventory    the list of items that Enemy has
     * @param healthPoints the number of health points of Enemy
     * @param weapon       weapon of Enemy
     */
    public Player(List<Item> inventory, int healthPoints, Weapon weapon) {
        super(inventory, healthPoints, weapon);
    }

    /**
     * Creates a new Enemy with default inventory, health points, and weapon.
     */
    public Player() {
        super();
    }

    /**
     * Adds an item to the Player's inventory. If it's a weapon, equip it.
     *
     * @param article Item or Weapon subclass
     */
    public <T> void pickUp(T article) {
        if (article instanceof Weapon) {
            this.setWeapon((Weapon) article);
        }
        if (article instanceof Item) {
            this.getInventory().add((Item) article);
            // need to call hp restoration?
        }
    }

    /**
     * If the method is called on a weapon, equip it. Else, remove it from the Player's inventory
     *
     * @param article Item or Weapon subclass
     */
    public <T> void disposeItem(T article) {
        if (article instanceof Weapon) {
            this.setWeapon(null);
        }
        if (article instanceof Item) {
            this.getInventory().remove((Item) article);
        }
    }

}
