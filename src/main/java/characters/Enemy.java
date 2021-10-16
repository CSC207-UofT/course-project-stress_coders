package characters;

import items.Item;
import weapons.Weapon;

import java.util.List;

public class Enemy extends Character{
    /**
     *  An Enemy subclass of Character with inventory, health points, and weapon.
     */

    /**
     * Creates a new Enemy.
     *
     * @param inventory            the list of items that Enemy has
     * @param healthPoints         the number of health points of Enemy
     * @param weapon               weapon of Enemy
     */
    public Enemy(List<Item> inventory, int healthPoints, Weapon weapon) {
        super(inventory, healthPoints, weapon);
    }

    /**
     * Creates a new Enemy with default inventory, health points, and weapon.
     */
    public Enemy() {
        super();
    }



}
