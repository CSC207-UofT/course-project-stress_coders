package characters;

import items.Item;
import weapons.Weapon;

import java.util.List;

public class Enemy extends Character{
    /**
     * Weapon of type <entity> is a throwable or collectable item, with
     * Attributes:
     * Type: Use Case
     * Class name: Enemy
     * Attributes:
     * Items - enemyItems (This is the class in laster slides))
     * HP - In
     * Weapon - currentWeapon (or rather a subclass of this class)
     *
     *
     * Responsibilities:
     * Enforce that each character instance has the given attributes.
     *
     * Collaborators: Character
     */


    public Enemy(List<Item> inventory, int healthPoints, Weapon weapon) {
        super(inventory, healthPoints, weapon);
    }

    public Enemy() {
        super();
    }



}
