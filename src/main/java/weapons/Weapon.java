package weapons;

import characters.Player;
import characters.Character;
import weaponiteminterfaces.CollectableObject;

public class Weapon implements CollectableObject {


    // === Instance Variables ===

    // The name of this Weapon.
    private String name;
    // The damage this Weapon inflicts upon Characters.
    private int damage;
    // Whether this Weapon is in use.
    private boolean inUse;


    // === Representation Invariants ===
    // damage >= 0

    /**
     * Creates a new Weapon.
     *
     * Weapon is a CollectableObject.
     *
     * @param name          the name of this Weapon.
     * @param damage        the damage this Weapon inflicts.
     */

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
        this.inUse = false;
    }

    /**
     * Sets the new HP of the character after being damaged by the Weapon,
     * and returns the new HP.
     *
     * @param charName  the Character to be damaged.
     * @return a non negative integer for the Character's HP after being damaged.
     */

    public int damageCharacter(Character charName) {
        int newHP = charName.getHealthPoints() - this.damage;
        charName.setHealthPoints(newHP);
        return newHP;
    }


}
