package Characters;

import Weapons.Weapon;

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


    public int dealDamage(int damageAmount) {
        /**
         * returns an integer representing the damage dealt
         * to this object.
         */
        if (damageAmount <= this.HP) {
            return this.HP - damageAmount;
        } // otherwise damageAmount > this.HP
        return 0;
    }

}
