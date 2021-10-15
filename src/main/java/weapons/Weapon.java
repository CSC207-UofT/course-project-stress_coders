package weapons;

import characters.Player;
import characters.Character;
import weaponiteminterfaces.CollectableObject;

public class Weapon implements CollectableObject {


    /**
     * Weapon of type <entity> is a throwable or collectable item, with
     * Attributes:
     * Weapon name (name - String)
     * Weapon damage (damage - int)
     * Weapon inUse(isCurrent - bool) false by default.
     *
     * Responsibilities:
     * Enforce that the weapon instance of Weapon has the given attributes.
     * Allow weapon to damage Enemies and ensure that it can be interpreted as a package.
     * Allow weapon to be thrown
     * Collaborators: Player
     *
     * **NOTE: For adding weapons,
     * we make them a subclass of this class and have them implement certain interfaces
     */

    private String name;
    private int damage;
    private boolean inUse;
    //private float hitProb;


    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
        this.inUse = false;
    }


    public int damageCharacter(Character charName) {
        // set the characters HP to its HP after being damaged,
        // and return the new HP
        int newHP = charName.getHealthPoints() - this.damage;
        charName.setHealthPoints(newHP);
        return newHP;
    }


}
