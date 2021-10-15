package characters;

import weapons.Weapon;

public class Enemy {
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

    // private Items enemyItems;
    private int HP;
    private Weapon currentWeapon;


    public Enemy(int HP, Weapon currentWeapon) {
        this.HP = HP;
        this.currentWeapon = currentWeapon;
    }



}